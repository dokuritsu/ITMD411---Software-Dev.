package lab4;

// Imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 

public class LoanProcessing extends BankRecords{

    static JFrame f;
    static JTable j;
    static ArrayList<String[]> records = new ArrayList<>();

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

        System.out.println("ID:\t\tINCOME:\t\t\tPEP:");

        // Extracting data from result set
        try {
            while(rs.next()) {
                String id = rs.getString(1);
                String removeID = id.replace("id", "");
                String income = rs.getString(2);
                String pep = rs.getString(3);

                // Create a 1D array of the records & add to arraylist for easy access
                String[] curr = {removeID, income, pep};
                records.add(curr);
                
                String printout = String.format("%s\t\t%s \t\t%s", removeID, income, pep);
                System.out.println(printout);
            }

            // Close result set object
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        showTable(records);

    }

    public static void showTable(ArrayList<String[]> records) {
        f = new JFrame();

        // Set the Frame Title
        f.setTitle("Loan Analysis Report");

        // If frame closed
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Column names for table
        String [] columnNames = {"ID", "INCOME", "PEP"};

        // Since we know the amount of records & # of columns...
        String[][] data = new String[600][3];

        // Add the records into the 2D array
        for (int i = 0; i < records.size(); i++) {
            data[i] = records.get(i);
        }

        // Create table with data & column name
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        // Adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);

        // Size the frame
        f.pack();

        // Set the frame as visible
        f.setVisible(true);
    }
}