package pl.wsb.licencjat.controller;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsb.licencjat.model.database.Movie;
import pl.wsb.licencjat.model.tmdb.TmdbMovie;
import pl.wsb.licencjat.repository.MovieRepository;
import pl.wsb.licencjat.services.TmdbApiConsumer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    private MovieRepository movieRepository;
    private TmdbApiConsumer tmdbApiConsumer;

    public MoviesController(MovieRepository movieRepository, TmdbApiConsumer tmdbApiConsumer) {
        this.movieRepository = movieRepository;
        this.tmdbApiConsumer = tmdbApiConsumer;
    }

    @RequestMapping("")
    String createMoviesPage(Model model) {
        model.addAttribute("random", tmdbApiConsumer.getMovie(movieRepository.findRandomMovie().getId()));
        model.addAttribute("movie", tmdbApiConsumer.getMovie(movieRepository.findRandomMovie().getId()));
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
        return "movies";
    }

    @PostMapping("/find/{id}")
    String getSimilarMovieTo(@PathVariable String id, HttpServletRequest request, Model model) {
        return "movies";
    }

    @RequestMapping("/filters")
    String getMovieFromFilters(HttpServletRequest request, Model model) {
        Set<String> genres = request.getParameterMap().keySet();
        model.addAttribute("movie", new TmdbMovie());
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
        return "movies";
    }
}
