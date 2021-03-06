package main.java.finalproject;

import java.awt.GridLayout; //useful for layouts
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;

public class Login extends JFrame {

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
        Color c = new Color(147, 112, 219);
        getContentPane().setBackground(c);

        // Set up a border
        ((JComponent) getContentPane())
                .setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white),
                        BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                                BorderFactory.createLoweredBevelBorder())));

        JLabel title = new JLabel("IIT HELP ", JLabel.RIGHT);
        JLabel title1 = new JLabel("DESK LOGIN", JLabel.LEFT);
        title.setForeground(Color.white);
        title1.setForeground(Color.white);

        // Set up controls
        JLabel lblUsername = new JLabel("Username", JLabel.LEFT);
        JLabel lblPassword = new JLabel("Password", JLabel.LEFT);
        JLabel lblStatus = new JLabel(" ", JLabel.CENTER);
        // JLabel lblSpacer = new JLabel(" ", JLabel.CENTER);

        // Add color
        lblUsername.setForeground(Color.white);
        lblPassword.setForeground(Color.white);

        // Area for username & password
        JTextField txtUname = new JTextField(10);
        JPasswordField txtPassword = new JPasswordField();

        // Buttons for logging and exiting
        JButton btn = new JButton("Login");
        JButton btnExit = new JButton("Exit");

        // Add color
        Color c2 = new Color(75, 0, 130);
        btnExit.setBackground(c2);
        btn.setBackground(c2);
        btnExit.setForeground(Color.white);
        btn.setForeground(Color.white);

        // Constraints
        lblStatus.setToolTipText("Contact help desk to unlock password");
        lblUsername.setHorizontalAlignment(JLabel.CENTER);
        lblPassword.setHorizontalAlignment(JLabel.CENTER);

        // Add objects to frame
        add(title);
        add(title1);
        add(lblUsername); // 1st row filler
        add(txtUname);
        add(lblPassword); // 2nd row
        add(txtPassword);
        add(btn); // 3rd row
        add(btnExit);
        // add(lblStatus); // 4th row

        // Add action listener for Login button
        btn.addActionListener(new ActionListener() {
            int count = 0; // count agent

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Login button clicked");
                boolean isAdmin = false;
                count = count + 1;
                int admin;

                // Verify credentials of user
                String query = "SELECT * FROM lpereda_users WHERE uname = ? and upass = ?;";
                try (PreparedStatement stmt = connect.getConnection().prepareStatement(query)) {
                    String user = txtUname.getText();
                    stmt.setString(1, user);
                    stmt.setString(2, txtPassword.getText());
                    ResultSet rs = stmt.executeQuery();
                    
                    if(rs.next()){
                        admin = rs.getInt("admin");
                        if(admin == 1){
                            isAdmin = true;
                            System.out.println("Successfully logged in as a admin");
                        } else {
                            System.out.println("Successfully logged in as a user");
                        }

                        new Tickets(isAdmin, user);
                        setVisible(false); // HIDE THE FRAME
                        dispose(); // CLOSE OUT THE WINDOW
                    } else {
                        lblStatus.setText("Try again! " + (3 - count) + " / 3 attempts left");
                        System.out.println("Try again! " + (3 - count) + " / 3 attempts left");
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }

            }
            
        });

        // Add action listerer for Exit button
        btnExit.addActionListener(e -> System.exit(0));
        
        setVisible(true); // SHOW THE FRAME
    }

    public static void main(String[] args) {
		new Login();
	}
    
}