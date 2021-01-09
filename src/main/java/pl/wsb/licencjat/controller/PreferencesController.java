package pl.wsb.licencjat.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsb.licencjat.model.tmdb.TmdbMovie;
import pl.wsb.licencjat.model.tmdb.TmdbSeries;
import pl.wsb.licencjat.recommendation.MovieProfileUpdater;
import pl.wsb.licencjat.recommendation.SeriesProfileUpdater;
import pl.wsb.licencjat.repository.IgnoredMoviesRepository;
import pl.wsb.licencjat.repository.IgnoredSeriesRepository;
import pl.wsb.licencjat.repository.UserRepository;
import pl.wsb.licencjat.services.TmdbApiConsumer;

import javax.servlet.http.HttpServletRequest;
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
    private IgnoredMoviesRepository ignoredMoviesRepository;
    private IgnoredSeriesRepository ignoredSeriesRepository;
    private UserRepository userRepository;

    public PreferencesController(IgnoredMoviesRepository ignoredMoviesRepository, TmdbApiConsumer tmdbApiConsumer, IgnoredSeriesRepository ignoredSeriesRepository, UserRepository userRepository) {
        this.ignoredMoviesRepository = ignoredMoviesRepository;
        this.tmdbApiConsumer = tmdbApiConsumer;
        this.ignoredSeriesRepository = ignoredSeriesRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/movies")
    String createMoviesPreferencesPage(Model model) {
        model.addAttribute("movies", createTmdbMovies(Arrays.asList(MOVIES_IDS.split(","))));
        return "preferencesMovies";
    }

    @PostMapping("/saveMovies")
    String saveMoviesPreferences(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> ids = Arrays.asList(MOVIES_IDS.split(","));
        MovieProfileUpdater movieProfileUpdater = new MovieProfileUpdater(userRepository.findByUsername(authentication.getName()).getId(), ignoredMoviesRepository);
        for (String id : ids) {
            movieProfileUpdater.modifyProfile(Long.parseLong(id), Integer.parseInt(request.getParameter(id)));
            if (!request.getParameter(id).equals("1")) {
                movieProfileUpdater.addToIgnoreList(Long.parseLong(id));
            }
        }
        return "redirect:/preferences/series";
    }

    @PostMapping("/saveSeries")
    String saveSeriesPreferences(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> ids = Arrays.asList(SERIES_IDS.split(","));
        SeriesProfileUpdater seriesProfileUpdater = new SeriesProfileUpdater(userRepository.findByUsername(authentication.getName()).getId(), ignoredSeriesRepository);
        for (String id : ids) {
            seriesProfileUpdater.modifyProfile(Long.parseLong(id), Integer.parseInt(request.getParameter(id)));
            if (!request.getParameter(id).equals("1")) {
                seriesProfileUpdater.addToIgnoreList(Long.parseLong(id));
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/series")
    String createSeriesPreferencesPage(Model model) {
        model.addAttribute("series", createTmdbSeries(Arrays.asList(SERIES_IDS.split(","))));
        return "preferencesSeries";
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
