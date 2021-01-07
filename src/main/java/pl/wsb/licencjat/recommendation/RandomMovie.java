package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.Movie;

import javax.persistence.Query;
import java.util.Random;

public class RandomMovie extends RandomMedia<Movie> {

    public RandomMovie(int userID) {
        super(userID);
        selectMediaQuery = "select c from " + "Movie" + " c where ";
    }

    protected void getMedia() {
        String selectMoviesQuery = selectMediaQuery;
        selectMoviesQuery = selectMoviesQuery + "c.id not in " +
                "(Select d.movieID from IgnoredMovies d where d.id=" + userID + ")";
        System.out.println(selectMoviesQuery);
        Query query = entityManager.createQuery(selectMoviesQuery);
        mediaList = query.getResultList();
    }

    public Long getRandomMedia() {
        getMedia();
        Random random = new Random();
        int range = random.nextInt(mediaList.size());
        foundID = mediaList.get(range).getId();
        return foundID;
    }
}
