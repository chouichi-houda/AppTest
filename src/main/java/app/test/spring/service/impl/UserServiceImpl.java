package app.test.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.test.spring.constant.ApplicationConstants.LoginErrorCodes;
import app.test.spring.constant.ApplicationConstants.UserErrorCodes;
import app.test.spring.entity.User;
import app.test.spring.exception.BadRequestException;
import app.test.spring.exception.DataNotFoundException;
import app.test.spring.repository.UserRepository;
import app.test.spring.service.SecurityService;
import app.test.spring.service.UserService;
import app.test.spring.utility.RegexUtils;

/**
 * @author houda
 *
 */

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	/**
	 * Inject {@link UserRepository} bean
	 */
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Inject {@link PasswordEncoder} bean
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Inject {@link SecurityService} bean
	 */
	@Autowired
	private SecurityService securityService;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(UserErrorCodes.USER_NOT_FOUND, ""));
		 
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new DataNotFoundException(UserErrorCodes.USER_NOT_FOUND, ""));
	    
	}

	@Override
	public User addUser(User user) {
		
		final boolean isUpdate = user.getId() != null;
		// check email is valid
		if (!RegexUtils.isValidEmail(user.getEmail())) {
			throw new BadRequestException(UserErrorCodes.USER_EMAIL_INVALID, "");
		}
				
		Optional<User> testUser = userRepository.findByEmailIgnoreCase(user.getEmail());
		if ((testUser.isPresent() && isUpdate && !testUser.get().getId().equals(user.getId()))
				|| (testUser.isPresent() && !isUpdate)) {
			throw new BadRequestException(UserErrorCodes.DUPLICATED_USER_EMAIL, "");
		}
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmailIgnoreCase(username)
				.orElseThrow(() -> new DataNotFoundException(LoginErrorCodes.USER_NOT_FOUND, ""));
		return user;
	}

}
