package pl.wsb.licencjat.recommendation;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public abstract class ProfileUpdater<T, U> {
    protected long userID;
    protected T media;
    protected U userData;
    protected EntityManagerFactory emFactory;
    protected EntityManager entityManager;
    protected CrudRepository repository;

    public ProfileUpdater(long userID, CrudRepository repository) {
        this.userID = userID;
        this.repository = repository;
        emFactory = Persistence.createEntityManagerFactory("spring-jpa-pu");
        entityManager = emFactory.createEntityManager();
    }

    public abstract void ModifyProfile(long mediaID, int score);
    public abstract void AddToIgnoreList(long mediaID);
}
