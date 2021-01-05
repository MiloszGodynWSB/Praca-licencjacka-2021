package pl.wsb.licencjat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.licencjat.model.database.IgnoredMovies;

@Repository
public interface IgnoredMoviesRepository extends CrudRepository<IgnoredMovies, Integer> {
}
