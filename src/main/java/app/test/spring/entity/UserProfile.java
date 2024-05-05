package app.test.spring.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import app.test.spring.enumeration.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author houda
 *
 */
@Entity
@Table(name = "USER_PROFILE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4233050555304964633L;

	/**
	 * Profile id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	/**
	 * Profile type.
	 */
	@Column(name = "USER_TYPE", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private UserTypeEnum type;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "USER_ID", nullable = false)
	public User user;
	
	/**
	 * Profile name.
	 */
	@Column(name = "NAME", length = 150)
	private String name;

	/**
	 * Profile role.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	private Role role;

}
