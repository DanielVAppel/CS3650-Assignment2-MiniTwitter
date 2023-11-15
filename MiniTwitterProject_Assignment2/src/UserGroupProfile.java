import java.util.ArrayList;
import java.util.List;
//User Group class implementing System Entry

public class UserGroupProfile implements SysEntry {
	
	private List<SysEntry> entryList  = new ArrayList<SysEntry>();
	private List<User> groupUsers = new ArrayList<User>();
	private List<UserGroupProfile > subGroups = new ArrayList<UserGroupProfile >();
	private String groupID;
	
	//constructor to take in a string whenever a new
	//UserGroup object is created
	public UserGroupProfile (String name) {
		groupID = name;
	}//end UserGroup
	
	
	//adds a user into the group
	//if the user name is already in the group, a message is printed instead
	public void addUser(User name) {
		if(groupUsers.contains(name)) {
			System.out.println("This user is already in this group");
		}
		else {
			groupUsers.add(name);
		}
		
	}//end addUser
	
	
	//adds a subgroup into the group
	public void addGroup(UserGroupProfile  name) {
		subGroups.add(name);
	}//end addGroup
	
	
	//gets the displayName of a group
	public String getID() {
		return groupID;
	}//end getID

	
	//sets the display name for a group
	public void setID(String id) {
		this.groupID = id;
	}//end setID



	//getter for subGroups
	public List<UserGroupProfile > getGroup(){
		return subGroups;
	}//end getGroup
	
	//getter for groupUsers
	public List<User> getUserList(){
		return groupUsers;
	}//end getUserList


	@Override
	public int accept(GroupTotalDisplayVisitor visitor) {
		int total = visitor.visit(this.getGroup());
		return total;
	}//end accept


	@Override
	public int accept(MessageTotalDisplayVisitor  visitor) {
		int total = 0;
		for(User user: this.getUserList()) {
			total += visitor.visit(user.getMessageCounter());
		}
		return total;
		
	}


	@Override
	public float accept(PositivePercentDisplayVisitor visitor, List<String> list) {
		float total = 0;
		for(User user: this.getUserList()) {
			total += visitor.visit(user.getFeed(), list);
		}
		return  total;
		
	}//end accept


	@Override
	public int accept(UserTotalDisplayVisitor visitor) {
		int total = visitor.countUsers(this.getUserList());
		return total;
	}//end accept

}