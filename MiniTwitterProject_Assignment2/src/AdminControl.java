//CS36050 Assignment 2 By Daniel Appel, Created 11/14/2023
//Summary: This projects creates a mini Twitter GUI that mimics the basic functions of a twitter program.



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//Singleton class using the Singleton pattern
public class AdminControl implements TreeSelectionListener {
	
	private static AdminControl instance = null;

	private JFrame mainFrame;
	private JTextField userTextField;
	private JTextField groupTextField;
	private String tempID;
	private User newUser;
	private UserGroupProfile newGroup;
	private UserGroupProfile root = new UserGroupProfile("Root");
	private JTree tree;
	private DefaultMutableTreeNode selectedNode;
	public int totalGroups;
	public int totalUsers;
	public int totalMessages;
	public float totalPositive;
	private JLabel userTotalLabel;
	private JLabel groupTotalLabel;
	private JLabel messageTotalLabel;
	private JLabel positivePercentageLabel;
	private List<String> positiveWordsList = new ArrayList<String>();
	
	
	
	//private constructor to control the instances
	private AdminControl() {
			
	}
		
	// New object of AdminControl must call getInstance to ensure that there is only
	// one instance of the Singleton class
	public static AdminControl getInstance() {
		if(instance == null) {
			instance = new AdminControl();
		}
			
		return instance;
	}
	
	private void generateTree() {
		// Implementation for generating the tre
	}

