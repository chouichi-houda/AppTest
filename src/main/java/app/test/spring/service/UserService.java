package app.test.spring.service;

import java.util.List;

import app.test.spring.entity.User;

/**
 * @author houda
 *
 */
public interface UserService {
	
	/**
	 * @return
	 */
	List<User> getAllUsers();
	
	/**
	 * @param userId
	 * @return
	 */
	User getUserById(Long userId);
	
	/**
	 * @param email
	 * @return
	 */
	User getUserByEmail(String email);
	
	/**
	 * @param user
	 * @return
	 */
	User addUser(User user);
	
	//void resetPassword(ResetPassword resetpasswordDto, Errors errors)

}
