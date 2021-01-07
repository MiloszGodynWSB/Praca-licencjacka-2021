package pl.wsb.licencjat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.wsb.licencjat.model.tmdb.TmdbMovie;
import pl.wsb.licencjat.model.tmdb.TmdbSeries;
import pl.wsb.licencjat.recommendation.*;
import pl.wsb.licencjat.repository.IgnoredMoviesRepository;
import pl.wsb.licencjat.repository.IgnoredSeriesRepository;
import pl.wsb.licencjat.services.TmdbApiConsumer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/debug")
public class DebugController {

    private GenreMatcher genreMatcher;
    private TmdbApiConsumer tmdbApiConsumer;
    private ProfileUpdater profileUpdater;
    private IgnoredSeriesRepository ignoredSeriesRepository;
    private IgnoredMoviesRepository ignoredMoviesRepository;
    @Value("${spring.preferences.movies.ids}") String MOVIES_IDS;
    @Value("${spring.preferences.series.ids}") String SERIES_IDS;


    @Autowired
    public DebugController(TmdbApiConsumer tmdbApiConsumer, IgnoredSeriesRepository ignoredSeriesRepository,
                           IgnoredMoviesRepository ignoredMoviesRepository) {
        this.ignoredSeriesRepository = ignoredSeriesRepository;
        this.ignoredMoviesRepository = ignoredMoviesRepository;
        this.tmdbApiConsumer = tmdbApiConsumer;
    }

    @GetMapping(value = "/randomMovie/{userId}")
    String getRandomMovie(@PathVariable("userId") int userId) {
        RandomMedia media = new RandomMovie(userId);
        System.out.println(media.getRandomMedia());
        return "redirect:/";
    }

    @GetMapping(value = "/randomSeries/{userId}")
    String getRandomSeries(@PathVariable("userId") int userId) {
        RandomMedia media = new RandomSeries(userId);
        System.out.println(media.getRandomMedia());
        return "redirect:/";
    }

    @GetMapping(value = "/movieGenres/{genre1}/{genre2}")
    String getMovie(@PathVariable("genre1") String genre1, @PathVariable("genre2") String genre2) {
        genreMatcher = new GenreMatcherMovies();
        genreMatcher.setGenrePreference(1, List.of(genre1, genre2));
        Long movie = genreMatcher.getRecommendation();
        System.out.println(movie);
        return "redirect:/";
    }

    @GetMapping(value = "/seriesGenres/{genre1}/{genre2}")
    String getSeries(@PathVariable("genre1") String genre1, @PathVariable("genre2") String genre2) {
        genreMatcher = new GenreMatcherSeries();
        genreMatcher.setGenrePreference(1, List.of(genre1, genre2));
        Long movie = genreMatcher.getRecommendation();
        System.out.println(movie);
        return "redirect:/";
    }

    @GetMapping(value = "/movieNoGenres/{userID}")
    String getMovie(@PathVariable("userID") int user) {
        genreMatcher = new GenreMatcherMovies();
        genreMatcher.setGenrePreference(user);
        Long movie = genreMatcher.getRecommendation();
        System.out.println(movie);
        return "redirect:/";
    }

    @GetMapping(value = "/score/movie/{userID}/{movieID}/{liked}")
    String updateMovieProfile(@PathVariable("userID") long userID, @PathVariable("movieID") long movieID, @PathVariable("liked") int liked) {
        profileUpdater = new MovieProfileUpdater(userID, ignoredMoviesRepository);
        profileUpdater.modifyProfile(movieID, liked);
        profileUpdater.addToIgnoreList(movieID);
        return "redirect:/";
    }

    @GetMapping(value = "/score/movie/{userID}/{movieID}")
    String updateMovieProfile(@PathVariable("userID") long userID, @PathVariable("movieID") long movieID) {
        profileUpdater = new MovieProfileUpdater(userID, ignoredMoviesRepository);
        profileUpdater.addToIgnoreList(movieID);
        return "redirect:/";
    }

    @GetMapping(value = "/score/series/{userID}/{movieID}/{liked}")
    String updateSeriesProfile(@PathVariable("userID") long userID, @PathVariable("movieID") long movieID, @PathVariable("liked") int liked) {
        profileUpdater = new SeriesProfileUpdater(userID, ignoredSeriesRepository);
        profileUpdater.modifyProfile(movieID, liked);
        profileUpdater.addToIgnoreList(movieID);
        return "redirect:/";
    }

    @GetMapping(value = "/score/series/{userID}/{movieID}")
    String updateSeriesProfile(@PathVariable("userID") long userID, @PathVariable("movieID") long movieID) {
        profileUpdater = new SeriesProfileUpdater(userID, ignoredSeriesRepository);
        profileUpdater.addToIgnoreList(movieID);
        return "redirect:/";
    }

    @GetMapping(value = "/similar/movie/{movieID}")
    String getSimilarMovie(@PathVariable("movieID") long movieID) {
        MediaMatcher matcher = new MediaMatcherMovies(1);
        matcher.setMainMedia(movieID);
        System.out.println(matcher.getSimilarMedia());
        return "redirect:/";
    }

    @GetMapping(value = "/similar/series/{movieID}")
    String getSimilarSeries(@PathVariable("movieID") long movieID) {
        MediaMatcher matcher = new MediaMatcherSeries(1);
        matcher.setMainMedia(movieID);
        System.out.println(matcher.getSimilarMedia());
        return "redirect:/";
    }

    @GetMapping(value = "/tmdb/api/movie/{id}")
    String consumeTmdbApiMovie(@PathVariable String id, RestTemplate restTemplate) {
        System.out.println(tmdbApiConsumer.getMovie(id).toString());
        return "redirect:/";
    }

    @GetMapping(value = "/tmdb/api/series/{id}")
    String consumeTmdbApiSeries(@PathVariable String id, RestTemplate restTemplate) {
        System.out.println(tmdbApiConsumer.getSeries(id).toString());
        return "redirect:/";
    }

    @GetMapping(value = "/movie/jsontest")
    String consumeTmdbApiSearchMovie(RestTemplate restTemplate) {
        var id = tmdbApiConsumer.searchMovie("It");
        return "redirect:/";
    }

    @GetMapping(value = "/series/jsontest")
    String consumeTmdbApiSearchSeries(RestTemplate restTemplate) {
        var id = tmdbApiConsumer.searchSeries("altered carbon");
        return "redirect:/";
    }

    @RequestMapping("/security")
    String checkIfSecurityWorks() {
        return "security";
    }

    @RequestMapping("/checkIds")
    String checkIds() {
        List<String> moviesList = Arrays.asList(MOVIES_IDS.split(","));
        List<String> seriesList = Arrays.asList(SERIES_IDS.split(","));

        for (String movieId : moviesList) {
            TmdbMovie movie = tmdbApiConsumer.getMovie(movieId);
            System.out.println("Film: " + movie.getTitle());
        }
        System.out.println("------------------");
        for (String seriesId : seriesList) {
            TmdbSeries series = tmdbApiConsumer.getSeries(seriesId);
            System.out.println("Serial: " + series.getName());
        }
        return "redirect:/";
    }
}