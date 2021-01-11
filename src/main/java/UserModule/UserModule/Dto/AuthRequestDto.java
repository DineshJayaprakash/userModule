package UserModule.UserModule.Dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * class AuthRequestDto
 * 
 * @created By Dinesh J
 * @created Date 09/12
 * @description used for authenticate the user
 */
@Data
@NoArgsConstructor
public class AuthRequestDto implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;


	/**
	 *  name of the User
	 */
	private String userName;
	
	
	/**
	 *  password of the User
	 */
	private String password;
	

}
