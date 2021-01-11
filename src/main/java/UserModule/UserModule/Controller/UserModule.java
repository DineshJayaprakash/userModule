package UserModule.UserModule.Controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import UserModule.UserModule.Dto.AuthRequestDto;
import UserModule.UserModule.Dto.RoleDto;
import UserModule.UserModule.Dto.UserDto;
import UserModule.UserModule.Service.UserModuleService;




/**
 * class UserModule
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @description used to perform end point operations
 */

@RestController
@RequestMapping("users")
public class UserModule {

    /**
     * UserModuleService Bean
     */
	private final UserModuleService userModuleService;  


	/**
	 * Constructor Parameterized
	 * 
	 * @param userModuleService
	 * @Description Dependency Injection
	 */
	@Autowired
	public UserModule(UserModuleService userModuleService) {
		this.userModuleService = userModuleService;
	}


	/**
	 * function generateToken
	 * 
	 * @param authRequestDto
	 * @return generated token
	 * @description used to generate the token for validated user
	 */
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequestDto authRequestDto) throws Exception {
			return userModuleService.generateToken(authRequestDto);
	}
	
	/**
	 * function getListOfUsers
	 * 
	 * @param none
	 * @return List Of Users
	 * @description GetMapping used to list out all users from db
	 */
	@Cacheable("users")
	@GetMapping("")
	public List<UserDto> getListOfUsers() {
		return  userModuleService.getAllUsers();
	}
	
	/**
	 * function addUser
	 * 
	 * @param myUser
	 * @return saved User
	 * @description PostMapping used to add user to db
	 */
	@PostMapping("")
	public UserDto addUser(@RequestBody UserDto myUser) {
		return userModuleService.addUser(myUser);
	}

	/**
	 * function addRole
	 * 
	 * @param myRole
	 * @return saved Role
	 * @description PostMapping used to add role to db
	 */
	@PostMapping("/addRole")
	public RoleDto addRole(@RequestBody RoleDto myRole) {
		return userModuleService.addRole(myRole);
	}

	/**
	 * function getUserById
	 * 
	 * @param id
	 * @return User based on given id
	 * @description GetMapping used to get a user details from db
	 */
	@Cacheable(key = "#id", value="user")
	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable Long id) {
		return userModuleService.getUserById(id);
	}

	/**
	 * function updateUserById
	 * 
	 * @param id
	 * @return updated User based on given id
	 * @description PutMapping used to update a user details in db
	 */
	@CachePut(key="#id",value="user")
	@PutMapping("/{id}")
	public UserDto updateUserById(@RequestBody UserDto myUser, @PathVariable Long id) {
		return userModuleService.updateUserById(myUser, id);
	}

	/**
	 * function deleteUserById
	 * 
	 * @param id
	 * @return delete User based on given id
	 * @description DeleteMapping used to delete a user details in db
	 */
	@CacheEvict(key="id",value="user")
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {
		 userModuleService.deleteUserWithRole(id);
	}

	
	/**
	 * function getPaginationWithSorting
	 * 
	 * @param pageNo,pageSize,sortBy
	 * @return List Of User Based on Pagination and Sorting
	 * @description GetMapping used to filter and sort the records from db
	 */
	@GetMapping("/{pageNo}/{pageSize}/{sortBy}")
	public List<UserDto> getPaginationWithSorting(@PathVariable int pageNo, @PathVariable int pageSize, @PathVariable String sortBy) {
		return userModuleService.getPaginationWithSorting(pageNo, pageSize, sortBy);
	}
	
	/**
	 * function getUserId
	 * 
	 * @param none
	 * @return hard coded userid to interact feignClient
	 * @description GetMapping used to interact with other modules
	 */
	@GetMapping("/getUserId")
	public long getUserId() {
		return 1;
	}
	
	
	/**
	 * function findByUsername
	 * 
	 * @param userName
	 * @return List of User 
	 * @description GetMapping used to get the list of users based on the search
	 */
	@GetMapping("findByUserName/{userName}")
	public List<UserDto> findByUserName(@PathVariable String userName) {
		return userModuleService.findByUserName(userName);
	}
	
	/**
	 * function findByRolename
	 * 
	 * @param roleName
	 * @return List of User 
	 * @description GetMapping used to get the list of users based on the search
	 */
	@GetMapping("findByRoleName/{roleName}")
	public List<RoleDto> findByRoleName(@PathVariable String roleName) {
		return userModuleService.findByRoleName(roleName);
	}



}
