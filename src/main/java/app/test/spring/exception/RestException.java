package app.test.spring.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author houda
 *
 */
@Getter
@AllArgsConstructor
public class RestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7997879253712425393L;

	/**
	 * 
	 */
	private final HttpStatus httpStatus;
	
	/**
	 * 
	 */
	private final String code;
	
	/**
	 * 
	 */
	private final String message;
	
	/**
	 * 
	 */
	private final transient Object data;

	/**
	 * @param httpStatus
	 * @param code
	 * @param message
	 */
	public RestException(HttpStatus httpStatus, String code, String message) {
		super(message);
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
		this.data = null;
	}



}
