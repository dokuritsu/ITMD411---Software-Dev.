package main.java.finalproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tickets extends JFrame implements ActionListener{

    // For connecting to db
    Dao dao = new Dao(); // for CRUD operations
    Boolean chkIfAdmin = null;

    // Main menu object items
	private JMenu mnuFile = new JMenu("File");
	private JMenu mnuAdmin = new JMenu("Admin");
    private JMenu mnuTickets = new JMenu("Tickets");
    
    // Sub menu item objects for all Main menu item objects
    JMenuItem mnuItemExit;
    JMenuItem mnuItemRefresh;
	JMenuItem mnuItemUpdate;
	JMenuItem mnuItemDelete;
	JMenuItem mnuItemOpenTicket;
    JMenuItem mnuItemViewTicket;

    // Limit what is shown depending on who logs in
    public Tickets(Boolean isAdmin) {
        if (chkIfAdmin != isAdmin){
            try{
                // Use JTable built in functionality to build a table model and
				// display the table model off your result set!!!
				JTable jt = new JTable(ticketsJTable.buildTableModel(dao.readRecords()));
				jt.setBounds(30, 40, 200, 400);
				JScrollPane sp = new JScrollPane(jt);
				add(sp);
                setVisible(true); // refreshes or repaints frame on screen
                chkIfAdmin = true;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
		createMenu();
		// prepareGUI();

    }

    private void createMenu(){
        /* Initialize sub menu items **************************************/

		// initialize sub menu item for File main menu
		mnuItemExit = new JMenuItem("Exit");
		// add to File main menu item
        mnuFile.add(mnuItemExit);
        
        // initialize sub menu item for File main menu
		mnuItemRefresh = new JMenuItem("Refresh");
		// add to File main menu item
        mnuFile.add(mnuItemRefresh);
        
        // Check to see if user is admin
        if (chkIfAdmin == true){
            // initialize first sub menu items for Admin main menu
            mnuItemUpdate = new JMenuItem("Update Ticket");
            // add to Admin main menu item
            mnuAdmin.add(mnuItemUpdate);

            // initialize second sub menu items for Admin main menu
            mnuItemDelete = new JMenuItem("Delete Ticket");
            // add to Admin main menu item
            mnuAdmin.add(mnuItemDelete);
        }

        // initialize first sub menu item for Tickets main menu
		mnuItemOpenTicket = new JMenuItem("Open Ticket");
		// add to Ticket Main menu item
		mnuTickets.add(mnuItemOpenTicket);

		// initialize second sub menu item for Tickets main menu
		mnuItemViewTicket = new JMenuItem("View Ticket");
		// add to Ticket Main menu item
		mnuTickets.add(mnuItemViewTicket);

        // initialize any more desired sub menu items below
        /* Add action listeners for each desired menu item *************/
		mnuItemExit.addActionListener(this);
		mnuItemUpdate.addActionListener(this);
		mnuItemDelete.addActionListener(this);
		mnuItemOpenTicket.addActionListener(this);
		mnuItemViewTicket.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
    
}