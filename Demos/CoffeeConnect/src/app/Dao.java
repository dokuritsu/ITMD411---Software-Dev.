package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Dao {

	// Code database URL
	static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/411labs?autoReconnect=true&useSSL=false";
	// Database credentials
	static final String USER = "db411", PASS = "411";

	static Statement stmt;

	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}

	public Dao() {
		try {
			// initialize any objects here
			stmt = connect().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void menu() {
		String menuItems = "1.Create table\n2.Insert recs\n3.Update recs\n4.Delete recs\n5.View Recs (Console)\n6.View Recs (Window)\n7.Exit";
		System.out.println(menuItems);
	}

	public static void createTables() {
		// finish implementing...
	}

	public static void updates(String desc, int id) {
		String SQL = "Update papa_coffee SET prod_desc = ? WHERE id = ?";
		// use prepared statement
		try (PreparedStatement pstmt = connect().prepareStatement(SQL)) {
			pstmt.setString(1, desc);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deletes(int id) {
		// finish implementing...
	}

	public static void inserts() throws InterruptedException {

		// create an insert statement
		String sql = "";

		System.out.println("Inserting records...");
		try {
			Thread.sleep(3000); // sleep for 3 seconds
			sql = "Insert Into papa_coffee(prod_name,prod_desc,prod_price)"
					+ " values('J Papa''s','Strong Brew',29.55)";
			// execute query for insert(s)
			stmt = connect().createStatement();
			stmt.executeUpdate(sql);

			// System.out.println("Records inserted");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void retrieveRecords() {

		try {
			// get record data from result set object
			ResultSet rs = stmt.executeQuery("Select * from papa_coffee");
			while (rs.next()) {

				int id = rs.getInt(1);
				String desc = rs.getString("prod_desc");
				double price = Double.parseDouble(rs.getString("prod_price"));

				// print records to console
				System.out.println("Id : " + id + "\t  Desc. : " + desc + "\t  Price: " + price);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void retrieveRecords2() {

		try {
			// get record data from result set object
			ResultSet rs = stmt.executeQuery("Select * from papa_coffee");
			new TableViewer().runView(rs); // display records in window
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws InterruptedException {

		new Dao();
		Scanner sc = new Scanner(System.in);
		do {
			menu();
			int choice = sc.nextInt();
			switch (choice) {
			case 1:// createTables(); 
				break;
			case 2:
				inserts();
				break;
			case 3:
				updates("Nova Coffee", 1);
				break;
			case 4:
				// delete some id
				// deletes(id);
				break;
			case 5:
				retrieveRecords();
				break;
			case 6:
				retrieveRecords2();
				break;
			case 7:
				System.out.println("bye for now!");
				System.exit(0);
			}
		    System.out.println();
		} while (true);
	}
}