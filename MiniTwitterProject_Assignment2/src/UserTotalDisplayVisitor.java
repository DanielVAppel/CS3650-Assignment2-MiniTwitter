
import java.util.List;
//Visitor class to show the total number of users
public class UserTotalDisplayVisitor  {
	/**
	 * Count the total number of user objects in the provided list
	 * 
	 * @param userList: list of User objects
	 * @return: int that counts the total number of users
	 */
	public int countUsers(List<User> userList) {
		int totalUsers = 0;
		for(User user:  userList) {
			totalUsers++;
		}
		return totalUsers;
	}
}