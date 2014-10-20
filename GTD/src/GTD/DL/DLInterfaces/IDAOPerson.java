package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Person;

/**
 * Interface pro správu Osob v databázi.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:53
 */
public interface IDAOPerson {

	/**
	 * Vytvoří nového uživatele.
	 * @return
	 * 
	 * @param osoba
	 */
	public boolean createOsoba(Person osoba);

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * @return
	 * 
	 * @param osoba
	 */
	public boolean deactivateOsoba(Person osoba);

	/**
	 * Vrátí všechny osoby.
	 * @return
	 */
	public List getAllOsoby();

	/**
	 * Vrátí uživatele na základě uživatelského jména
	 * @return
	 * 
	 * @param username
	 */
	public Person getOsoba(String username);

	/**
	 * Vrátí osobu podle jejího ID.
	 * @return
	 * 
	 * @param id
	 */
	public Person getOsoba(int id);

	/**
	 * Vrátí ID prihlasene osoby.
	 * @return
	 */
	public int getOsobaID();

	/**
	 * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
	 * @return
	 * 
	 * @param login
	 */
	public boolean checkNewLogin(String login);

	/**
	 * Zkontroluje přihlašovací údaje. Zda je ucet povolen.
	 * @return
	 * 
	 * @param login
	 */
	public boolean checkPrihlaseni(String login);

	/**
	 * Uloží změny osoby.
	 * @return
	 * 
	 * @param osoba
	 */
	public boolean updateOsoba(Person osoba);

}