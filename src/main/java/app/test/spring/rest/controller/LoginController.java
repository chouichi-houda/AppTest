package app.test.spring.rest.controller;

import java.util.Date;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.test.spring.auth.LoginRequest;
import app.test.spring.auth.LoginResponse;
import app.test.spring.config.JWTAuthorizationFilter;
import app.test.spring.constant.ApplicationConstants.LoginErrorCodes;
import app.test.spring.entity.User;
import app.test.spring.exception.BadRequestException;
import app.test.spring.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author houda
 *
 */
@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "http://localhost:3030")
public class LoginController {
	
	public static final long EXPIRATION_TIME = 864_000_000;// 10 jours
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest,
			HttpServletRequest request) {
		User userPrincipal = null;
		if (StringUtils.isEmpty(loginRequest.getLogin())) {
			throw new BadRequestException(LoginErrorCodes.MISSING_LOGIN, "");
		}
		if (StringUtils.isEmpty(loginRequest.getPassword())) {
			throw new BadRequestException(LoginErrorCodes.MISSING_PASSWORD, "");
		}

		String jwt = null;
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
			 userPrincipal = (User) authentication.getPrincipal();

			if (userPrincipal != null) {
				User user = userRepository.findByEmailIgnoreCase(loginRequest.getLogin())
						.get();
				jwt = generateToken(userPrincipal, user);
			}
		} catch (BadCredentialsException e) {
			throw new BadRequestException(LoginErrorCodes.BAD_CREDENTIALS, "");
		} catch (DisabledException e) {
			throw new BadRequestException(LoginErrorCodes.ACCOUNT_DISABLED, "");
		} catch (CredentialsExpiredException e) {
			User user = userRepository.findByEmailIgnoreCase(loginRequest.getLogin())
					.get();
		jwt  = generateTokenPwdExpired( user);
		} catch (AuthenticationException e) {
			throw new BadRequestException(LoginErrorCodes.LOGIN_FAILED, "");
		}

		return ResponseEntity.ok().body(new LoginResponse(jwt));
	}

	private String generateToken(User userPrincipal, User user) {
		return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, JWTAuthorizationFilter.SECRET).claim("email", user.getEmail())
				.claim("authorities",
						userPrincipal.getAuthorities().stream().map(authority -> authority.getAuthority())
								.collect(Collectors.toList()))
				.claim("lastname", user.getLastName()).claim("firstname", user.getFirstName()).compact();
	}
	
	private String generateTokenPwdExpired(User user) {
		return Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, JWTAuthorizationFilter.SECRET).claim("email", user.getEmail())
				.claim("lastname", user.getLastName()).claim("firstname", user.getFirstName())
				.claim("passwordExpired", true).compact();
	}
}
