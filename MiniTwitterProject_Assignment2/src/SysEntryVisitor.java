
import java.util.List;
//System Entry Visitor class implementing Visitable

public class SysEntryVisitor implements Visitable {

	
	
	public void visit() {
		// Implementation for visiting a system entry
		// This method can be further developed for specific functionality
	}

	@Override
	public int accept(GroupTotalDisplayVisitor  visitor) {
		// TODO Implement acceptance of GroupTotalDisplayVisitor
		return 0;
	}

	@Override
	public int accept(MessageTotalDisplayVisitor  visitor) {
		// TODO Implement acceptance of MessageTotalDisplayVisitor
		return 0;
	}

	@Override
	public float accept(PositivePercentDisplayVisitor  visitor, List<String> list) {
		// TODO Implement acceptance of PositivePercentDisplayVisitor
		return 0;
	}

	@Override
	public int accept(UserTotalDisplayVisitor  visitor) {
		// TODO Implement the acceptance logic for UserTotalDisplayVisitor
		return 0;
	}

}