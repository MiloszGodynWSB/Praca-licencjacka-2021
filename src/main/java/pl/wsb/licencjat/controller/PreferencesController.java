package pl.wsb.licencjat.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsb.licencjat.model.tmdb.TmdbMovie;
import pl.wsb.licencjat.model.tmdb.TmdbSeries;
import pl.wsb.licencjat.services.TmdbApiConsumer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/preferences")
public class PreferencesController {

    @Value("${spring.preferences.movies.ids}")
    private String MOVIES_IDS;
    @Value("${spring.preferences.series.ids}")
    private String SERIES_IDS;
    private final TmdbApiConsumer tmdbApiConsumer;

    public PreferencesController(TmdbApiConsumer tmdbApiConsumer) {
        this.tmdbApiConsumer = tmdbApiConsumer;
    }

    @RequestMapping("")
    String createPreferencesPage(Model model) {
        model.addAttribute("movies", createTmdbMovies(Arrays.asList(MOVIES_IDS.split(","))));
        model.addAttribute("series", createTmdbSeries(Arrays.asList(SERIES_IDS.split(","))));
        return "preferences";
    }

    private List<TmdbMovie> createTmdbMovies(List<String> ids) {
        List<TmdbMovie> movies = new LinkedList<>();
        for (String id : ids) {
            movies.add(tmdbApiConsumer.getMovie(id));
        }
        return movies;
    }

    private List<TmdbSeries> createTmdbSeries(List<String> ids) {
        List<TmdbSeries> series = new LinkedList<>();
        for (String id : ids) {
            series.add(tmdbApiConsumer.getSeries(id));
        }
        return series;
    }
}
