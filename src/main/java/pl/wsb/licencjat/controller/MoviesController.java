package pl.wsb.licencjat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsb.licencjat.model.database.User;
import pl.wsb.licencjat.model.tmdb.TmdbMovie;
import pl.wsb.licencjat.recommendation.GenreMatcherMovies;
import pl.wsb.licencjat.recommendation.MediaMatcherMovies;
import pl.wsb.licencjat.recommendation.MovieProfileUpdater;
import pl.wsb.licencjat.repository.IgnoredMoviesRepository;
import pl.wsb.licencjat.repository.MovieRepository;
import pl.wsb.licencjat.repository.UserRepository;
import pl.wsb.licencjat.services.TmdbApiConsumer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private TmdbApiConsumer tmdbApiConsumer;
    private IgnoredMoviesRepository ignoredMoviesRepository;

    public MoviesController(MovieRepository movieRepository, UserRepository userRepository, TmdbApiConsumer tmdbApiConsumer, IgnoredMoviesRepository ignoredMoviesRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.tmdbApiConsumer = tmdbApiConsumer;
        this.ignoredMoviesRepository = ignoredMoviesRepository;
    }

    @RequestMapping("")
    String createMoviesPage(HttpServletRequest request, Model model) {
        User user = getCurrentUser(request);
        GenreMatcherMovies genreMatcherMovies = new GenreMatcherMovies();
        genreMatcherMovies.setGenrePreference(user.getId());
        model.addAttribute("random", tmdbApiConsumer.getMovie(movieRepository.findRandomMovie().getId()));
        model.addAttribute("movie", tmdbApiConsumer.getMovie(genreMatcherMovies.getRecommendation()));
        return "movies";
    }

    @PostMapping("/random")
    String getRandomMovie(HttpServletRequest request, Model model) {
        model.addAttribute("random", tmdbApiConsumer.getMovie(movieRepository.findRandomMovie().getId()));
        model.addAttribute("movie", tmdbApiConsumer.getMovie(request.getParameter("movie")));
        return "movies";
    }

    @PostMapping("/find")
    String getMovieForUser(HttpServletRequest request, Model model) {
        User user = getCurrentUser(request);
        GenreMatcherMovies genreMatcherMovies = new GenreMatcherMovies();
        genreMatcherMovies.setGenrePreference(user.getId());
        model.addAttribute("random", tmdbApiConsumer.getMovie(request.getParameter("random")));
        model.addAttribute("movie", tmdbApiConsumer.getMovie(genreMatcherMovies.getRecommendation()));
        return "movies";
    }

    @RequestMapping("/find/{id}")
    String getSimilarMovieTo(@PathVariable String id, HttpServletRequest request, Model model) {
        User user = getCurrentUser(request);
        MediaMatcherMovies mediaMatcherMovies = new MediaMatcherMovies(user.getId());
        mediaMatcherMovies.setMainMedia(Long.parseLong(id));
        model.addAttribute("random", tmdbApiConsumer.getMovie(movieRepository.findRandomMovie().getId()));
        model.addAttribute("movie", tmdbApiConsumer.getMovie(mediaMatcherMovies.getSimilarMedia()));
        return "movies";
    }

    @RequestMapping("/filters")
    String getMovieFromFilters(HttpServletRequest request, Model model) {
        List<String> genres = request.getParameterMap()
                .keySet()
                .stream()
                .collect(Collectors.toList());
        User user = getCurrentUser(request);
        GenreMatcherMovies genreMatcherMovies = new GenreMatcherMovies();
        genreMatcherMovies.setGenrePreference(user.getId(), genres);
        model.addAttribute("movie", tmdbApiConsumer.getMovie(genreMatcherMovies.getRecommendation()));
        return "filtersMovies";
    }

    @PostMapping("/similar")
    String getSimilarMovie(HttpServletRequest request, Model model) {
        String searchPhrase = request.getParameter("title");
        List<Long> ids = tmdbApiConsumer.searchMovie(searchPhrase);
        if (ids.size() > 1) {
            List<TmdbMovie> movies = new ArrayList<>();
            for (Long id : ids) {
                movies.add(tmdbApiConsumer.getMovie(id));
            }
            model.addAttribute("search", searchPhrase);
            model.addAttribute("movies", movies);
            return "searchMovie";
        } else {
            String id = ids.get(0).toString();
            return "redirect:/movies/find/" + id;
        }
    }

    @PostMapping("/rate")
    String rateMovie(HttpServletRequest request, Model model) {
        MovieProfileUpdater movieProfileUpdater = new MovieProfileUpdater(getCurrentUser(request).getId(), ignoredMoviesRepository);
        String movieId = request.getParameterMap().keySet().iterator().next();
        movieProfileUpdater.modifyProfile(Long.parseLong(movieId), Integer.parseInt(request.getParameter(movieId)));
        movieProfileUpdater.addToIgnoreList(Long.parseLong(movieId));
        return "redirect:/movies";
    }

    private User getCurrentUser(HttpServletRequest request) {
        return userRepository.findByUsername(request.getUserPrincipal().getName());
    }
}
