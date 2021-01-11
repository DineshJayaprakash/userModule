package UserModule.UserModule.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import UserModule.UserModule.Filter.JwtFilter;
import UserModule.UserModule.Service.MyUserDetailsService;



/**
 * class SecurityConfig
 * 
 * @created By Dinesh J
 * @created Date 09/12
 * @description used to implement the web security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * MyUserDetailsService bean
	 */
	private final MyUserDetailsService  myUserDetailsService;
	
	/**
	 * JwtFilter bean
	 */
	private final JwtFilter jwtFilter;
	
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param myUserDetailsService,jwtFilter
	 * @description used to add Dependency 
	 */
	@Autowired
	public SecurityConfig(MyUserDetailsService  myUserDetailsService,JwtFilter jwtFilter) {
		this.myUserDetailsService = myUserDetailsService; 
		this.jwtFilter = jwtFilter;
	}
	
	
	/**
	 * function configure
	 * 
	 * @param auth
	 * @return none
	 * @description used to add our own userDetailsService in spring security
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}
	
	/**
	 * function configure
	 * 
	 * @param http
	 * @return none
	 * @description used to allow the authenticate request alone from this module
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/users/authenticate")
		.permitAll().anyRequest().authenticated()
		.and().exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
	}
	
	
	
	/**
	 * function authenticationManager
	 * 
	 * @param none
	 * @return none
	 * @description used to override the custom AuthenticationManager class
	 */
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
