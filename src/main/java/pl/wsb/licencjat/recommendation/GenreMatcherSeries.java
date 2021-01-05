package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.Series;
import pl.wsb.licencjat.model.database.SeriesProfiles;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

public class GenreMatcherSeries extends GenreMatcher<Series> {

    private SeriesProfiles userData;

    protected void getMovies() {

        if(genres.size() > 0) {
            selectMoviesQuery = "select c from " + "Series" + " c where ";
            for (String genre : genres) {
                selectMoviesQuery = selectMoviesQuery + "c." + genre + "=1 and ";
            }
            selectMoviesQuery = selectMoviesQuery.substring(0, selectMoviesQuery.length()-5);
            selectMoviesQuery = selectMoviesQuery + " and c.id not in " +
                    "(Select d.movieID from IgnoredSeries d where d.id=" + userID + ")";
        }
        else {
            selectMoviesQuery = "select c from " + "Series" + " c where c.id not in " +
                    "(Select d.movieID from IgnoredSeries d where d.id=" + userID + ")";
        }

        System.out.println(selectMoviesQuery);
        Query query = entityManager.createQuery(selectMoviesQuery);
        media = query.getResultList();
    }

    protected void getUserData() {
        String userQuery = "select c from" + "SeriesProfiles" + "c where c.userID=" + userID;
        Query query = entityManager.createQuery(userQuery);
        List<SeriesProfiles> data = query.getResultList();
        userData = data.get(0);
    }

    protected int calculateDotProduct(Series movie) {
        return userData.getAction() * movie.getAction() +
                userData.getAdventure() * movie.getAdventure() +
                userData.getAnimation() * movie.getAnimation() +
                userData.getWar() * movie.getWar() +
                userData.getComedy() * movie.getComedy() +
                userData.getCrime() * movie.getCrime() +
                userData.getDocumentary() * movie.getDocumentary() +
                userData.getDrama() * movie.getDrama() +
                userData.getFamily() * movie.getFamily() +
                userData.getFantasy() * movie.getFantasy() +
                userData.getHistory() * movie.getHistory() +
                userData.getHorror() * movie.getHorror() +
                userData.getKids() * movie.getKids() +
                userData.getMusic() * movie.getMusic() +
                userData.getMystery() * movie.getMystery() +
                userData.getNews() * movie.getNews() +
                userData.getReality() * movie.getReality() +
                userData.getRomance() * movie.getRomance() +
                userData.getScienceFiction() * movie.getScienceFiction() +
                userData.getSoap() * movie.getSoap() +
                userData.getTalk() * movie.getTalk() +
                userData.getThriller() * movie.getThriller() +
                userData.getTvMovie() * movie.getTvMovie() +
                userData.getWestern() * movie.getWestern();
    }

    protected void matchMovies() {
        for(Series movie : media) {
            results.put(
                    movie.getId(),
                    calculateDotProduct(movie)
            );
        }

        foundID = Collections.max(results.entrySet(), SortedMap.Entry.comparingByValue()).getKey();
    }
}
