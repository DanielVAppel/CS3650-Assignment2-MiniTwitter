

import java.util.List;
//Visitable interface for accepting visitors
public interface Visitable {
	/**
	 * Accepts a visitor for calculating the total number of groups.
	 * 
	 * @param visitor: the GroupTotalDisplayVisitor to accept
	 * @return: an integer representing the total number of groups
	 */
	public int accept(GroupTotalDisplayVisitor  visitor);
	/**
	 * Accepts a visitor for calculating the total number of messages.
	 * 
	 * @param visitor: the MessageTotalDisplayVisitor to accept
	 * @return: an integer representing the total number of messages
	 */
	public int accept(MessageTotalDisplayVisitor  visitor);
	/**
	 * Accepts a visitor for calculating the positive percentage.
	 * 
	 * @param visitor: the PositivePercentDisplayVisitor to accept
	 * @param list: a list of positive words
	 * @return: a float representing the positive percentage
	 */
	public float accept(PositivePercentDisplayVisitor  visitor, List<String> list);
	/**
	 * Accepts a visitor for calculating the total number of users.
	 * 
	 * @param visitor: the UserTotalDisplayVisitor to accept
	 * @return: an integer representing the total number of users
	 */
	public int accept(UserTotalDisplayVisitor visitor);
	
}