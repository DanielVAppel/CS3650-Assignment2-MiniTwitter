// Observer interface for receiving updates

public interface UpdateObserver  {
	
	// Update the feed when called by the subject
	public void updateFeed(String tweet);
	
	// Method to update the feed when a user you follow tweets
	public void followerUpdateFeed(String message);
}