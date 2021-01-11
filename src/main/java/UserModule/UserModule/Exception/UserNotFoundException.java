package UserModule.UserModule.Exception;


/**
 * class UserNotFoundException
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @description used to handle the user not found exception
 */
public class UserNotFoundException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterized Constructor 
	 * 
	 * @param id
	 * @Description Passing the Custom message to Super Class
	 */
	public UserNotFoundException(Long id) {
		super("Could not find User " + id);
	}

}
