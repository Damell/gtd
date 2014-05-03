package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOOsoba;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání osob z databáze.
 *
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:21
 */
public class DAOOsoba implements IDAOOsoba {

	private Connection con;

	public DAOOsoba() {
		con = DatabaseConnection.getConnection();

	}

	/**
	 * Vytvoří nového uživatele.
	 *
	 * @param osoba
	 */
	public boolean createOsoba(Osoba osoba) {
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 *
	 * @param osoba
	 */
	public boolean deactivateOsoba(Osoba osoba) {
		return false;
	}

	/**
	 * Vrátí všechny osoby.
	 */
	public List<Osoba> getAllOsoby() {
		return null;
	}

	/**
	 * Vrátí osobu podle jejího ID.
	 *
	 * @param id
	 */
	public Osoba getOsoba(int id) {
		return null;
	}

	/**
	 * Uloží změny osoby.
	 *
	 * @param osoba
	 */
	public boolean updateOsoba(Osoba osoba) {
		return false;
	}

	/**
	 * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
	 *
	 * @param login
	 */
	public boolean checkNewLogin(String login) {
		return false;
	}

	/**
	 * Zkontroluje přihlašovací údaje.
	 *
	 * @param login
	 * @param heslo
	 */
	public boolean checkPrihlaseni(String login, String heslo) {
		boolean authenticated = false;
		if (con != null) {
			try {
				Statement stmt = con.createStatement();
				ResultSet rset = stmt
					.executeQuery("select login, pass_hash from persons");
				while (rset.next()) {
					if (rset.getString(1).equals(login) && String.valueOf(rset.getLong(2)).equals(heslo)) {
						authenticated = true;
					}
				}
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				System.err.println("DB query error");
			}
		}
		return authenticated;
	}

}
