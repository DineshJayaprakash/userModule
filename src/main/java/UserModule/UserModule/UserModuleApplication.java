package UserModule.UserModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * class UserModuleApplication
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @description used to execute user module 
 */
@SpringBootApplication
@EnableSwagger2
public class UserModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserModuleApplication.class, args);
	}

}
