package main.java.finalproject;

import java.awt.GridLayout; //useful for layouts
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Login extends JFrame{
    
    // Establish a connection
    Dao connect = new Dao();

    public Login() {

        // Set title
        super("IIT HELP DESK LOGIN");

        // Create the tables in the database (Remove later on)
        connect.createTables();

        // Set up some basics
        setSize(400, 210);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null); // centers window

        // Set up controls
        JLabel lblUsername = new JLabel("Username", JLabel.LEFT);
		JLabel lblPassword = new JLabel("Password", JLabel.LEFT);
		JLabel lblStatus = new JLabel(" ", JLabel.CENTER);
		// JLabel lblSpacer = new JLabel(" ", JLabel.CENTER);
    }

    
}