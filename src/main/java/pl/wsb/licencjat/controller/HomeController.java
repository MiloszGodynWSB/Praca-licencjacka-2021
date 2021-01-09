package pl.wsb.licencjat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.wsb.licencjat.model.database.MoviesProfiles;
import pl.wsb.licencjat.model.database.User;
import pl.wsb.licencjat.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    private UserRepository userRepository;
    private EntityManager entityManager;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.entityManager = Persistence.createEntityManagerFactory("spring-jpa-pu").createEntityManager();
    }

    @GetMapping("/")
    String home(HttpServletRequest request) {
        User user = userRepository.findByUsername(request.getUserPrincipal().getName());
        if (hasProfile(user.getId()))
            return "home";
        else
            return "redirect:/preferences/movies";
    }

    private boolean hasProfile(long userID) {
        String userQuery = "select c from MoviesProfiles c where c.userID=" + userID;
        Query query = entityManager.createQuery(userQuery);
        List<MoviesProfiles> userData = query.getResultList();
        return !userData.isEmpty();
    }
}
