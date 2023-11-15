
import java.util.List;

//Visitor class to show the total number of groups
public class GroupTotalDisplayVisitor  {
	
	/***
	 * Count the total number of user groups in the provided list
	 * 
	 * @param group: the list that contains UserGroup objects
	 * @return: int that counts the total number of groups
	 */
	public int visit(List<UserGroupProfile > groupList) {
		int totalGroups = 0;
		for(UserGroupProfile  userGroup : groupList) {
			totalGroups++;
		}
		return totalGroups;
	}//end countGroups
}