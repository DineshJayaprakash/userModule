package UserModule.UserModule.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;




/**
 * class Role
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @Description used to add role details into Db
 */
@Entity
@Data
@Table(name="roles")
public class Role {


	/**
	 * Id of the role
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleid")
	private long roleId;

	/**
	 * name of the role
	 */
	@Column(name = "rolename")
	private String roleName;

}
