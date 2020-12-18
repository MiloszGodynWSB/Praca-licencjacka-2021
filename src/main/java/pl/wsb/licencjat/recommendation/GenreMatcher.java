package pl.wsb.licencjat.recommendation;

import java.util.List;
import java.util.Map;

import pl.wsb.licencjat.model.Movie;
import pl.wsb.licencjat.model.Series;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GenreMatcher {
    private String user;
    private List<String> genres;
    private String type; //either movie or a series
    private Map<String, Long> results;
    private EntityManagerFactory emFactory;
    EntityManager entityManager;
    private List<Series> tvShows;
    private List<Movie> movies;

    public GenreMatcher() {
        emFactory = Persistence.createEntityManagerFactory("spring-jpa-pu");
        entityManager = emFactory.createEntityManager();
    }

    long id;

    public void setGenrePreference(String user, List<String> genres, String type) {
        this.user = user;
        this.genres = genres;
        this.type = type;
    }

    void getMovies() {
        long movieID = 0;
        System.out.println(this.type);
        String selectMovies = "select c from " + "Movie" + " c where ";
        for (String genre: genres) {
            selectMovies =  selectMovies + "c." + genre + "=1 and ";
        }
        selectMovies = selectMovies.substring(0, selectMovies.length()-5);
        System.out.println(selectMovies);
        Query query = entityManager.createQuery(selectMovies);
        if(type == "Movie") {
            movies = query.getResultList();
        }
        else {
            tvShows = query.getResultList();
        }
    }

    public List<Movie> getShit() {
        getMovies();
        return movies;
    }

}
