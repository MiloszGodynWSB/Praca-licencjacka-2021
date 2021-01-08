package pl.wsb.licencjat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.wsb.licencjat.repository.UserRepository;

@Controller
public class HomeController {

    private UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    String home() {
        return "home";
    }
}
