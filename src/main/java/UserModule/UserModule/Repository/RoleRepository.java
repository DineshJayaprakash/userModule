package UserModule.UserModule.Repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import UserModule.UserModule.Model.Role;




/**
 * interface RoleRepository
 * 
 * @created By Dinesh J
 * @create Date 04/12
 * @description used to add the roles into Db
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * function getAllRoles 
	 * 
	 * @param none
	 * @return  List of role name
	 * @description used to fetch all role names from roles table
	 */
	@Transactional
	@Query(nativeQuery = true , value = "select rolename from roles")
	List<String> getAllRoles();
	
	
	/**
	 * function findByRoleName 
	 * 
	 * @param roleName
	 * @return  List of role name
	 * @description used to fetch all role names by users search
	 */
	@Transactional
	@Query(nativeQuery = true , value = "select * from roles where rolename LIKE %?1%")
	List<Role> findByRoleName(String roleName);

}
