package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Osoba;
import java.util.List;

/**
 * Interface pro správu Osob v databázi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IDAOOsoba {

	/**
	 * Vytvoří nového uživatele.
	 * 
	 * @param osoba
	 */
	public boolean createOsoba(Osoba osoba);

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 */
	public boolean deactivateOsoba(Osoba osoba);

	/**
	 * Vrátí všechny osoby.
	 */
	public List getAllOsoby();

	/**
	 * Vrátí osobu podle jejího ID.
	 * 
	 * @param id
	 */
	public Osoba getOsoba(int id);
	
	/**
	 * Vrátí uživatele na základě uživatelského jména
	 * 
	 * @param username
	 */
	public Osoba getOsoba(String username);

	/**
	 * Uloží změny osoby.
	 * 
	 * @param osoba
	 */
	public boolean updateOsoba(Osoba osoba);

	/**
	 * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
	 * 
	 * @param login
	 */
	public boolean checkNewLogin(String login);

	/**
	 * Zkontroluje přihlašovací údaje.
	 * 
	 * @param login
	 * @param heslo
	 */
	public boolean checkPrihlaseni(String login, String heslo);

}