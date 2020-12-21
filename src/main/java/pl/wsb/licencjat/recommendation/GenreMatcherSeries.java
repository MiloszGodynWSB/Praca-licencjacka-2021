package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.Series;
import pl.wsb.licencjat.model.database.SeriesProfiles;

import javax.persistence.Query;
import java.util.List;

public class GenreMatcherSeries extends GenreMatcher<Series> {

    private List<SeriesProfiles> userData;

    protected void getMovies() {
        selectMoviesQuery = "select c from " + "Series" + " c where ";
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
