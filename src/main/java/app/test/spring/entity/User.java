package app.test.spring.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

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
public class User implements Serializable{

	
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
	
	@Column(name = "LOGIN", length = 30, nullable = false)
	private String login;
	
	/**
	 * Password
	 */
	@Column(name = "PASSWORD", length = 255, nullable = false)
	private String password;
	
	/**
	 * Expiration date password.
	 */
	@Column(name = "EXPIRATION_DATE_PASSWORD")
	private LocalDate expirationPwdDate;

	/**
	 * Security code.
	 */
	@Column(name = "SECURITY_CODE", length = 10)
	private String securityCode;

	/**
	 * Security code generation date.
	 */
	@Column(name = "SECURITY_CODE_EXPIRATION_DATETIME")
	private LocalDateTime securityCodeExpirationDate;
	
	@OrderBy("id")
	@OneToMany(fetch = FetchType.LAZY, targetEntity = UserProfile.class, cascade = {CascadeType.ALL}, mappedBy = "user", orphanRemoval = true)
	private Set<UserProfile> profiles;
	
	

}
