package app.test.spring.constant;

/**
 * @author houda
 *
 */
public abstract class ApplicationConstants {
	
	public static final int NAMES_LENGTH = 150;
	
	public abstract class UserErrorCodes {
		
		public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
		public static final String USER_EMAIL_INVALID = "USER_EMAIL_INVALID";
		public static final String DUPLICATED_USER_EMAIL = "DUPLICATED_USER_EMAIL";
		public static final String DUPLICATED_USER_LOGIN = "DUPLICATED_USER_LOGIN";
		
		
	}

	public abstract class LoginErrorCodes {
		public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
		public static final String MISSING_LOGIN = "MISSING_LOGIN";
		public static final String MISSING_PASSWORD = "MISSING_PASSWORD";
		public static final String BAD_CREDENTIALS = "BAD_CREDENTIALS";
		public static final String ACCOUNT_DISABLED = "ACCOUNT_DISABLED";
		public static final String LOGIN_FAILED = "LOGIN_FAILED";
		
	}
}
