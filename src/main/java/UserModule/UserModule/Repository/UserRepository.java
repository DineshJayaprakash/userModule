package UserModule.UserModule.Repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import UserModule.UserModule.Model.User;


/**
 * interface UserRepository
 * 
 * @created By Dinesh J
 * @created Date 04/12
 * @Description used to add user details into Db
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>,PagingAndSortingRepository<User, Long> {
	
	/**
	 * function searchByUserName 
	 * 
	 * @param userName
	 * @return  List of User
	 * @description used to fetch all user names by users search
	 */
	@Transactional
	@Query(nativeQuery = true , value = "select * from users where username LIKE %?1%")
	List<User> searchByUserName(String userName);
	
	/**
	 * function findByUserName 
	 * 
	 * @param userName
	 * @return  User Details
	 * @description used to fetch User details by username
	 */
	User findByUserName(String username);

}
