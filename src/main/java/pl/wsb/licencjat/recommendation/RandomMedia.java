package pl.wsb.licencjat.recommendation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class RandomMedia<T> {
    protected EntityManagerFactory emFactory;
    protected int userID;
    protected List<T> mediaList;
    protected String selectMediaQuery;
    protected Long foundID;
    EntityManager entityManager;

    public RandomMedia(int userID) {
        emFactory = Persistence.createEntityManagerFactory("spring-jpa-pu");
        entityManager = emFactory.createEntityManager();
        this.userID = userID;
    }

    protected abstract void getMedia();
    public abstract Long getRandomMedia();

}
