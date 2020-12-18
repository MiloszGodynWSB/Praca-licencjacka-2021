package pl.wsb.licencjat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.wsb.licencjat.model.Movie;
import pl.wsb.licencjat.recommendation.GenreMatcher;
import pl.wsb.licencjat.repository.MovieRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class DebugController {

    private GenreMatcher genreMatcher;

    @Autowired
    public DebugController() {
        this.genreMatcher = new GenreMatcher();
    }

    @GetMapping(value = "/genres/{genre1}/{genre2}")
    String getMovie(@PathVariable("genre1") String genre1, @PathVariable("genre2") String genre2) {

        List<String> lista = new ArrayList<String>();
        lista.add(genre1);
        lista.add(genre2);
        genreMatcher.setGenrePreference("user", List.of(genre1, genre2), "Movie");

        List<Movie> movie = genreMatcher.getShit();
        System.out.println(movie.size());
        return "redirect:/";
    }
}