	/**
	 * Initialize the contents of the mainFrame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		mainFrame = new JFrame("Twitter Admin Panel");
		mainFrame.setBounds(100, 100, 725, 340);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		JButton addUserBtn = new JButton("Add User");
		addUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Selects the node clicked on by the mouse
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				// Creates a newNode from the userTextField
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(userTextField.getText());
				// Adds the new node underneath the selected node
				selectedNode.add(newNode);
				
				// Gets the tree model
				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
				// Reload the tree model
				model.reload();
				
				String selectedGroup = selectedNode.getUserObject().toString();
				newUser = new User(userTextField.getText());
				
				// Adds the user to the root's user list if root is the selected node
				if(root.getID().equals(selectedGroup) ) {
					root.addUser(newUser);
					newUser.setParent(root.getID());
				}
				else{
					for(UserGroupProfile  group: root.getGroup()) {
						if(group.getID().equals(selectedGroup)) {
							group.addUser(newUser);
							newUser.setParent(group.getID());
						}
					}
				}
				
				
				
			}
		});
		addUserBtn.setBounds(365, 11, 103, 23);
		
		mainFrame.getContentPane().add(addUserBtn);
		
		JButton addGroupBtn = new JButton("Add Group");
		addGroupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(groupTextField.getText());
				selectedNode.add(newNode);
				
				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
				model.reload();
				
				String selectedGroup = selectedNode.getUserObject().toString();
				newGroup = new UserGroupProfile (groupTextField.getText());
				
				// Adds the group to the root's group list if root is the selected node
				if(root.getID().equals(selectedGroup) ) {
					root.addGroup(newGroup);
				}
				else{
					for(UserGroupProfile  group: root.getGroup()) {
						if(group.getID().equals(selectedGroup)) {
							group.addGroup(newGroup);
						}
					}
				}
				
			}
		});
		addGroupBtn.setBounds(365, 45, 103, 23);
		
		mainFrame.getContentPane().add(addGroupBtn);
		
		
		userTextField = new JTextField();
		userTextField.setBounds(235, 12, 120, 20);
		mainFrame.getContentPane().add(userTextField);
		userTextField.setColumns(10);
		
		
		groupTextField = new JTextField();
		groupTextField.setColumns(10);
		groupTextField.setBounds(235, 46, 120, 20);
		mainFrame.getContentPane().add(groupTextField);
		
		tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
			}
		});
		tree.setEditable(true);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode(root.getID()) {
				{
				}
			}
		));
		tree.setBounds(10, 11, 196, 279);
		mainFrame.getContentPane().add(tree);
		
		JButton findLastUpdatedBtn = new JButton("Find Last Updated User");
		findLastUpdatedBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        findLastUpdatedUser();
		    }
		});
		findLastUpdatedBtn.setBounds(365, 114, 233, 23);
		mainFrame.getContentPane().add(findLastUpdatedBtn);
		
		
		JButton verifyIDsBtn = new JButton("Verify IDs");
		verifyIDsBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        verifyIDs();
		    }
		});
		verifyIDsBtn.setBounds(495, 79, 103, 23);
		mainFrame.getContentPane().add(verifyIDsBtn);
		
		JButton openUserViewBtn = new JButton("Open User View");
		openUserViewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				String user = selectedNode.getUserObject().toString();
				for(User a: root.getUserList()) {
					if(a.getID().equals(user)) {
						UserViewWindow nw = new UserViewWindow(a, root);
						nw.NewScreen(a, root);
					}
				}
				for(UserGroupProfile  group: root.getGroup()) {
					for(User a: group.getUserList()) {
						if(a.getID().equals(user)) {
							UserViewWindow nw = new UserViewWindow(a, root);
							nw.NewScreen(a, root);
						}
					}
				}
			}
		});
		openUserViewBtn.setBounds(235, 79, 233, 23);
		mainFrame.getContentPane().add(openUserViewBtn);
		
		//button that displays the total number of users when pressed
		JButton userTotalBtn = new JButton("Show User Total");
		userTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalUsers = root.accept(new UserTotalDisplayVisitor());
				for(UserGroupProfile  group: root.getGroup()) {
					totalUsers += group.accept(new UserTotalDisplayVisitor ());
				}
				userTotalLabel.setText("Total Number of Users: " + totalUsers);
			}
		});
		userTotalBtn.setBounds(235, 142, 233, 23);
		mainFrame.getContentPane().add(userTotalBtn);
		
		//button that displays the total number of groups when pressed
		JButton groupTotalBtn = new JButton("Show Group Total");
		groupTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalGroups = root.accept(new GroupTotalDisplayVisitor ());
				for(UserGroupProfile  group: root.getGroup()) {
					totalGroups += group.accept(new GroupTotalDisplayVisitor ());
				}
				groupTotalLabel.setText("Total Number of Groups: " + totalGroups);
			}
		});
		groupTotalBtn.setBounds(235, 176, 233, 23);
		mainFrame.getContentPane().add(groupTotalBtn);
		
		//button that displays the total number of messages when pressed
		JButton messageTotalBtn = new JButton("Show Message Total");
		messageTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalMessages = root.accept(new MessageTotalDisplayVisitor ());
				for(UserGroupProfile  group: root.getGroup()) {
					totalMessages += group.accept(new MessageTotalDisplayVisitor ());
				}
				messageTotalLabel.setText("Total Number of Messages: " + totalMessages);
			}
		});
		messageTotalBtn.setBounds(235, 210, 233, 23);
		mainFrame.getContentPane().add(messageTotalBtn);
		
		//button that displays the percentage of positive words when sent
		JButton positiveTotalBtn = new JButton("Show Positive Percentage");
		positiveTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float stringTotal = 0;
				for(User user: root.getUserList()) {
					for(String string: user.getFeed()) {
						stringTotal++;
					}
				}
				for(UserGroupProfile  group: root.getGroup()) {
					for(User user: group.getUserList()) {
						for(String string: user.getFeed()) {
							stringTotal++;
						}
					}
				}
				
				totalPositive = root.accept(new PositivePercentDisplayVisitor (), positiveWordsList);
				for(UserGroupProfile  group: root.getGroup()) {
					totalPositive += group.accept(new PositivePercentDisplayVisitor (), positiveWordsList);
				}
				float positivePerc = (totalPositive/stringTotal) * 100;
				positivePercentageLabel.setText("Positive Percentage: " + positivePerc + "%");
				
			}
		});
		positiveTotalBtn.setBounds(235, 244, 233, 23);
		mainFrame.getContentPane().add(positiveTotalBtn);
		
		userTotalLabel = new JLabel("Total Number of Users:");
		userTotalLabel.setBounds(478, 146, 221, 14);
		mainFrame.getContentPane().add(userTotalLabel);
		
		groupTotalLabel = new JLabel("Total Number of Groups: ");
		groupTotalLabel.setBounds(478, 180, 224, 14);
		mainFrame.getContentPane().add(groupTotalLabel);
		
		messageTotalLabel = new JLabel("Total Number of Messages:");
		messageTotalLabel.setBounds(478, 214, 221, 14);
		mainFrame.getContentPane().add(messageTotalLabel);
		
		positivePercentageLabel = new JLabel("Positive Percentage:");
		positivePercentageLabel.setBounds(478, 248, 221, 14);
		mainFrame.getContentPane().add(positivePercentageLabel);
		mainFrame.setVisible(true);
		
	}
	
	public void runWindow() {
		positiveWordsList.add("good");
		positiveWordsList.add("great");
		positiveWordsList.add("nice");
		positiveWordsList.add("Excellent");
		positiveWordsList.add("beautiful");
		positiveWordsList.add("smart");
		positiveWordsList.add("cool");
		
		initialize();
	}

	@Override
	public void valueChanged(TreeSelectionEvent tsl) {
		if(tsl.getNewLeadSelectionPath() != null) {
			
		}
		
	}

	public List<String> getPositiveWords() {
		return positiveWordsList;
	}

	public void setPositiveWords(List<String> positiveWordsList) {
		this.positiveWordsList = positiveWordsList;
	}
	
	public void findLastUpdatedUser() {
	    // Implement logic to find the last updated user
	    // Display the result using a dialog or console output
		//User lastUpdatedUser = root.findLastUpdatedUser();
	    User lastUpdatedUser = findLastUpdatedUserRecursive(root);
		if (lastUpdatedUser != null) {
            JOptionPane.showMessageDialog(mainFrame, "Last Updated User: " + lastUpdatedUser.getID(), "Last Updated User", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No users have been updated yet.", "Last Updated User", JOptionPane.INFORMATION_MESSAGE);
        }
    }
	private User findLastUpdatedUserRecursive(UserGroupProfile group) {
		 User lastUpdatedUser = null;
		    long lastUpdateTime = 0;

		    // Check all users in this group
		    for (User user : group.getUserList()) {
		        if (user.getLastUpdateTime() > lastUpdateTime) {
		            lastUpdatedUser = user;
		            lastUpdateTime = user.getLastUpdateTime();
		        }
		    }

		    // Recursively check all subgroups
		    for (UserGroupProfile subgroup : group.getGroup()) {
		        User subgroupLastUpdatedUser = findLastUpdatedUserRecursive(subgroup);
		        if (subgroupLastUpdatedUser != null && subgroupLastUpdatedUser.getLastUpdateTime() > lastUpdateTime) {
		            lastUpdatedUser = subgroupLastUpdatedUser;
		            lastUpdateTime = subgroupLastUpdatedUser.getLastUpdateTime();
		        }
		    }

		    return lastUpdatedUser;
		}
	
	private void verifyIDs() {
	    // Implement ID verification logic here
	    // Display the result using a dialog or console output
	    HashSet<String> idSet = new HashSet<>();
		boolean areIDsValid = root.verifyAllIDs(idSet);
        if (areIDsValid) {
            JOptionPane.showMessageDialog(mainFrame, "All IDs are unique and valid.", "ID Verification", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Some IDs are not unique or valid.", "ID Verification", JOptionPane.ERROR_MESSAGE);
        }
    }
	public boolean validateIDs() {
	    HashSet<String> idSet = new HashSet<>();
	    return validateIDsRecursive(root, idSet);

	}
	public boolean validateAllIDs() {
	    HashSet<String> idSet = new HashSet<>();
	    return root.verifyAllIDs(idSet);
	}
	public User getLastUpdatedUser() {
	    User lastUpdatedUser = null;
	    long lastUpdateTime = 0;

	    for (User user : getAllUsers()) {
	        if (user.getLastUpdateTime() > lastUpdateTime) {
	            lastUpdatedUser = user;
	            lastUpdateTime = user.getLastUpdateTime();
	        }
	    }

	    return lastUpdatedUser;
	}

	private boolean validateIDsRecursive(UserGroupProfile group, HashSet<String> idSet) {
	    // Check and add the group ID
	    if (idSet.contains(group.getID()) || group.getID().contains(" ")) {
	        return false;
	    }
	    idSet.add(group.getID());
	 // Check all users in this group
	    for (User user : group.getUserList()) {
	        if (idSet.contains(user.getID()) || user.getID().contains(" ")) {
	            return false;
	        }
	        idSet.add(user.getID());
	    }

	    // Recursively check all subgroups
	    for (UserGroupProfile subgroup : group.getGroup()) {
	        if (!validateIDsRecursive(subgroup, idSet)) {
	            return false;
	        }
	    }

	    return true;
	}
	public List<User> getAllUsers() {
	    List<User> allUsers = new ArrayList<>();
	    collectAllUsersRecursive(root, allUsers);
	    return allUsers;
	}

	private void collectAllUsersRecursive(UserGroupProfile group, List<User> allUsers) {
	    allUsers.addAll(group.getUserList()); // Add all users in this group

	    for (UserGroupProfile subgroup : group.getGroup()) { // Recursively add users from all subgroups
	        collectAllUsersRecursive(subgroup, allUsers);
	    }
	}
}