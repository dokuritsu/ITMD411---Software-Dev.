package main.java.finalproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Tickets extends JFrame implements ActionListener{

    // For connecting to db
    Dao dao = new Dao(); // for CRUD operations
    Boolean chkIfAdmin = false;
    String user = "";
    Color c = new Color(147, 112, 219);
    Color c2 = new Color(75, 0, 130);

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
    public Tickets(Boolean isAdmin, String user) {
        this.user = user;
        if (chkIfAdmin != isAdmin){
            try{
				JTable jt = new JTable(ticketsJTable.buildTableModel(dao.readTickets()));
                jt.setBounds(30, 40, 200, 400);

                // Add some color
                jt.setBackground(c);
                jt.setForeground(Color.white);
                jt.getTableHeader().setBackground(c2);
                jt.getTableHeader().setForeground(Color.white);

				JScrollPane sp = new JScrollPane(jt);
                add(sp);
                setVisible(true); // refreshes or repaints frame on screen
                chkIfAdmin = true;
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } else {
            try {
				JTable jt = new JTable(ticketsJTable.buildTableModel(dao.viewUserTickets(user)));
                jt.setBounds(30, 40, 200, 400);

                // Add some color
                jt.setBackground(c);
                jt.setForeground(Color.white);
                jt.getTableHeader().setBackground(c2);
                jt.getTableHeader().setForeground(Color.white);

				JScrollPane sp = new JScrollPane(jt);
				add(sp);
                setVisible(true); // refreshes or repaints frame on screen
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
		createMenu();
		prepareGUI();

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
        mnuItemRefresh.addActionListener(this);

        // Check if user is asmin
        if(chkIfAdmin == true){
            mnuItemUpdate.addActionListener(this);
		    mnuItemDelete.addActionListener(this);
        }
		mnuItemOpenTicket.addActionListener(this);
		mnuItemViewTicket.addActionListener(this);
    }

    private void prepareGUI() {
        // create JMenu bar
		JMenuBar bar = new JMenuBar();
        bar.add(mnuFile); // add main menu items in order, to JMenuBar
        
        // Check if user is admin
        if (chkIfAdmin == true) {
            bar.add(mnuAdmin);
        }
        bar.add(mnuTickets);

		// add menu bar components to frame
        setJMenuBar(bar);
        
        addWindowListener(new WindowAdapter() {
			// define a window close operation
			public void windowClosing(WindowEvent wE) {
				System.exit(0);
			}
		});
		// set frame options
		setSize(800, 400);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //  Just decoration
        UIManager.put("OptionPane.background", (c)); 
		UIManager.put("Panel.background", (c));
		UIManager.put("OptionPane.foreground", (Color.white)); 
		UIManager.put("Panel.foreground", (Color.white));

        // implement actions for sub menu items
		if (e.getSource() == mnuItemExit) {
			System.exit(0);
		} else if (e.getSource() == mnuItemOpenTicket) {
            // get ticket information
			String ticketName = JOptionPane.showInputDialog(null, "Enter your Username");
            String ticketDesc = JOptionPane.showInputDialog(null, "Enter a ticket description");
            
            // Check to make sure the ticket name & descr is not null
            if (ticketName == null || ticketDesc == null || ticketName.equals("") || ticketDesc.equals("")){
                JOptionPane.showMessageDialog(null, "Failed to open ticket: No valid name or description provided.");
			    System.out.println("Failed to open ticket: No valid name or description provided.");
            } else {
                // Insert ticket info to database
                int id = dao.insertTicket(ticketName, ticketDesc);

                // display results if successful or not to console / dialog box
                if (id != 0) {
                    System.out.println("Ticket ID : " + id + " created successfully!!!");
                    JOptionPane.showMessageDialog(null, "Ticket id: " + id + " created");

                    try{
                        JTable jt = new JTable(ticketsJTable.buildTableModel(dao.viewUserTickets(this.user)));
                        jt.setBounds(30, 40, 200, 400);

                        // Add some color
                        jt.setBackground(c);
                        jt.setForeground(Color.white);
                        jt.getTableHeader().setBackground(c2);
                        jt.getTableHeader().setForeground(Color.white);
                        
                        JScrollPane sp = new JScrollPane(jt);
                        add(sp);
                        setVisible(true); // refreshes or repaints frame on screen
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                } else
                    System.out.println("Ticket cannot be created!!!");
            }
        } else if (e.getSource() == mnuItemViewTicket || e.getSource() == mnuItemRefresh){
            try {
                if (chkIfAdmin == true){
                    JTable jt = new JTable(ticketsJTable.buildTableModel(dao.readTickets()));
                    jt.setBounds(30, 40, 200, 400);

                    // Add some color
                    jt.setBackground(c);
                    jt.setForeground(Color.white);
                    jt.getTableHeader().setBackground(c2);
                    jt.getTableHeader().setForeground(Color.white);
                    
                    JScrollPane sp = new JScrollPane(jt);
                    add(sp);
                    
                    setVisible(true); // refreshes or repaints frame on screen
                } else {
                    JTable jt = new JTable(ticketsJTable.buildTableModel(dao.viewUserTickets(user)));
                    jt.setBounds(30, 40, 200, 400);

                    // Add some color
                    jt.setBackground(c);
                    jt.setForeground(Color.white);
                    jt.getTableHeader().setBackground(c2);
                    jt.getTableHeader().setForeground(Color.white);
                    
                    JScrollPane sp = new JScrollPane(jt);
                    add(sp);
                    setVisible(true); // refreshes or repaints frame on screen
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } else if (e.getSource() == mnuItemUpdate) {
            // Note: Update feature is only for admins

            // Provide ticket info
            String ticketID = JOptionPane.showInputDialog(null, "Please provide the ticket ID");
            String ticketDesc = JOptionPane.showInputDialog(null, "Please provide reason for update");
            String status = JOptionPane.showInputDialog(null, "Please provide the current status of the ticket");

            // Check that the ticket id is not null
            if (ticketID == null || ticketID.equals("") || ticketDesc == null || ticketDesc.equals("") || status == null || status.equals("")){
                JOptionPane.showMessageDialog(null, "Failed to update ticket: Empty or invalid ticketID");
                System.out.println("Failed to update ticket: Empty or invalid ticketID, ticketDesc, or status");
            } else {
                // Insert new ticket info into database
                int tID = Integer.parseInt(ticketID);
                dao.updateTicket(ticketID, ticketDesc, status);

                // Inform user if successful or not
                if (tID != 0){
                    JOptionPane.showMessageDialog(null, "Ticket ID: " + tID + " updated successfully!!!");
                    System.out.println("Ticket ID: " + tID + " updated successfully!!!");

                    try{
                        JTable jt = new JTable(ticketsJTable.buildTableModel(dao.readTickets()));
                        jt.setBounds(30, 40, 200, 400);

                        // Add some color
                        jt.setBackground(c);
                        jt.setForeground(Color.white);
                        jt.getTableHeader().setBackground(c2);
                        jt.getTableHeader().setForeground(Color.white);
                        
                        JScrollPane sp = new JScrollPane(jt);
                        add(sp);
                        setVisible(true); // refreshes or repaints frame on screen
                    } catch (SQLException se) {
                        
                    }
                }

            }

        } else if (e.getSource() == mnuItemDelete) {
            // Note: Delete feature only for admins

            // Get ticket ID
            String ticketID = JOptionPane.showInputDialog(null, "Please provide a ticket ID");

            // Check to make sure the ticketID is not empty or null
            if (ticketID == null || ticketID.equals("")){
                JOptionPane.showMessageDialog(null, "Failed to delete ticket: Invalid or empty ticket ID");
                System.out.println("Failed to delete ticket: Invalid or empty ticket ID");
            } else {
                int tID = Integer.parseInt(ticketID);

                // Verify that the admin wants to delete ticket
                int verify = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete ticket " + tID + "?", "Warning!", JOptionPane.YES_NO_OPTION);
                if(verify == JOptionPane.YES_OPTION){
                    int result = dao.deleteTicket(ticketID);
                    if (result != 0){
                        JOptionPane.showMessageDialog(null, "Successfully deleted ticket ID: " + tID + "!");
                        System.out.println("Successfully deleted ticket ID: " + tID + "!");

                        try{
                            JTable jt = new JTable(ticketsJTable.buildTableModel(dao.readTickets()));
                            jt.setBounds(30, 40, 200, 400);
    
                            // Add some color
                            jt.setBackground(c);
                            jt.setForeground(Color.white);
                            jt.getTableHeader().setBackground(c2);
                            jt.getTableHeader().setForeground(Color.white);
                            
                            JScrollPane sp = new JScrollPane(jt);
                            add(sp);
                            setVisible(true); // refreshes or repaints frame on screen
                        } catch (SQLException se) {
                            
                        }
                    } else {
                        System.out.println("Ticket cannot be deleted!!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ticket ID: " + tID + " gets to live another day...");
                }
            }
        }

    }
    
}