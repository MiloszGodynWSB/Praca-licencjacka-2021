package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.Series;

import javax.persistence.Query;

public class GenreMatcherSeries extends GenreMatcher<Series> {

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
}
