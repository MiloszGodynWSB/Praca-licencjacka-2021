package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.MoviesProfiles;
import pl.wsb.licencjat.model.database.Series;
import pl.wsb.licencjat.model.database.SeriesProfiles;

import javax.persistence.Query;
import java.util.List;

public class GenreMatcherSeries extends GenreMatcher<Series> {

    private SeriesProfiles userData;

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
        String userQuery = "select c from" + "SeriesProfiles" + "c where c.userID=" + userID;
        Query query = entityManager.createQuery(userQuery);
        List<SeriesProfiles> data = query.getResultList();
        userData = data.get(0);
    }

    protected int calculateDotProduct(Series movie) {
        return 0;
    }

    protected void matchMovies() {

    }
}
