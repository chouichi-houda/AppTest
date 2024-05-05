package app.test.spring.exception;

import org.springframework.http.HttpStatus;

/**
 * @author houda
 *
 */
public class BadRequestException extends RestException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2222903762087425896L;
	

	/**
	 * @param code
	 * @param message
	 */
	public BadRequestException(String code, String message) {
		super(HttpStatus.BAD_REQUEST, code, message);
	}

	/**
	 * @param code
	 * @param message
	 * @param data
	 */
	public BadRequestException(String code, String message, Object data) {
		super(HttpStatus.BAD_REQUEST, code, message, data);
	}

}
