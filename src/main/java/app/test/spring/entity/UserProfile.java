package app.test.spring.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	

	@OneToOne
	public User user;
	
	/**
	 * Profile name.
	 */
	@Column(name = "NAME", length = 150)
	private String name;

}
