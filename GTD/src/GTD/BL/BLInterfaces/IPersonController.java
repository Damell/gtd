package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface defines the way BL and PL communicates concerning persons
 * @author GTD team
 * @version 1.0
 */
public interface IPersonController {

	/**
	 * Zkusí přihlásit uživatele s danými přihlašovacími údaji.
	 * 
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public boolean loginPerson(String username, String password);

	/**
	 * Vytvoří nového uživatele.
	 * 
	 * @param person
	 * @return boolean
	 */
	public boolean addPerson(Person person);

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param person
	 * @return boolean
	 */
	public boolean deactivatePerson(Person person);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

	/**
	 * Vrátí přihlášeného uživatele.
	 * @return 
	 */
	public Person getLoggedPerson();

	/**
	 * Vrátí uživatele podle ID.
	 * @param id
	 * @return 
	 */
	public Person getPerson(int id);

	/**
	 * Vrátí všechny uživatele
	 * @return boolean
	 */
	public List getAllUsers();

	/**
	 * Odhlásí aktuálního uživatele.
	 */
	public void logout();

}