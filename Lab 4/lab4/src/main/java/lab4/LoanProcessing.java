package lab4;

// Imports
import java.sql.ResultSet;
import java.sql.SQLException;

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

        System.out.println("ID:\t\tINCOME:\t\t\t PEP:");

        // Extracting data from result set
        try {
            while(rs.next()) {
                String id = rs.getString(1);
                String removeID = id.replace("id", "");
                String income = rs.getString(2);
                String pep = rs.getString(3);

                String printout = String.format("%s\t\t%s \t\t%s", removeID, income, pep);
                System.out.println(printout);
            }

            // Close result set object
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}