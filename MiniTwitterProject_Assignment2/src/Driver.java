	// Main method to launch the application
public class Driver {

	public static void main(String[] args) {
        // Create a single instance of AdminController using the Singleton pattern
		AdminControl twitter = AdminControl.getInstance();
        // Invoke a method to initialize and display the graphical user interface
		twitter.runWindow();
	}

}