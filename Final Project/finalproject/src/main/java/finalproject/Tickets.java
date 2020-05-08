package main.java.finalproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Tickets extends JFrame implements ActionListener{

    // For connecting to db
    Dao dao = new Dao(); // for CRUD operations
    Boolean chkIfAdmin = null;

    // Limit what is shown depending on who logs in
    public Tickets(Boolean isAdmin) {
        if (chkIfAdmin != isAdmin){
            try{

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
		createMenu();
		prepareGUI();

	}

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    
}