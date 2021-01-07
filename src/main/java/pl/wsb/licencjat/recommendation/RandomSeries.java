package pl.wsb.licencjat.recommendation;

import pl.wsb.licencjat.model.database.Series;

import javax.persistence.Query;
import java.util.Random;

public class RandomSeries extends RandomMedia<Series> {

    public RandomSeries(int userID) {
        super(userID);
        selectMediaQuery = "select c from " + "Series" + " c where ";
    }

    protected void getMedia() {
        String selectSeriesQuery = selectMediaQuery;
        selectSeriesQuery = selectSeriesQuery + "c.id not in " +
                "(Select d.movieID from IgnoredSeries d where d.id=" + userID + ")";
        System.out.println(selectSeriesQuery);
        Query query = entityManager.createQuery(selectSeriesQuery);
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
