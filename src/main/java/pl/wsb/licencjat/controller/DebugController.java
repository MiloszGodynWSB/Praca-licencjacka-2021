package pl.wsb.licencjat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pl.wsb.licencjat.model.database.Movie;
import pl.wsb.licencjat.model.database.Series;
import pl.wsb.licencjat.recommendation.GenreMatcher;
import pl.wsb.licencjat.recommendation.GenreMatcherMovies;
import pl.wsb.licencjat.recommendation.GenreMatcherSeries;
import pl.wsb.licencjat.services.TmdbApiConsumer;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/debug")
public class DebugController {

    private GenreMatcher genreMatcher;
    private TmdbApiConsumer tmdbApiConsumer;

    @Autowired
    public DebugController(TmdbApiConsumer tmdbApiConsumer) {
        this.tmdbApiConsumer = tmdbApiConsumer;
    }

    @GetMapping(value = "/movieGenres/{genre1}/{genre2}")
    String getMovie(@PathVariable("genre1") String genre1, @PathVariable("genre2") String genre2) {
        genreMatcher = new GenreMatcherMovies();
        List<String> lista = new ArrayList<>();
        lista.add(genre1);
        lista.add(genre2);
        genreMatcher.setGenrePreference(1, List.of(genre1, genre2));

        Long movie = genreMatcher.getShit();
        System.out.println(movie);
        return "redirect:/";
    }

    @GetMapping(value = "/seriesGenres/{genre1}/{genre2}")
    String getSeries(@PathVariable("genre1") String genre1, @PathVariable("genre2") String genre2) {
        genreMatcher = new GenreMatcherSeries();
        List<String> lista = new ArrayList<>();
        lista.add(genre1);
        lista.add(genre2);
        genreMatcher.setGenrePreference(1, List.of(genre1, genre2));

        Long movie = genreMatcher.getShit();
        System.out.println(movie);
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
}