package app.test.spring.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import app.test.spring.enumeration.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author houda
 *
 */
@Entity
@Table(name = "USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, UserDetails{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7414157352453684554L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, updatable = false)
	private Long id;
	
	/**
	 * First Name
	 */
	@Column(name = "FIRSTNAME", length = 150)
	private String firstName;
	
	
	/**
	 * Last Name
	 */
	@Column(name = "LASTNAME", length = 150)
	private String lastName;
	
	/**
	 * Email
	 */
	@Column(name = "EMAIL", length = 150, nullable = false)
	private String email;
	
	/**
	 * Password
	 */
	@Column(name = "PASSWORD", length = 255, nullable = false)
	private String password;
	
	/**
	 * user role
	 */
	@Column(name = "USER_ROLE", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	@OneToOne
	private UserProfile profile;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

}
