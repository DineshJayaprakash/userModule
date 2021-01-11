package UserModule.UserModule.Service;

import java.util.List;

import UserModule.UserModule.Dto.AuthRequestDto;
import UserModule.UserModule.Dto.RoleDto;
import UserModule.UserModule.Dto.UserDto;



/**
 * interface UserModuleService
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @decription used to perform user module crud operations
 * version : 0420
 */
public interface UserModuleService {

	/**
	 * function getAllUsers
	 * 
	 * @param none
	 * @return Users List
	 * @description used to get all the users details to Db
	 */
	public List<UserDto> getAllUsers();
	
	
	/**
	 * function addRole
	 * 
	 * @param myRole
	 * @return saved Role
	 * @description used to add the role details to Db
	 */
	public RoleDto addRole(RoleDto myRole);
	
	
	/**
	 * function addUser
	 * 
	 * @param myUser
	 * @throws RoleNotfoundException
	 * @return saved User
	 * @description used to add a user detail to Db
	 */
	public UserDto addUser(UserDto myUser);
	
	
	/**
	 * function getUserById
	 * 
	 * @param id
	 * @return User
	 * @description used to get a user detail based on id
	 */
	public UserDto getUserById(Long id);
	
	
	/**
	 * function updateUserById
	 * 
	 * @param myUser,id
	 * @throws RoleNotfoundException
	 * @return User
	 * @description update a User based on given id into db
	 */
	public UserDto updateUserById(UserDto myUser, Long id);

	/**
	 * function deleteUserById
	 * 
	 * @param id
	 * @throws UserNotFoundException
	 * @return none
	 * @description delete User based on given id in our db
	 */
	public void deleteUserWithRole(Long id);

	/**
	 * function getPaginationWithSorting
	 * 
	 * @param pageNo,pageSize,sortBy
	 * @return List of Users
	 * @description used to display list of users Based on pagination and sorting
	 */
	public List<UserDto> getPaginationWithSorting(int pageNo, int pageSize, String sortBy);
	
	/**
	 * function findByUsername
	 * 
	 * @param userName
	 * @return List of Users 
	 * @description used to display list of users Based on user's search
	 */
	public List<UserDto> findByUserName(String userName);
	
	
	/**
	 * function findByRoleName
	 * 
	 * @param roleName
	 * @return List of roleName 
	 * @description used to display list of roles Based on roles's search
	 */
	public List<RoleDto> findByRoleName(String roleName);
	
	/**
	 * function generateToken
	 * 
	 * @param authRequestDto
	 * @return jwtToken
	 * @throws Exception 
	 * @description used to generate authentication token for user
	 */
	public String generateToken(AuthRequestDto authRequestDto) throws Exception;

}
