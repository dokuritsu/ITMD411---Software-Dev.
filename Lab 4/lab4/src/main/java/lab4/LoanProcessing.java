package lab4;

import java.sql.ResultSet;

// Imports
// import java.sql.ResultSet;
// import java.sql.SQLException;

public class LoanProcessing extends BankRecords{

    public static void main(String[] args) {
        // Object instantiations
        BankRecords br = new BankRecords();
        Dao dao = new Dao();

        // Read data from csv file...
        br.readData();

        // Create a table; Comment out after running once
        dao.createTables();

        // Insert the records
        dao.insertRecords(br.getbArrayList());

        // Fill the result set object
        ResultSet rs = dao.retrieveRecords();
    }
}