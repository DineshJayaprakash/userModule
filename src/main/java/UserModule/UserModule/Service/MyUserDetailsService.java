package UserModule.UserModule.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import UserModule.UserModule.Model.User;
import UserModule.UserModule.Repository.UserRepository;

/**
 * class MyUserDetailsService
 * 
 * @author dinesh
 * @created Date 10/12 
 * @description used to validate the userDetails
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

	/**
	 * UserRepository bean
	 */
	private final UserRepository userRepository; 
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param userRepository
	 * @description used for Dependency Injection
	 */
	@Autowired
	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * function loadUserByUsername
	 * 
	 * 
	 * @param username
	 * @return UserDetails
	 * @description used to list out the user details based on the loadUserByUsername
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		System.out.println(user.getUserName()+ " " + user.getPassword());
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
	}

}
