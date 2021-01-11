package UserModule.UserModule.Service;

import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import UserModule.UserModule.Aop.TrackExecutionTime;
import UserModule.UserModule.Dto.AuthRequestDto;
import UserModule.UserModule.Dto.RoleDto;
import UserModule.UserModule.Dto.UserDto;
import UserModule.UserModule.Exception.RoleAlreadyExistsException;
import UserModule.UserModule.Exception.RoleNotfoundException;
import UserModule.UserModule.Exception.UserNotFoundException;
import UserModule.UserModule.Model.Role;
import UserModule.UserModule.Model.User;
import UserModule.UserModule.Repository.RoleRepository;
import UserModule.UserModule.Repository.UserRepository;
import UserModule.UserModule.Util.JwtUtil;



/**
 * class UserModuleServiceImpl
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @description used to implement all the usermodule service crud operations from userModuleService
 */
@Service
public class UserModuleServiceImpl implements UserModuleService{


	private final UserRepository userRepository;  


	private final RoleRepository roleRepository;    
	

	private final ModelMapper modelMapper;      
	

	private final AuthenticationManager authenticationManager;
	

	private final JwtUtil jwtUtil;
	

	@Autowired
	public UserModuleServiceImpl(UserRepository userRepository,RoleRepository roleRepository, ModelMapper modelMapper, AuthenticationManager authenticationManager,JwtUtil jwtUtil){
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	

	@TrackExecutionTime
	public List<UserDto> getAllUsers(){
		List<User> user = userRepository.findAll();
		Type listType = new TypeToken<List<UserDto>>(){}.getType();
		List<UserDto> myUserDto = modelMapper.map(user, listType);
		return  myUserDto;
	}


	@TrackExecutionTime
	public RoleDto addRole(RoleDto myRole){
		List<String> roles = roleRepository.getAllRoles();
		String myRolename = myRole.getRoleName();
		if(!(roles.contains(myRolename)))
		{
			throw new RoleAlreadyExistsException();
		}
		Role MyRole = modelMapper.map(myRole, Role.class);
		Role roleEntity = roleRepository.save(MyRole);
		RoleDto roleDto = modelMapper.map(roleEntity, RoleDto.class);
		return roleDto;
	}

	@TrackExecutionTime
	public UserDto addUser(UserDto myUser){
		List<String> roles = roleRepository.getAllRoles();
		String myRole = myUser.getUserRole().getRoleName();
		if(!(roles.contains(myRole)))
		{
			throw new RoleNotfoundException();
		}
		User MyUser = modelMapper.map(myUser, User.class);
		User usrEntity = userRepository.save(MyUser);
		UserDto myUserDto = modelMapper.map(usrEntity, UserDto.class);
		return myUserDto;
	}

	@TrackExecutionTime
	public UserDto getUserById(Long id){
		User usrEntity = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
		UserDto myUserDto = modelMapper.map(usrEntity, UserDto.class);
		return myUserDto;
	}


	@TrackExecutionTime
	public UserDto updateUserById(UserDto myUser, Long id){
		User MyUser = modelMapper.map(myUser,User.class);
		User usrEntity = userRepository.findById(id).map(user->{user.setUserName(MyUser.getUserName());
		user.setPassword(MyUser.getPassword());
		List<String> roles = roleRepository.getAllRoles();
		String myRole = MyUser.getUserRole().getRoleName();
		if(!(roles.contains(myRole)))
		{
			throw new RoleNotfoundException();
		}
		user.setUserRole(MyUser.getUserRole());
		return userRepository.save(user);}).orElseGet(()->{MyUser.setUserId(id);
		return userRepository.save(MyUser);});
		
		UserDto myUserDto = modelMapper.map(usrEntity, UserDto.class);
		return  myUserDto;
	}

	@TrackExecutionTime
	public void deleteUserWithRole(Long id){
		try{
			userRepository.deleteById(id);
		}
		catch(Exception e){
			throw new UserNotFoundException(id);
		}

	}


	@TrackExecutionTime
	public List<UserDto> getPaginationWithSorting(int pageNo, int pageSize, String sortBy) {
		Pageable paging =  PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
		Page<User> pagedResult = userRepository.findAll(paging);
		List<User> user = pagedResult.toList();
		Type listType = new TypeToken<List<UserDto>>(){}.getType();
		List<UserDto> myUserDto = modelMapper.map(user, listType);
		return myUserDto;
	}


	@TrackExecutionTime
	public List<UserDto> findByUserName(String userName) {
		List<User> myUser = userRepository.searchByUserName(userName);
		Type listType = new TypeToken<List<UserDto>>(){}.getType();
		List<UserDto> optUserDto = modelMapper.map(myUser,listType);
		return optUserDto;
	}

	@TrackExecutionTime
	public List<RoleDto> findByRoleName(String userName) {
		List<Role> myUser = roleRepository.findByRoleName(userName);
		Type listType = new TypeToken<List<RoleDto>>(){}.getType();
		List<RoleDto> optUserDto = modelMapper.map(myUser,listType);
		return optUserDto;
	}


	@TrackExecutionTime
	@Override
	public String generateToken(AuthRequestDto authRequestDto) throws Exception {
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUserName(),authRequestDto.getPassword()));
		}
		catch(Exception ex) {
			throw new Exception("inavalid username/password");
		}
		
		return jwtUtil.generateToken(authRequestDto.getUserName());
	}

}
