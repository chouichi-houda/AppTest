package app.test.spring.auth;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author houda
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 128774377799642244L;
	
	private String jwt;

}
