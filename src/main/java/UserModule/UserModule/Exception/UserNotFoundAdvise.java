package UserModule.UserModule.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


/**
 * class UserNotFoundAdvise
 * 
 * @created By Dinesh J
 * @crested Date 04/12
 * @description used handle the user related exceptions globally
 */
@ControllerAdvice
public class UserNotFoundAdvise {

	
	/**
	 * function roleNotFoundHandler 
	 * 
	 * @param ex
	 * @return Message
	 * @description used to handle the user not found exception
	 */
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String userNotFoundHandler(UserNotFoundException ex) {
		return ex.getMessage();

	}


}
