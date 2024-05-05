package app.test.spring.config;

import static app.test.spring.constant.SecurityConstant.OPTIONS_HTTP_METHOD;
import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
	
	public static final String SECRET = "app@secret.key";
	public static final long EXPIRATION_TIME = 864_000_000;// 10 jours
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	private JWTTokenProvider jwtTokenProvider;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
			response.setStatus(OK.value());
		} else {
//			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//			if(authorizationHeader == null || authorizationHeader.startsWith(TOKEN_PREFIX)) {
//				filterChain.doFilter(request, response);
//				return;
//			}
			response.setStatus(OK.value());
		}
		
	}

}
