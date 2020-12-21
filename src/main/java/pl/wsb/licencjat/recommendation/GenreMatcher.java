package pl.wsb.licencjat.recommendation;

import java.util.List;
import java.util.Map;

import pl.wsb.licencjat.model.enumerations.MediaType;
import pl.wsb.licencjat.model.database.Movie;
import pl.wsb.licencjat.model.database.Series;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class GenreMatcher<T> {
    protected String user;
    protected List<String> genres;
    protected Map<String, Long> results;
    protected EntityManagerFactory emFactory;
    protected List<T> media;
    protected String selectMoviesQuery;
    EntityManager entityManager;


    public GenreMatcher() {
        emFactory = Persistence.createEntityManagerFactory("spring-jpa-pu");
        entityManager = emFactory.createEntityManager();
    }

    public void setGenrePreference(String user, List<String> genres) {
        this.user = user;
        this.genres = genres;
    }

    protected abstract void getMovies();

    public List<T> getShit() {
        getMovies();
        return media;
    }

}
