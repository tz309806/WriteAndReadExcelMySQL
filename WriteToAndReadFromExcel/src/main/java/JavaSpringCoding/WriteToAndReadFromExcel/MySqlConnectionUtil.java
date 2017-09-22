package JavaSpringCoding.WriteToAndReadFromExcel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySqlConnectionUtil {

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306";
	final String USER = "root";
	final String PASS = "Tz64220866";

	public void MySqlconnector() {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database.");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("Creating Statement");
			stmt = (Statement) conn.createStatement();
			String sql;
			sql = "SELECT ADDRESS_ID, ADDRESS, PHONE FROM SAKILA.ADDRESS";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int address_id = rs.getInt("ADDRESS_ID");
				String phone = rs.getString("PHONE");
				String address = rs.getString("ADDRESS");
				

				// Display values
				System.out.print("Address ID: " + address_id);
				System.out.print(", Phone number: " + phone);
				System.out.print(", Address: " + address);


			}
			rs.close();
			System.out.println("Ruleset closed");
			stmt.close();
			System.out.println("Statement closed");
			conn.close();
			System.out.println("Connection closed");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		}
	}
	public static void main(String[] args){
		
	}
}