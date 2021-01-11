package UserModule.UserModule.Exception;


/**
 * class RoleAlreadyExistsException
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @description used to handle the role already present exception
 */
public class RoleAlreadyExistsException extends RuntimeException {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Default Constructor 
	 * 
	 * @param none
	 * @Description Passing the Custom message to Super Class
	 */
	public RoleAlreadyExistsException() {
		super("Role Already Exists Why you are adding again");
	}

}
