package pl.wsb.licencjat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.wsb.licencjat.model.database.Movie;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findById(long id);

    @Query(value = "SELECT * FROM movies ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Movie findRandomMovie();
}
