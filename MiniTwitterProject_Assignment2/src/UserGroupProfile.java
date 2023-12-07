import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
//User Group class implementing System Entry

public class UserGroupProfile implements SysEntry {
	
    private long creationTime;
	private List<SysEntry> entryList  = new ArrayList<SysEntry>();
	private List<User> groupUsers = new ArrayList<User>();
	private List<UserGroupProfile > subGroups = new ArrayList<UserGroupProfile >();
	private String groupID;
	
	//constructor to take in a string whenever a new
	//UserGroup object is created
	public UserGroupProfile (String name) {
		groupID = name;
		this.creationTime = System.currentTimeMillis();
	    setCreationTime(System.currentTimeMillis());
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


	public long getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
	
	public User findLastUpdatedUser() {
	    User lastUpdatedUser = null;
	    long maxLastUpdateTime = Long.MIN_VALUE;

	    for (User user : groupUsers) {
	        if (user.getLastUpdateTime() > maxLastUpdateTime) {
	            maxLastUpdateTime = user.getLastUpdateTime();
	            lastUpdatedUser = user;
	        }
	    }

	    // Now lastUpdatedUser contains the user with the latest update time in the group
	    return lastUpdatedUser;
	}
	
	//verify ID logic
	public boolean  verifyAllIDs(HashSet<String> idSet) {
		// Check the group's ID
	    if (idSet.contains(this.groupID) || this.groupID.contains(" ")) {
	        return false;
	    }
	    idSet.add(this.groupID);

	    // Check all users in this group
	    for (User user : this.groupUsers) {
	        if (idSet.contains(user.getID()) || user.getID().contains(" ")) {
	            return false;
	        }
	        idSet.add(user.getID());
	    }

	    // Recursively check all subgroups
	    for (UserGroupProfile subgroup : this.subGroups) {
	        if (!subgroup.verifyAllIDs(idSet)) {
	            return false;
	        }
	    }

	    return true;
	}

	// A helper method to verify the ID of a single user
	private boolean verifyID(User user) {
	    // Display the result using a dialog or console output
	   /* if (isValidID(user.getID())) {
	        System.out.println("ID Verification Passed for User: " + user.getID());
	    } else {
	        System.out.println("ID Verification Failed for User: " + user.getID());
	    }*/
	    return isValidID(user.getID());

	}

	// A simple ID validation logic: Check if the ID is greater than zero
	private boolean isValidID(String userID) {
	    try {
	        int idNumber = Integer.parseInt(userID);
	        return idNumber > 0;
	    } catch (NumberFormatException e) {
	        // Handle the case where the ID is not a valid integer
	        return false;
	    }
	}
}