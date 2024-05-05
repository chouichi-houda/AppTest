package app.test.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.test.spring.entity.User;
import app.test.spring.service.UserService;

/**
 * @author houda
 *
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	/**
	 * Inject {@link UserService} bean
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<User>>getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	/**
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		user = userService.addUser(user);
		return ResponseEntity.ok(user);
	}

	
	/**
	 * @param userId
	 * @return
	 */
	@GetMapping(value = "/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		User user = userService.getUserById(userId);
		return ResponseEntity.ok(user);
	}
	
	/**
	 * @param email
	 * @return
	 */
	@GetMapping(value = "/byEmail/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		User user = userService.getUserByEmail(email);
		return ResponseEntity.ok(user);
	}
}
