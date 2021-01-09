package pl.wsb.licencjat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsb.licencjat.model.database.User;
import pl.wsb.licencjat.model.tmdb.TmdbSeries;
import pl.wsb.licencjat.recommendation.GenreMatcherSeries;
import pl.wsb.licencjat.recommendation.MediaMatcherSeries;
import pl.wsb.licencjat.recommendation.SeriesProfileUpdater;
import pl.wsb.licencjat.repository.IgnoredSeriesRepository;
import pl.wsb.licencjat.repository.SeriesRepository;
import pl.wsb.licencjat.repository.UserRepository;
import pl.wsb.licencjat.services.TmdbApiConsumer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/series")
public class SeriesController {

    private SeriesRepository seriesRepository;
    private UserRepository userRepository;
    private TmdbApiConsumer tmdbApiConsumer;
    private IgnoredSeriesRepository ignoredSeriesRepository;

    public SeriesController(SeriesRepository seriesRepository, UserRepository userRepository, TmdbApiConsumer tmdbApiConsumer, IgnoredSeriesRepository ignoredSeriesRepository) {
        this.seriesRepository = seriesRepository;
        this.userRepository = userRepository;
        this.tmdbApiConsumer = tmdbApiConsumer;
        this.ignoredSeriesRepository = ignoredSeriesRepository;
    }

    @RequestMapping("")
    String createSeriesPage(HttpServletRequest request, Model model) {
        User user = getCurrentUser(request);
        GenreMatcherSeries genreMatcherSeries = new GenreMatcherSeries();
        genreMatcherSeries.setGenrePreference(user.getId());
        model.addAttribute("random", tmdbApiConsumer.getSeries(seriesRepository.findRandomSeries().getId()));
        model.addAttribute("series", tmdbApiConsumer.getSeries(genreMatcherSeries.getRecommendation()));
        return "series";
    }

    @PostMapping("/random")
    String getRandomSeries(HttpServletRequest request, Model model) {
        model.addAttribute("random", tmdbApiConsumer.getSeries(seriesRepository.findRandomSeries().getId()));
        model.addAttribute("series", tmdbApiConsumer.getSeries(request.getParameter("series")));
        return "series";
    }

    @PostMapping("/find")
    String getSeriesForUser(HttpServletRequest request, Model model) {
        User user = getCurrentUser(request);
        GenreMatcherSeries genreMatcherSeries = new GenreMatcherSeries();
        genreMatcherSeries.setGenrePreference(user.getId());
        model.addAttribute("random", tmdbApiConsumer.getSeries(request.getParameter("random")));
        model.addAttribute("series", tmdbApiConsumer.getSeries(genreMatcherSeries.getRecommendation()));
        return "series";
    }

    @RequestMapping("/find/{id}")
    String getSimilarSeriesTo(@PathVariable String id, HttpServletRequest request, Model model) {
        User user = getCurrentUser(request);
        MediaMatcherSeries mediaMatcherSeries = new MediaMatcherSeries(user.getId());
        mediaMatcherSeries.setMainMedia(Long.parseLong(id));
        model.addAttribute("random", tmdbApiConsumer.getSeries(seriesRepository.findRandomSeries().getId()));
        model.addAttribute("series", tmdbApiConsumer.getSeries(mediaMatcherSeries.getSimilarMedia()));
        return "series";
    }

    @RequestMapping("/filters")
    String getSeriesFromFilters(HttpServletRequest request, Model model) {
        List<String> genres = request.getParameterMap()
                .keySet()
                .stream()
                .collect(Collectors.toList());
        User user = getCurrentUser(request);
        GenreMatcherSeries genreMatcherSeries = new GenreMatcherSeries();
        genreMatcherSeries.setGenrePreference(user.getId(), genres);
        model.addAttribute("series", tmdbApiConsumer.getSeries(genreMatcherSeries.getRecommendation()));
        return "filtersSeries";
    }

    @PostMapping("/similar")
    String getSimilarSeries(HttpServletRequest request, Model model) {
        String searchPhrase = request.getParameter("title");
        List<Long> ids = tmdbApiConsumer.searchSeries(searchPhrase);
        if (ids.size() > 1) {
            List<TmdbSeries> series = new ArrayList<>();
            for (Long id : ids) {
                series.add(tmdbApiConsumer.getSeries(id));
            }
            model.addAttribute("search", searchPhrase);
            model.addAttribute("series", series);
            return "searchSeries";
        } else {
            String id = ids.get(0).toString();
            return "redirect:/series/find/" + id;
        }
    }

    @PostMapping("/rate")
    String rateSeries(HttpServletRequest request, Model model) {
        SeriesProfileUpdater seriesProfileUpdater = new SeriesProfileUpdater(getCurrentUser(request).getId(), ignoredSeriesRepository);
        String seriesId = request.getParameterMap().keySet().iterator().next();
        seriesProfileUpdater.modifyProfile(Long.parseLong(seriesId), Integer.parseInt(request.getParameter(seriesId)));
        seriesProfileUpdater.addToIgnoreList(Long.parseLong(seriesId));
        return "redirect:/series";
    }

    private User getCurrentUser(HttpServletRequest request) {
        return userRepository.findByUsername(request.getUserPrincipal().getName());
    }
}
