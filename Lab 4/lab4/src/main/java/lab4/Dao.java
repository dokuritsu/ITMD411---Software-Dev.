package  lab4;

import java.sql.*;
import java.util.ArrayList;

public class Dao {

    // DB objects
    static DBConnect conn = null;
    static Statement stmt = null;

    // Constructor
    public Dao() {
        conn = new DBConnect();
    }

    /*
     * Creates a db table; Includes the following fields: pid, id, income & pep
     */
    public void createTables() {
        try {
            // Open a connection to db
            System.out.println("Connecting to a selected database to create a table...");
            System.out.println("Successfully connected to database...");

            // Execute given query
            System.out.println("Creating table in given database...");

            stmt = conn.connect().createStatement();

            // Query
            String sql = "CREATE TABLE L_PERE_tab " +
                        "pid INTEGER not NULL AUTO_INCREMENT, " +
                        "id VARCHAR(10), " +
                        "income numeric(8,2)," +
                        "pep VARCHAR(4), " +
                        "PRIMARY KEY ( pid ))";

            stmt.executeUpdate(sql);

            // Close connection
            System.out.println("Created table in given database...");
            conn.connect().close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /*
    *   Allow for the arraylist of BankRecords objects to be inserted into database table when called
    */
    public void insertRecords(ArrayList<BankRecords> bankRecords) {
        try {
            // Execute the query
            System.out.println("Inserting records into the table...");
            stmt = conn.connect().createStatement();
            String sql = null;

            // Loop through arraylist of objects to update table with records
            for (int i = 0; i < bankRecords.size(); i++){

                // Get the following information from each bank record: id, income, & pep
                String id = bankRecords.get(i).getId();
                Double income = bankRecords.get(i).getIncome();
                String pep = bankRecords.get(i).getPep();
                
                // Create sql based on object
                sql = "INSERT INTO L_PERE_tab(`id`, `income`, `pep`) " +
                "VALUES (' "+id+" ', ' "+income+" ', ' "+pep+" ' )";
         
                stmt.executeUpdate(sql);
            }

            System.out.println("Inserted records into the table...");
            conn.connect().close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ResultSet retrieveRecords() {

        // Create ResultSet object
        ResultSet rs = null;

        try {
            // Establish connection to database
            System.out.println("Retrieving records from database...");
            stmt = conn.connect().createStatement();

            // Create sql query based on the following fields: id, income & pep
            String sql = "SELECT id,income,pep from L_PERE_tab order by pep desc";

            // Attach result to rs object
            rs = stmt.executeQuery(sql);

            // Close connection
            System.out.println("Retrieved records from database...");
            conn.connect().close();

        } catch (SQLException se) {
            se.printStackTrace();
        }
    
        return rs;

    }
}