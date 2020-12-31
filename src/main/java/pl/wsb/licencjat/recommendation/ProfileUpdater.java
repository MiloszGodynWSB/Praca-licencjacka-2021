package pl.wsb.licencjat.recommendation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class ProfileUpdater<T, U> {
    protected long userID;
    protected T media;
    protected U userData;
    protected EntityManagerFactory emFactory;
    protected EntityManager entityManager;

    public ProfileUpdater(long userID) {
        this.userID = userID;
        emFactory = Persistence.createEntityManagerFactory("spring-jpa-pu");
        entityManager = emFactory.createEntityManager();
    }

    public abstract void ModifyProfile(long mediaID, int score);
}
