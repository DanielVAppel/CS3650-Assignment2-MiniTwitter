
import java.util.List;

public class MessageTotalDisplayVisitor {
	
	/**
	 * Calculate and return the total number of messages
	 * 
	 * @param messageCounter: the counter representing the number of messages
	 * @return: int representing the total number of messages
	 */
	
	public int visit(int counter) {
		int total = 0;
		total += counter;
		return total;
	}
}