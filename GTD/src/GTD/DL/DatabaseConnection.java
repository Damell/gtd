package GTD.DL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Singleton for holding an instance of database connection
 * @author damell
 */
public class DatabaseConnection {

	private static DatabaseConnection instance = null;

	private static Connection connection;

	private DatabaseConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
		}
		try {
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@oracle.fit.cvut.cz:1521:ORACLE", "pavlim33",
					"oracleGTD");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}

	public static Connection getConnection() {
		if(instance == null) {
			instance = new DatabaseConnection();
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			connection.close();
			System.out.println("closing connection");
		} catch (SQLException e) {
			System.err.println("closing connection unsuccessful");
		}
	}

}
