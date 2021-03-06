package main.java.finalproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

public class Dao {
    // Instance fields
    static Connection connect = null;
    Statement statement = null;

    // General constructor
    public Dao() {
    }

    // Setup the connection with the database
    public Connection getConnection(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3307/tickets?autoReconnect=true&useSSL=false"
            + "&user=fp411&password=411");
        } catch (SQLException se){
            se.printStackTrace();
        }

        return connect;
    }

    // Create the necessary tables in the database
    public void createTables(){
        // Necessary variables for SQL Query
        System.out.println("Connecting to a selected database to create Ticket & User tables...");

        final String createTicketTB = "CREATE TABLE lpereda_tickets(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_issuer VARCHAR(30), ticket_description VARCHAR(200), ticket_status VARCHAR(10), start_date VARCHAR(20), end_date VARCHAR(20))";
        final String createUsersTB = "CREATE TABLE lpereda_users(uid INT AUTO_INCREMENT PRIMARY KEY, uname VARCHAR(30), upass VARCHAR(30), admin int)";

        try{
            // Execute queries to create necessary tables
            statement = getConnection().createStatement();

            // Create the tables
            statement.executeUpdate(createTicketTB);
            statement.executeUpdate(createUsersTB);

            System.out.println("Successfully created Ticket & User tables...");

            // Close the connection
            statement.close();
            connect.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

        // Add users from the given csv file
        addUsers();
    }

    // Add users from the given csv file
    private void addUsers() {
        String sql;
        Statement statement;
        BufferedReader br;
        List<List<String>> info = new ArrayList<>();
       
        // Begin reading from csv file
        try{
            System.out.println("Reading from csv file...");
            br = new BufferedReader(new FileReader(new File("./userlist.csv")));

            String line;
            while ((line = br.readLine()) != null) {
                info.add(Arrays.asList(line.split(",")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set up connection to DB
        try{
            System.out.println("Connecting to db to insert users...");
            statement = getConnection().createStatement();

            // Loop through info to obtain values and insert into user table
            for (List<String> rowData: info) {
                sql = "insert into lpereda_users(uname,upass,admin) " + "values('" + rowData.get(0) + "'," + " '"
                + rowData.get(1) + "','" + rowData.get(2) + "');";
            
                statement.executeUpdate(sql);
            }

            // Close out connection
            System.out.println("Inserted users from csv into user table...");
            statement.close();

        } catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    // Insert ticket
    public int insertTicket(String ticketName, String ticketDesc) {
        int id = 0;

        try{
            System.out.println("Connecting to db to insert ticket...");
            statement = getConnection().createStatement();
            
            // Create timestamp of ticket
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd, HH:mm:ss").format(Calendar.getInstance().getTime());
            
            // Pass query
            statement.executeUpdate("Insert into lpereda_tickets" + "(ticket_issuer, ticket_description, ticket_status, start_date) values(" + " '" 
                + ticketName + "','" + ticketDesc + "','" + "OPEN"  + "','" + timeStamp + "')", Statement.RETURN_GENERATED_KEYS);

            System.out.println("Inserted ticket into ticket table...");

            // Retrieve ticket ID # that was newly auto-genereated when inserted
            ResultSet resultSet = null;
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                // Return the first field in table
                id = resultSet.getInt(1);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return id;
    }
    
    // View tickets
    public ResultSet readTickets() {
        ResultSet results = null;
        try{
            System.out.println("Connecting to db to read the tickets...");
            statement = connect.createStatement();
            results = statement.executeQuery("SELECT * FROM lpereda_tickets");
            //connect.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return results;
    }

    // View specific user's tickets if not admin
    public ResultSet viewUserTickets(String ticketIssuer) {
        ResultSet results = null;
        try{
            System.out.println("Connecting to db to read users tickets...");

            // SQL Query
            String sql = "select * from lpereda_tickets where ticket_issuer=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, ticketIssuer);
            results = ps.executeQuery();
            //connect.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        System.out.println("Retrieved user's tickets...");
        return results;
    }

    // Update the specific ticket
    public void updateTicket(String ticketID, String ticketDesc, String status) {
        // Need to update ticket description first
        ResultSet results = null;
        String curr_desc = null;
        try{
            System.out.println("Connecting to database to obtain current ticket description...");
            String sql = "select ticket_description from lpereda_tickets where ticket_id=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, ticketID);
            results = ps.executeQuery();

            System.out.println("Successfully obtained current ticket description...");

            while(results.next()){
                curr_desc = results.getString("ticket_description");
            }
            ps.close();

            String updatedDesc = curr_desc + "\n Update: " + ticketDesc;

            // Check the status of ticket
            String timeStamp = null;
            if(status.equalsIgnoreCase("CLOSED") || status.equalsIgnoreCase("")){
                // We need to get a timestamp to add to end_date
                // Create timestamp of ticket
                timeStamp = new SimpleDateFormat("yyyy/MM/dd, HH:mm:ss").format(Calendar.getInstance().getTime());
                // Update the ticket
                System.out.println("Connecting to database to update ticket...");
                String sql2 = "update lpereda_tickets set ticket_description=?, ticket_status=?, end_date=? where ticket_id=?";
                PreparedStatement ps2 = connect.prepareStatement(sql2);
                ps2.setString(1, updatedDesc);
                ps2.setString(2, status);
                ps2.setString(3, timeStamp);
                ps2.setString(4, ticketID);
                ps2.executeUpdate();
                ps2.close();
            } else {
                // Update the ticket
                System.out.println("Connecting to database to update ticket...");
                String sql2 = "update lpereda_tickets set ticket_description=?, ticket_status=? where ticket_id=?";
                PreparedStatement ps2 = connect.prepareStatement(sql2);
                ps2.setString(1, updatedDesc);
                ps2.setString(2, status);
                ps2.setString(3, ticketID);
                ps2.executeUpdate();
                ps2.close();
            }
            ps.close();
            System.out.println("Successfully connected to database & updated ticket...");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Delete the specific ticket
    public int deleteTicket(String ticketID) {
        int result = 0;
        try{
            System.out.println("Connecting to database to delete ticket...");
            String sql = "delete from lpereda_tickets where ticket_id=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, ticketID);
            result = ps.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return result;
    }
}