package GTD.DL.DLDAO;

import GTD.PL.PLView.GTDGUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton for holding an instance of database connection
 * @author GTD team
 * @version 1.0
 */
public class DatabaseConnection {

	private static DatabaseConnection instance = null;

	private Connection connection;
	private int id;
	private String username;

        /**
        * Připojení do databáze
        * 
        * @param username
        * @param password
        */
	private DatabaseConnection(String username, String password, String hostname, String port, String sid) throws SQLException {
		// pavlim33 oracleGTD
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your Oracle JDBC Driver?");
			//e.printStackTrace();
			return;
		}
		connection = DriverManager.getConnection(
			"jdbc:oracle:thin:@" + hostname + ":" + port + ":" + sid,
			username,
			password);
	}

	/**
	 * Vrátí instanci připojení
	 * 
	 * @return instance.connection
	 */
	public static Connection getConnection() {
		if(instance == null) {
			return null;
		}
		return instance.connection;
	}

	/**
	 * Přihlášení do databáze
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean login(String username, String password, String hostname, String port, String sid) {
		if(instance == null) {
			try {
				instance = new DatabaseConnection(username, password, hostname, port, sid);
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				//e.printStackTrace();
				instance = null;
				return false;
			}
		}
		return true;
	}

	/**
	 * Uzavření prípojení
	 */
	public static void closeConnection() {
		try {
			if(instance != null) {
				instance.connection.close();
				System.out.println("closing connection");
			}
		} catch (SQLException e) {
			System.err.println("closing connection unsuccessful");
		}
	}

	/**
	 * Vrátí id databázového připojení
	 * 
	 * @return id
	 */
	public static int getID() {
		return DatabaseConnection.instance.id;
	}

	/**
	 * Vrátí login přihlášeného uživatele
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Zobrazí okno s chybovou hláškou
	 * 
	 * @param error
	 */
	public static void showError(String error) {
		GTDGUI.getGTDGUI().showError(error);
		System.err.println(error);
	}
}
