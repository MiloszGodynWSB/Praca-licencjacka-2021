package pl.wsb.licencjat.recommendation;

import org.springframework.data.repository.CrudRepository;
import pl.wsb.licencjat.model.database.IgnoredSeries;
import pl.wsb.licencjat.model.database.Series;
import pl.wsb.licencjat.model.database.SeriesProfiles;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class SeriesProfileUpdater extends ProfileUpdater<Series, SeriesProfiles> {

    public SeriesProfileUpdater(long userID, CrudRepository repository) {
        super(userID, repository);
        String userQuery = "select c from SeriesProfiles c where c.userID=" + userID;
        Query query = entityManager.createQuery(userQuery);
        userData = (SeriesProfiles) query.getResultList().get(0);
    }

    public void ModifyProfile(long mediaID, int score) {
        if(score == 0) {
            return;
        }

        String seriesQuery = "select c from Series c where c.id=" + mediaID;
        System.out.println(seriesQuery);
        Query query = entityManager.createQuery(seriesQuery);
        media = (Series) query.getResultList().get(0);
        System.out.println(media.toString());
        userData.increaseAction(score * media.getAction()); 
        userData.increaseAdventure(score * media.getAdventure());
        userData.increaseAnimation(score * media.getAnimation());
        userData.increaseWar(score * media.getWar());
        userData.increaseComedy(score * media.getComedy());
        userData.increaseCrime(score * media.getCrime());
        userData.increaseDocumentary(score * media.getDocumentary());
        userData.increaseDrama(score * media.getDrama());
        userData.increaseFamily(score * media.getFamily());
        userData.increaseFantasy(score * media.getFantasy());
        userData.increaseHistory(score * media.getHistory());
        userData.increaseHorror(score * media.getHorror());
        userData.increaseKids(score * media.getKids());
        userData.increaseMusic(score * media.getMusic());
        userData.increaseMystery(score * media.getMystery());
        userData.increaseNews(score * media.getNews());
        userData.increaseReality(score * media.getReality());
        userData.increaseRomance(score * media.getRomance());
        userData.increaseScienceFiction(score * media.getScienceFiction());
        userData.increaseSoap(score * media.getSoap());
        userData.increaseTalk(score * media.getTalk());
        userData.increaseThriller(score * media.getThriller());
        userData.increaseTvMovie(score * media.getTvMovie());
        userData.increaseWestern(score * media.getWestern());

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(userData);
        tx.commit();

    }

    public void AddToIgnoreList(long mediaID) {
        IgnoredSeries ignoredSeries = new IgnoredSeries();
        ignoredSeries.setMovieID(mediaID);
        ignoredSeries.setuserID((int) userID);
        repository.save(ignoredSeries);
    }
}
