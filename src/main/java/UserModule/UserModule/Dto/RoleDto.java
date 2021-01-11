package UserModule.UserModule.Dto;


import java.io.Serializable;

import lombok.Data;



/**
 * class RoleDto
 * 
 * @created By Dinesh J
 * @created Date 04/20
 * @description RoleDto used to give the response to end users
 */
@Data
public class RoleDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	  * Id of the role
	  */
	private long roleId;    

	/**
	  * Name of the role
	  */
	private String roleName; 

}
