package app.test.spring.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import app.test.spring.entity.User;
import app.test.spring.repository.UserRepository;
import app.test.spring.service.SecurityService;

/**
 * @author houda
 *
 */

@Service
public class SecurityServiceImpl implements SecurityService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return userRepository.findByEmailIgnoreCase(username);
	}

}
