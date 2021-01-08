package pl.wsb.licencjat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.licencjat.model.database.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
