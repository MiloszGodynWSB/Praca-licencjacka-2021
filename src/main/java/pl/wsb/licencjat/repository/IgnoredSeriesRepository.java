package pl.wsb.licencjat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.licencjat.model.database.IgnoredSeries;

@Repository
public interface IgnoredSeriesRepository extends CrudRepository<IgnoredSeries, Integer> {
}
