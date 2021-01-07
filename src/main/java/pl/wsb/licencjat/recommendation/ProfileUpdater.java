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

    /**
     * Constructs ProfileUpdater object for specified user
     *
     * @param userID
     * @param repository
     */
    public ProfileUpdater(long userID, CrudRepository repository) {
        this.userID = userID;
        this.repository = repository;
        emFactory = Persistence.createEntityManagerFactory("spring-jpa-pu");
        entityManager = emFactory.createEntityManager();
    }

    /**
     * Modifies user profile based on the media watched and score given
     *
     * @param mediaID
     * @param score   -2 watched and didn't like
     *                -1 didn't watch and dont want to
     *                0 not interested
     *                1 didn't watch but want to
     *                2 watched and liked
     *                OR
     *                1 liked
     *                0 Ignore
     */
    public abstract void modifyProfile(long mediaID, int score);

    /**
     * Adds media to ignore list
     *
     * @param mediaID
     */
    public abstract void addToIgnoreList(long mediaID);
}
