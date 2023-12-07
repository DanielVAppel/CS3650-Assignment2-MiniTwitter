

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

//User View Window class for displaying user information

public class UserViewWindow {

	private JFrame frame;
	private static User u1;
	private JTextField feedTF;
	private JTextField followTF;
	JTextArea followTA;
	JTextArea feedTA;

	/**
	 * Launch the application.
	 */
	public static void NewScreen(User user, UserGroupProfile  root) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					u1 = user;
					UserViewWindow window = new UserViewWindow(u1, root);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserViewWindow(User u1, UserGroupProfile  root) {
		initialize(u1, root);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Added this line
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(User u1, UserGroupProfile  root) {
		frame = new JFrame(u1.getID());
		frame.setBounds(100, 100, 500, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Displaying the creation time
	    JLabel creationTimeLabel = new JLabel("Creation Time: " + u1.getCreationTime());
	    creationTimeLabel.setBounds(10, 470, 200, 14); // Adjust position as needed
	    frame.getContentPane().add(creationTimeLabel);

	    // Displaying the last update time
	    JLabel lastUpdateTimeLabel = new JLabel("Last Update Time: " + u1.getLastUpdateTime());
	    lastUpdateTimeLabel.setBounds(220, 470, 200, 14); // Adjust position as needed
	    frame.getContentPane().add(lastUpdateTimeLabel);
	    
		//button used to add a new user to follow
		JButton followBtn = new JButton("Follow User");
		followBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(User user: root.getUserList()) {
					if(user.getID().contentEquals(followTF.getText())) {
						u1.followUser(user);
						String followList = "";
						for(User following: u1.getFollowing()) {
							followList = followList.concat("  " + following.getID()); 
						}
						followTA.setText(followList);
					}
				}
				for(UserGroupProfile  group: root.getGroup()) {
					for(User user: group.getUserList()) {
						if(user.getID().contentEquals(followTF.getText())) {
							u1.followUser(user);
							String followList = "";
							for(User following: u1.getFollowing()) {
								followList = followList.concat("  " + following.getID()); 
							}
							followTA.setText(followList);
						}
					}
				}
			}
		});
		followBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		followBtn.setBounds(302, 11, 172, 23);
		frame.getContentPane().add(followBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 45, 464, 159);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		//label used to name the following panel
		JLabel followLbl = new JLabel("Following:");
		followLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		followLbl.setBounds(10, 11, 119, 22);
		panel_1.add(followLbl);
		
		followTA = new JTextArea();
		followTA.setLineWrap(true);
		followTA.setEditable(false);
		followTA.setBounds(10, 44, 444, 104);
		panel_1.add(followTA);
		
		feedTF = new JTextField();
		feedTF.setBounds(10, 215, 272, 23);
		frame.getContentPane().add(feedTF);
		feedTF.setColumns(10);
		
		//button used to send a tweet and update the user's feed
		JButton tweetBtn = new JButton("Post Tweet");
		tweetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				u1.updateFeed(feedTF.getText());
				String feed = "";
				for(String list: u1.getFeed()) {
					feed = feed.concat("    " + list);
				}
				feedTA.setText(feed);
			}
		});
		tweetBtn.setBounds(302, 215, 172, 23);
		frame.getContentPane().add(tweetBtn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 268, 464, 195);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		//Names the feed Pane
		JLabel feedLbl = new JLabel("Feed:");
		feedLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		feedLbl.setForeground(Color.BLACK);
		feedLbl.setBounds(10, 11, 57, 14);
		panel_2.add(feedLbl);
		
		feedTA = new JTextArea();
		feedTA.setLineWrap(true);
		feedTA.setEditable(false);
		feedTA.setBounds(10, 36, 444, 148);
		panel_2.add(feedTA);
		
		//the text field for typing in who to follow
		followTF = new JTextField();
		followTF.setBounds(10, 11, 198, 23);
		frame.getContentPane().add(followTF);
		followTF.setColumns(10);
		
		//This button is used to refresh auser's feed
		JButton refreshBtn = new JButton("Refresh Feed");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String feed = "";
				for(String list: u1.getFeed()) {
					feed = feed.concat("    " + list);
				}
				feedTA.setText(feed);
			}
		});
		refreshBtn.setBounds(302, 238, 172, 23);
		frame.getContentPane().add(refreshBtn);
	}
}