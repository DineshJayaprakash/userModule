package UserModule.UserModule.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;


/**
 * class User
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @Description used to add the user details into Db
 */
@Entity
@Data
@Table(name="users")
public class User {

	/**
	 * Id of the user
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="userid")
	private long userId;

	
	/**
	 * name of the user
	 */
	@Column(name="username")
	private String userName;

	/**
	 * password of the user
	 */
	@Column(name="password")
	private String password;

	/**
	 * role of the user
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userrole", referencedColumnName="roleid")
	private Role userRole;


}
