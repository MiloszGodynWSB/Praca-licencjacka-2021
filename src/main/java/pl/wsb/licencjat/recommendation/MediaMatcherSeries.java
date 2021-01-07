package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.Series;

import javax.persistence.Query;
import java.util.Collections;
import java.util.SortedMap;

public class MediaMatcherSeries extends MediaMatcher<Series> {

    public MediaMatcherSeries(int userID) {
        super(userID);
        selectMediaQuery = "select c from " + "Series" + " c where ";
    }

    protected int calculateDotProduct(Series media) {
        return mainMedia.getAction() * media.getAction() +
                mainMedia.getAdventure() * media.getAdventure() +
                mainMedia.getAnimation() * media.getAnimation() +
                mainMedia.getWar() * media.getWar() +
                mainMedia.getComedy() * media.getComedy() +
                mainMedia.getCrime() * media.getCrime() +
                mainMedia.getDocumentary() * media.getDocumentary() +
                mainMedia.getDrama() * media.getDrama() +
                mainMedia.getFamily() * media.getFamily() +
                mainMedia.getFantasy() * media.getFantasy() +
                mainMedia.getHistory() * media.getHistory() +
                mainMedia.getHorror() * media.getHorror() +
                mainMedia.getKids() * media.getKids() +
                mainMedia.getMusic() * media.getMusic() +
                mainMedia.getMystery() * media.getMystery() +
                mainMedia.getNews() * media.getNews() +
                mainMedia.getReality() * media.getReality() +
                mainMedia.getRomance() * media.getRomance() +
                mainMedia.getScienceFiction() * media.getScienceFiction() +
                mainMedia.getSoap() * media.getSoap() +
                mainMedia.getTalk() * media.getTalk() +
                mainMedia.getThriller() * media.getThriller() +
                mainMedia.getTvMovie() * media.getTvMovie() +
                mainMedia.getWestern() * media.getWestern();
    }

    protected Double calculateMagnitude(Series media) {
        return Math.sqrt(Math.pow(media.getAction(), 2) +
                Math.pow(media.getAdventure(), 2) +
                Math.pow(media.getAnimation(), 2) +
                Math.pow(media.getWar(), 2) +
                Math.pow(media.getComedy(), 2) +
                Math.pow(media.getCrime(), 2) +
                Math.pow(media.getDocumentary(), 2) +
                Math.pow(media.getDrama(), 2) +
                Math.pow(media.getFamily(), 2) +
                Math.pow(media.getFantasy(), 2) +
                Math.pow(media.getHistory(), 2) +
                Math.pow(media.getHorror(), 2) +
                Math.pow(media.getKids(), 2) +
                Math.pow(media.getMusic(), 2) +
                Math.pow(media.getMystery(), 2) +
                Math.pow(media.getNews(), 2) +
                Math.pow(media.getReality(), 2) +
                Math.pow(media.getRomance(), 2) +
                Math.pow(media.getScienceFiction(), 2) +
                Math.pow(media.getSoap(), 2) +
                Math.pow(media.getTalk(), 2) +
                Math.pow(media.getThriller(), 2) +
                Math.pow(media.getTvMovie(), 2) +
                Math.pow(media.getWestern(), 2));
    }

    public void setMainMedia(Long mediaID) {
        this.mediaID = mediaID;
        String selectMoviesQuery = selectMediaQuery + "c.id=" + mediaID;
        System.out.println(selectMoviesQuery);
        Query query = entityManager.createQuery(selectMoviesQuery);
        mainMedia = (Series) query.getResultList().get(0);
        mediaMagnitude = calculateMagnitude(mainMedia);
    }

    protected void getMediaToCompare() {
        String selectMoviesQuery = selectMediaQuery + "not c.id=" + mediaID;
        selectMoviesQuery = selectMoviesQuery + " and c.id not in " +
                "(Select d.movieID from IgnoredSeries d where d.id=" + userID + ")";
        System.out.println(selectMoviesQuery);
        Query query = entityManager.createQuery(selectMoviesQuery);
        mediaToCompare = query.getResultList();
    }

    protected void findSimilarMedia() {
        for (Series series : mediaToCompare) {
            results.put(
                    series.getId(),
                    calculateSimilarity(series)
            );
        }
        foundID = Collections.max(results.entrySet(), SortedMap.Entry.comparingByValue()).getKey();
    }
}
