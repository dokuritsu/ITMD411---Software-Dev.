package lab4;

// Imports
// import java.sql.ResultSet;
// import java.sql.SQLException;

public class LoanProcessing extends BankRecords{

    public static void main(String[] args) {
        // Object instantiations
        BankRecords br = new BankRecords();

        // Read data from csv file...
        br.readData();
    }
}