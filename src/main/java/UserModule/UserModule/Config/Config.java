package UserModule.UserModule.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



/**
 * class Config
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @description used to add singleton bean in our application
 */
@SuppressWarnings("deprecation")
@Configuration
public class Config {
	
	
	/**
	 * function modelMapper
	 * 
	 * @param none
	 * @return ModelMapper bean
	 * @description used to perform utility operation
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
	/**
	 * function productApi
	 * 
	 * @param none
	 * @return new Docket
	 * @description used for swagger documentation
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("UserModule.UserModule")).build();
	}
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
	

}
