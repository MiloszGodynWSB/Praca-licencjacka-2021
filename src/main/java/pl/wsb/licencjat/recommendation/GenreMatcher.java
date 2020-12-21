package pl.wsb.licencjat.recommendation;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class GenreMatcher<T> {
    protected long userID;
    protected List<String> genres;
    protected SortedMap<Long, Integer> results;
    protected EntityManagerFactory emFactory;
    protected List<T> media;
    protected String selectMoviesQuery;
    protected Long foundID;
    EntityManager entityManager;


    public GenreMatcher() {
        emFactory = Persistence.createEntityManagerFactory("spring-jpa-pu");
        entityManager = emFactory.createEntityManager();
        results = new TreeMap<Long, Integer>();
    }

    public void setGenrePreference(long user, List<String> genres) {
        this.userID = user;
        this.genres = genres;
    }

    protected abstract void getMovies();
    protected abstract int calculateDotProduct(T movie);
    protected abstract void getUserData();
    protected abstract void matchMovies();


    public Long getShit() {
        getMovies();
        getUserData();
        matchMovies();
        return foundID;
    }

}
