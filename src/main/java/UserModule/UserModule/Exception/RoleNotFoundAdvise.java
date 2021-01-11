package UserModule.UserModule.Exception;

import javax.management.relation.RoleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



/**
 * class RoleNotFoundAdvise
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @description used to handle the exceptions globally
 */
@ControllerAdvice
public class RoleNotFoundAdvise {

	/**
	 * function roleNotFoundHandler 
	 * 
	 * @param ex
	 * @return message
	 * @description used to show the RoleNotFound exception message
	 */
	@ResponseBody
	@ExceptionHandler(RoleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String roleNotFoundHandler(RoleNotFoundException ex) {
		return ex.getMessage();
	}

	
	/**
	 * function roleAlreadyExistsHandler 
	 * 
	 * @param ex
	 * @return Message
	 * @description used to show the roleAlreadyExists exception message
	 */
	@ResponseBody
	@ExceptionHandler(RoleAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String roleAlreadyExistsHandler(RoleAlreadyExistsException ex) {
		return ex.getMessage();
	}
}
