package pl.wsb.licencjat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.wsb.licencjat.recommendation.*;
import pl.wsb.licencjat.services.TmdbApiConsumer;

import java.util.List;

@Controller
@RequestMapping(value = "/debug")
public class DebugController {

    private GenreMatcher genreMatcher;
    private TmdbApiConsumer tmdbApiConsumer;
    private ProfileUpdater profileUpdater;

    @Autowired
    public DebugController(TmdbApiConsumer tmdbApiConsumer) {
        this.tmdbApiConsumer = tmdbApiConsumer;
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
        profileUpdater = new MovieProfileUpdater(userID);
        profileUpdater.ModifyProfile(movieID, liked);
        return "redirect:/";
    }

    @GetMapping(value = "/score/series/{userID}/{movieID}/{liked}")
    String updateSeriesProfile(@PathVariable("userID") long userID, @PathVariable("movieID") long movieID, @PathVariable("liked") int liked) {
        profileUpdater = new SeriesProfileUpdater(userID);
        profileUpdater.ModifyProfile(movieID, liked);
        return "redirect:/";
    }

    @GetMapping(value = "/similar/movie/{movieID}")
    String getSimilarMovie(@PathVariable("movieID") long movieID) {
        MediaMatcher matcher = new MediaMatcheMovies(movieID,1);
        System.out.println(matcher.getSimilarMedia());
        return "redirect:/";
    }

    @GetMapping(value = "/similar/series/{movieID}")
    String getSimilarSeries(@PathVariable("movieID") long movieID) {
        MediaMatcher matcher = new MediaMatcherSeries(movieID,1);
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

    @RequestMapping("/security")
    String checkIfSecurityWorks() {
        return "security";
    }
}