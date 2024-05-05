package app.test.spring.utility;

import java.util.regex.Pattern;

/**
 * @author houda
 *
 */
public class RegexUtils {
	
	/**
	 * Email regular expression
	 */
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	
	/**
	 * Validates a string is valid email 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public static boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		if (email == null) {
			return false;
		}
		return pattern.matcher(email).matches();
	}

}
