package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.Movie;
import pl.wsb.licencjat.model.database.MoviesProfiles;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

public class GenreMatcherMovies extends GenreMatcher<Movie> {

    private MoviesProfiles userData;

    protected void getMovies() {
        selectMoviesQuery = "select c from " + "Movie" + " c where ";
        for (String genre: genres) {
            selectMoviesQuery =  selectMoviesQuery + "c." + genre + "=1 and ";
        }
        selectMoviesQuery = selectMoviesQuery.substring(0, selectMoviesQuery.length()-5);
        System.out.println(selectMoviesQuery);
        Query query = entityManager.createQuery(selectMoviesQuery);
        media = query.getResultList();
        SortedMap<Long, Long> a;
    }

    protected void getUserData() {
        String userQuery = "select c from " + "MoviesProfiles " + "c where c.userID=" + userID;
        System.out.println(userQuery);
        Query query = entityManager.createQuery(userQuery);
        List<MoviesProfiles> data = query.getResultList();
        userData = data.get(0);
    }

    protected int calculateDotProduct(Movie movie) {
        return userData.getAction() * movie.getAction() +
                userData.getAdventure() * movie.getAdventure() +
                userData.getAnimation() * movie.getAnimation() +
                userData.getDrama() * movie.getDrama() +
                userData.getComedy() * movie.getComedy() +
                userData.getCrime() * movie.getCrime() +
                userData.getDocumentary() * movie.getDocumentary() +
                userData.getFamily() * movie.getFamily() +
                userData.getFantasy() * movie.getFantasy() +
                userData.getHistory() * movie.getHistory() +
                userData.getHorror() * movie.getHorror() +
                userData.getMusic() * movie.getMusic() +
                userData.getMystery() * movie.getMystery() +
                userData.getRomance() * movie.getRomance() +
                userData.getScienceFiction() * movie.getScienceFiction() +
                userData.getThriller() * movie.getThriller() +
                userData.getTvMovie() * movie.getTvMovie() +
                userData.getWar() * movie.getWar() +
                userData.getWestern() * movie.getWestern();
    }

    protected void matchMovies() {
        for(Movie movie : media) {
            results.put(
                    movie.getId(),
                    calculateDotProduct(movie)
                    );
        }

        foundID = Collections.max(results.entrySet(), SortedMap.Entry.comparingByValue()).getKey();
    }
}
