package pl.wsb.licencjat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.wsb.licencjat.model.Series;
import pl.wsb.licencjat.repository.SeriesRepository;

import java.util.Optional;

@Controller
public class SeriesController {

    private SeriesRepository seriesRepository;

    @Autowired
    public SeriesController(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @GetMapping(value = "/series/{id}")
    String getMovie(@PathVariable String id) {
        Long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            System.err.println("Wprowadź poprawne ID");
            return "redirect:/";
        }
        Optional<Series> movie = seriesRepository.findById(longId);
        Series serie1 = new Series();
        movie.ifPresentOrElse(
                p -> System.out.println(p.toString()),
                () -> System.out.println("Nie ma takiego filmu")
        );
        return "redirect:/";
    }
}
