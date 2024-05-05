package app.test.spring.exception;

import org.springframework.http.HttpStatus;

/**
 * @author houda
 *
 */
public class DataNotFoundException extends RestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2853321774805559718L;

	/**
	 * @param code
	 * @param message
	 */
	public DataNotFoundException(String code, String message) {
		super(HttpStatus.NOT_FOUND, code, message);
	}

}
