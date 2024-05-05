package app.test.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import app.test.spring.constant.ApplicationConstants;
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
@Table(name = "ROLE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4756616948298829172L;


	/**
	 * Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	/**
	 * Name
	 */
	@Column(name = "NAME", length = ApplicationConstants.NAMES_LENGTH, nullable = false)
	private String name;
	
	/**
	 * Type
	 */
	@Column(name = "TYPE", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private UserTypeEnum type;
	
	
	/**
	 * Role permissions
	 */
	@OrderBy("id")
	@OneToMany(fetch = FetchType.LAZY, targetEntity = RolePermission.class, orphanRemoval = true, mappedBy = "role", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	private Set<RolePermission> permissions;
}
