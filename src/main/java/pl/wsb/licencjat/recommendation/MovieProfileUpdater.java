package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.Movie;
import pl.wsb.licencjat.model.database.MoviesProfiles;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class MovieProfileUpdater extends ProfileUpdater<Movie, MoviesProfiles> {

    public MovieProfileUpdater(long userID) {
        super(userID);
        String userQuery = "select c from MoviesProfiles c where c.userID=" + userID;
        Query query = entityManager.createQuery(userQuery);
        userData = (MoviesProfiles) query.getResultList().get(0);
    }

    public void ModifyProfile(long mediaID, int score) {
        if(score == 0) {
            return;
        }

        String movieQuery = "select c from Movie c where c.id=" + mediaID;
        System.out.println(movieQuery);
        Query query = entityManager.createQuery(movieQuery);
        media = (Movie) query.getResultList().get(0);
        System.out.println(media.toString());
        userData.increaseAction(score * media.getAction());
        userData.increaseAdventure(score * media.getAdventure());
        userData.increaseAnimation(score * media.getAnimation());
        userData.increaseDrama(score * media.getDrama());
        userData.increaseComedy( score * media.getComedy());
        userData.increaseCrime( score * media.getCrime());
        userData.increaseDocumentary( score * media.getDocumentary());
        userData.increaseFamily( score * media.getFamily());
        userData.increaseFantasy( score * media.getFantasy());
        userData.increaseHistory( score * media.getHistory());
        userData.increaseHorror( score * media.getHorror());
        userData.increaseMusic( score * media.getMusic());
        userData.increaseMystery( score * media.getMystery());
        userData.increaseRomance( score * media.getRomance());
        userData.increaseScienceFiction(score * media.getScienceFiction());
        userData.increaseThriller(score * media.getThriller());
        userData.increaseTvMovie( score * media.getTvMovie());
        userData.increaseWar(score * media.getWar());
        userData.increaseWestern(score * media.getWestern());

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(userData);
        tx.commit();
    }

}
