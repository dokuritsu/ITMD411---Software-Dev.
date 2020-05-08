package main.java.finalproject;

import java.awt.GridLayout; //useful for layouts

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;

public class Login extends JFrame{
    
    // Establish a connection
    Dao connect;

    public Login() {

        // Set title
        super("IIT HELP DESK LOGIN");

        // Create the tables in the database (Remove later on)
        connect = new Dao();
        //connect.createTables();

        // Set up some basics
        setSize(400, 210);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null); // centers window

        // Add background color
        Color c = new Color(148,0,211);
        getContentPane().setBackground(c);

        // Set up a border
        ((JComponent) getContentPane()).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white), BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder())));

        // Set up controls
        JLabel lblUsername = new JLabel("Username", JLabel.LEFT);
		JLabel lblPassword = new JLabel("Password", JLabel.LEFT);
		JLabel lblStatus = new JLabel(" ", JLabel.CENTER);
        // JLabel lblSpacer = new JLabel(" ", JLabel.CENTER);
        
        // Area for username & password
        JTextField txtUname = new JTextField(10);
        JPasswordField txtPassword = new JPasswordField();

        
        // Buttons for logging and exiting
		JButton btn = new JButton("Submit");
        JButton btnExit = new JButton("Exit");
        
        // Constraints
        lblStatus.setToolTipText("Contact help desk to unlock password");
		lblUsername.setHorizontalAlignment(JLabel.CENTER);
        lblPassword.setHorizontalAlignment(JLabel.CENTER);
        
        // Add objects to frame
        add(lblUsername);  // 1st row filler
		add(txtUname);
		add(lblPassword); // 2nd row
		add(txtPassword);
		add(btn);         // 3rd row
		add(btnExit);
        add(lblStatus);   // 4th row
        
        setVisible(true); // SHOW THE FRAME
    }

    public static void main(String[] args) {
		new Login();
	}
    
}