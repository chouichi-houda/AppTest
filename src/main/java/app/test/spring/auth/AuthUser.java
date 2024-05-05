package app.test.spring.auth;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import app.test.spring.entity.User;
import lombok.Getter;

/**
 * @author houda
 *
 */
public class AuthUser implements UserDetails {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9046054230545132865L;
	
	@Getter
	private final User user;

	public AuthUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return LocalDate.now(ZoneId.systemDefault()).isBefore(user.getExpirationPwdDate());
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
