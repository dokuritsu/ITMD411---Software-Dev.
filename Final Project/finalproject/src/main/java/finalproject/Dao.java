package main.java.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

        final String createTicketTB = "CREATE TABLE lpereda_tickets_test(ticket_id INT AUTO_INCREMENT PRIMARY KEY, ticket_issuer VARCHAR(30), ticket_description VARCHAR(200), start_date VARCHAR(10), end_data VARCHAR(10))";
        final String createUsersTB = "CREATE TABLE lpereda_users_test(uid INT AUTO_INCREMENT PRIMARY KEY, uname VARCHAR(30), upass VARCHAR(30), admin int)";

        try{
            // Execute queries to create necessary tables
            statement = getConnection().createStatement();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
}