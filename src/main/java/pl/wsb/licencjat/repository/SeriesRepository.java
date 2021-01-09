package pl.wsb.licencjat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.wsb.licencjat.model.database.Series;

import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {

    Optional<Series> findById(long id);

    @Query(value = "SELECT * FROM series ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Series findRandomSeries();

}
