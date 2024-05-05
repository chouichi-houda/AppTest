package app.test.spring.service;

import java.util.Optional;

import app.test.spring.entity.User;

/**
 * @author houda
 *
 */
public interface SecurityService {
	
	Optional<User> getCurrentUser();

}
