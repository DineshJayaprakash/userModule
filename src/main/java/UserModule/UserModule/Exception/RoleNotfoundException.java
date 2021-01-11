package UserModule.UserModule.Exception;


/**
 * class RoleNotfoundException
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @description used to handle the role not found exception
 */
public class RoleNotfoundException extends RuntimeException {

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
	public RoleNotfoundException() {
		super("Could not find the Role you given, Kindly Create the Role you have mentioned First");
	}

}
