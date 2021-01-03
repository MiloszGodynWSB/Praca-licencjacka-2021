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
    protected T mainMedia;
    protected Double mediaMagnitude;
    protected List<T> mediaToCompare;
    protected SortedMap<Long, Double> results;
    protected String selectMediaQuery;
    protected Long foundID;
    EntityManager entityManager;

    public MediaMatcher(Long mediaID) {
        emFactory = Persistence.createEntityManagerFactory("spring-jpa-pu");
        entityManager = emFactory.createEntityManager();
        results = new TreeMap<Long, Double>();
        this.mediaID = mediaID;
    }

    protected abstract int calculateDotProduct(T media);
    protected abstract Double calculateMagnitude(T media);
    protected Double calculateSimilarity(T media) {
        return calculateDotProduct(media) / (mediaMagnitude * calculateMagnitude(media));
    }
    protected abstract void getMainMedia();
    protected abstract void getMediaToCompare();
    protected abstract void findSimilarMedia();

    public Long getSimilarMedia(){
        findSimilarMedia();
        return foundID;
    }

}