package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.Movie;
import pl.wsb.licencjat.model.database.MoviesProfiles;
import pl.wsb.licencjat.recommendation.GenreMatcher;

import javax.persistence.Query;
import java.util.List;

public class GenreMatcherMovies extends GenreMatcher<Movie> {

    private List<MoviesProfiles> userData;

    protected void getMovies() {
        selectMoviesQuery = "select c from " + "Movie" + " c where ";
        for (String genre: genres) {
            selectMoviesQuery =  selectMoviesQuery + "c." + genre + "=1 and ";
        }
        selectMoviesQuery = selectMoviesQuery.substring(0, selectMoviesQuery.length()-5);
        System.out.println(selectMoviesQuery);
        Query query = entityManager.createQuery(selectMoviesQuery);
        media = query.getResultList();
    }

    protected void getUserData() {

    }

    protected  void calculateDotProducts() {

    }
}
