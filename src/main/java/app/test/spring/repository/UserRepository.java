package app.test.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.test.spring.entity.User;


/**
 * @author houda
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLoginIgnoreCase(String login);
	Optional<User> findByEmailIgnoreCase(String email);

}
