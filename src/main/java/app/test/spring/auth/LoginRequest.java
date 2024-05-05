package app.test.spring.auth;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author houda
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4123922364007154340L;

	/**
	 * 
	 */
	private String login;
	
	/**
	 * 
	 */
	private String password;
}
