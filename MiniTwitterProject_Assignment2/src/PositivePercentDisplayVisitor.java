
import java.util.List;

//Visitor class to show the percentage of positive messages
public class PositivePercentDisplayVisitor  {
	/**
	 * Calculate and return the percentage of messages containing positive words
	 * 
	 * @param feed: list of strings that contains the messages sent by a user
	 * @param list: list of strings that contains positive words
	 * @return: float that counts the number of strings containing positive words that appear in feed
	 */
	public float visit(List<String> messageFeed, List<String> positiveWordsList) {
		float positiveCount  = 0;
		for(String string: messageFeed) {
			for(String positiveWord : positiveWordsList) {
				if(string.contains(positiveWord )) {
					positiveCount ++;
					break;
				}
			}
		}//end for
		return positiveCount ;
	}
}