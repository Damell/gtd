package GTD.DL.DLDAO;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Singleton for holding an instance of database connection
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:52
 */
public class DatabaseConnection {

	private Connection connection;
	private int id;
	private static DatabaseConnection instance = null;
	private String username;

	public DatabaseConnection(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Připojení do databáze
	 * 
	 * @param username
	 * @param password    password
	 * @exception SQLException
	 */
	private DatabaseConnection(String username, String password)
	  throws SQLException{

	}

	/**
	 * Uzavření prípojení
	 */
	public static void closeConnection(){

	}

	/**
	 * Vrátí instanci připojení
	 * @return instance.connection
	 */
	public static Connection getConnection(){
		return null;
	}

	/**
	 * Vrátí id databázového připojení
	 * @return id
	 */
	public static int getID(){
		return 0;
	}

	/**
	 * Vrátí login přihlášeného uživatele
	 * @return username
	 */
	public String getUsername(){
		return "";
	}

	/**
	 * Přihlášení do databáze
	 * @return
	 * 
	 * @param username
	 * @param password
	 */
	public static boolean login(String username, String password){
		return false;
	}

	/**
	 * Zobrazí okno s chybovou hláškou
	 * 
	 * @param error    error
	 */
	public static void showError(String error){

	}

}