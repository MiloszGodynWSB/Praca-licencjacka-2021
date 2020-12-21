package pl.wsb.licencjat.recommendation;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenreMatcher<T> {
    protected long userID;
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

    public void setGenrePreference(long user, List<String> genres) {
        this.userID = user;
        this.genres = genres;
    }

    protected abstract void getMovies();
    protected abstract void calculateDotProducts();
    protected abstract void getUserData();


    public List<T> getShit() {
        getMovies();
        return media;
    }

}
