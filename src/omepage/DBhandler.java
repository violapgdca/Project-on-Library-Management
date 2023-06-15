package omepage;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBhandler {
	public static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			// TODO Auto-generated method stub
			try {
				Class.forName(("com.mysql.jdbc.Driver"));
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management", "root", "Sherlyn1!");
				conn.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getClass().getName() + ":" + e.getMessage());
				System.exit(0);
			}
			System.out.println("Opened Successfully");
		}
		return conn;
	}
}
