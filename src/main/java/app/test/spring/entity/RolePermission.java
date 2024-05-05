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

import app.test.spring.enumeration.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author houda
 *
 */

@Entity
@Table(name = "ROLE_PERMISSION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9174210076517204527L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;

	/**
	 * Role.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public Role role;

	/**
	 * Permission.
	 */
	@Column(name = "PERMISSION", length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private PermissionEnum permission;

}
