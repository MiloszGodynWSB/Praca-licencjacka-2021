package pl.wsb.licencjat.recommendation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class MediaMatcher<T> {
    protected EntityManagerFactory emFactory;
    protected Long mediaID;
    protected int userID;
    protected T mainMedia;
    protected Double mediaMagnitude;
    protected List<T> mediaToCompare;
    protected SortedMap<Long, Double> results;
    protected String selectMediaQuery;
    protected Long foundID;
    EntityManager entityManager;

    /**
     * Constructs MediaMatcher object for specified user
     * @param userID user that media matching will be done for. Needed for ignore list
     */
    public MediaMatcher(int userID) {
        emFactory = Persistence.createEntityManagerFactory("spring-jpa-pu");
        entityManager = emFactory.createEntityManager();
        results = new TreeMap<Long, Double>();
        this.userID = userID;
    }

    protected abstract int calculateDotProduct(T media);
    protected abstract Double calculateMagnitude(T media);
    protected Double calculateSimilarity(T media) {
        return calculateDotProduct(media) / (mediaMagnitude * calculateMagnitude(media));
    }

    /**
     * Sets Media that other media will be compared to
     * @param mediaID
     */
    public abstract void setMainMedia(Long mediaID);
    protected abstract void getMediaToCompare();
    protected abstract void findSimilarMedia();

    /**
     * Finds the most similar media to the main one based on user ignore list
     * @return id of found media
     */
    public Long getSimilarMedia(){
        getMediaToCompare();
        findSimilarMedia();
        return foundID;
    }

}
