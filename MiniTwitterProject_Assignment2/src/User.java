
import java.util.ArrayList;
import java.util.List;
//User class representing a system entry, notification subject, and update observer

public class User extends NotificationSubject  implements SysEntry, UpdateObserver {
	private List<User> followers = new ArrayList<User>();
	private List<User> following = new ArrayList<User>();
	private List<String> feed = new ArrayList<String>();
	private String userID;
	private String parentID;
	private int messageCounter = 0;
	
	
	//creates a constructor that takes in the user's name
	public User(String name) {
		userID = name;
	}//end constructor
	
	
	
	/***
	 * Update the user's feed with a given tweet and notify followers
	 * 
	 * @param tweet: the string message that the user is sending
	 */
	public void updateFeed(String tweet) {
		feed.add(this.getID() + ": " + tweet);
		messageCounter = getMessageCounter() + 1;
        this.notifyFollowers(followers, tweet, this.getID());
		// Use notifyFollowers from the NotificationSubject parent class		this.notifyFollowers(followers, tweet, this.getID());
	}//end updateFeed
	
	
	//method to update feed when a user you follow tweets
	/**
	 * @param message: the string message sent to the followers
	 */
	public void followerUpdateFeed(String message) {
		feed.add(message);
	}//end followerUpdateFeed
	
	// Get the display name of the user
	public String getID() {
		return userID;
	}//end getID
	
	//sets the display name of the user
	public void setID(String s) {
		userID = s;
	}//end setID
	
	//passes a User object as argument
	//adds the passed object into following list
	//adds the user who called into the arguments followers list
	public void followUser(User user) {
		if (user != null) {
	        following.add(user);
	        user.addFollower(this);
	    } else {
	        System.out.println("Cannot follow null user.");
	    }
		
		//Alternative Method in case change is desired.
		//if (!followers.contains(user)) {
		//	following.add(user);
		//	user.addFollower(this);}
	}//end follow user

	//getter for parent
	public String getParent() {
		return parentID;
	}//end getParent
	
	//setter for parent
	public void setParent(String parent) {
		this.parentID = parent;
	}//end setParent

	//getter for feed
	public List<String> getFeed() {
		return feed;
	}//end getFeed

	//setter for feed
	public void setFeed(List<String> feed) {
		this.feed = feed;
	}//end setFeed

	//getter for following
	public List<User> getFollowing() {
		return following;
	}//end getFollowing

	//setter for following
	public void setFollowing(List<User> following) {
		this.following = following;
	}//end setFollowing
	
	//getter for followers
	public List<User> getFollowers() {
		return followers;
	}//end getFollowers
	
	
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}//end setFollowers
	
	// Pass a User object as an argument
	// Add the argument into the followers list
	public void addFollower(User user) {
		followers.add(user);
	}//end addFollower
	

	@Override
	public int accept(GroupTotalDisplayVisitor  visitor) {
		int x = 0;
		return x;
		
	}


	@Override
	public int accept(MessageTotalDisplayVisitor  visitor) {
		int x = 0;
		return x;
		
	}


	@Override
	public float accept(PositivePercentDisplayVisitor  visitor, List<String> list) {
		float x = 0;
		return x;
	}


	@Override
	public int accept(UserTotalDisplayVisitor  visitor) {
		int x = 0;
		return x;
		
	}

	/**
	 * Get the total number of messages for a user
	 * 
	 * @return int: returns the total number of messages for a user
	 */
	public int getMessageCounter() {
		return messageCounter;
	}
	
}