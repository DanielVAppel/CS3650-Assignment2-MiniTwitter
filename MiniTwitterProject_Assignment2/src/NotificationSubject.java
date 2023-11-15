
import java.util.List;
//Subject class responsible for notifying followers

public class NotificationSubject  {
	
	
	/**
	 * Notify followers with a message from a specific user
	 * 
	 * @param followers: list of Users that follow a specific user
	 * @param tweet: a string message that the user types
	 * @param notifier: a string that is the id of the user who is notifing their followers
	 */
	public void notifyFollowers(List<User> followerList, String tweet, String notifier) {
		for(User follower: followerList) {
			follower.followerUpdateFeed(notifier + ": " + tweet);
		}
	}//end notifyFollowers
}