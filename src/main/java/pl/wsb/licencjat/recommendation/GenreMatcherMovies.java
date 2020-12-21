package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.Movie;
import pl.wsb.licencjat.model.database.MoviesProfiles;
import pl.wsb.licencjat.recommendation.GenreMatcher;

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
        return userData.Action * movie.Action +
                userData.Adventure * movie.Adventure +
                userData.Drama * movie.Drama +
                userData.Comedy * movie.Comedy +
                userData.Crime * movie.Crime +
                userData.Documentary * movie.Documentary +
                userData.Family * movie.Documentary +
                userData.Fantasy * movie.Fantasy +
                userData.History * movie.History +
                userData.Horror * movie.Horror +
                userData.Music * movie.Music +
                userData.Mystery * movie.Mystery +
                userData.Romance * movie.Romance +
                userData.ScienceFiction * movie.ScienceFiction +
                userData.thriller * movie.thriller +
                userData.TvMovie * movie.TvMovie +
                userData.War * movie.War +
                userData.Western * movie.Western;
    }

    protected void matchMovies() {
        for(Movie movie : media) {
            System.out.println(movie);
            results.put(
                    movie.id,
                    calculateDotProduct(movie)
                    );
        }
        foundID = Collections.max(results.entrySet(), SortedMap.Entry.comparingByValue()).getKey();
    }
}
