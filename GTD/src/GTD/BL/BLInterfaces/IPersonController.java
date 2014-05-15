package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Osobami.
 * @version 1.0
 */
public interface IPersonController {

	/**
	 * Zkusí přihlásit uživatele s danými přihlašovacími údaji.
	 * 
	 * @param login
	 * @param heslo
	 * @return 
	 */
	public boolean loginOsoba(String login, String heslo);

	/**
	 * Vytvoří nového uživatele.
	 * 
	 * @param osoba
	 * @return 
	 */
	public boolean addOsoba(Person osoba);

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 * @return 
	 */
	public boolean deactivateOsoba(Person osoba);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

	/**
	 * Vrátí přihlášeného uživatele.
	 * @return 
	 */
	public Person getPrihlasenaOsoba();

	/**
	 * Vrátí uživatele podle ID.
	 * @param id
	 * @return 
	 */
	public Person getOsoba(int id);

	/**
	 * Vrátí všechny uživatele
	 * @return 
	 */
	public List getAllUsers();

	/**
	 * Odhlásí aktuálního uživatele.
	 */
	public void logout();

}