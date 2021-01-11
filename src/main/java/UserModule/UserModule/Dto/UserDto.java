package UserModule.UserModule.Dto;



import java.io.Serializable;

import UserModule.UserModule.Model.Role;

import lombok.Data;

/**
 * class UserDto
 * 
 * @created By Dinesh J
 * @created date 04/20
 * @description UserDto used to give the response to the end user
 */

@Data
public class UserDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	  * Id of the user
	  */
	private long userId;  

	/**
	  * name of the user
	  */
	private String userName; 

	/**
	  * password of the user
	  */
	private String password;  

	/**
	  * role of the user
	  */
	private Role userRole; 

}
