package pl.wsb.licencjat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.wsb.licencjat.model.Movie;
import pl.wsb.licencjat.repository.MovieRepository;

import java.util.Optional;

@Controller
public class MovieController {

    private MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping(value = "/movies/{id}")
    String getMovie(@PathVariable String id) {
        Long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            System.err.println("Wprowadź poprawne ID");
            return "redirect:/";
        }
        Optional<Movie> movie = movieRepository.findById(longId);
        movie.ifPresentOrElse(
                p -> System.out.println(p.toString()),
                () -> System.out.println("Nie ma takiego filmu")
        );
        return "redirect:/";
    }
}
