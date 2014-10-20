package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Person;

/**
 * Interface defines the way BL and PL communicates concerning persons
 * @author GTD team
 * @version 1.0
 * @created 19-10-2014 12:30:53
 */
public interface IPersonController {

	/**
	 * Vytvoří nového uživatele.
	 * @return boolean
	 * 
	 * @param person
	 */
	public boolean addPerson(Person person);

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * @return boolean
	 * 
	 * @param person
	 */
	public boolean deactivatePerson(Person person);

	/**
	 * Vrátí všechny uživatele
	 * @return boolean
	 */
	public List getAllUsers();

	/**
	 * Vrátí přihlášeného uživatele.
	 * @return
	 */
	public Person getLoggedPerson();

	/**
	 * Vrátí uživatele podle ID.
	 * @return
	 * 
	 * @param id
	 */
	public Person getPerson(int id);

	/**
	 * Zkusí přihlásit uživatele s danými přihlašovacími údaji.
	 * @return boolean
	 * 
	 * @param username
	 * @param password
	 */
	public boolean loginPerson(String username, String password);

	/**
	 * Odhlásí aktuálního uživatele.
	 */
	public void logout();

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

}