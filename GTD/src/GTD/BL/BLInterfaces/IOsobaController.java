package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Osoba;
import java.util.List;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Osobami.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IOsobaController {

	/**
	 * Zkusí přihlásit uživatele s danými přihlašovacími údaji.
	 * 
	 * @param login
	 * @param heslo
	 */
	public boolean loginOsoba(String login, String heslo);

	/**
	 * Vytvoří nového uživatele.
	 * 
	 * @param osoba
	 */
	public boolean addOsoba(Osoba osoba);

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 */
	public boolean deactivateOsoba(Osoba osoba);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

	/**
	 * Vrátí přihlášeného uživatele.
	 */
	public Osoba getPrihlasenaOsoba();

	/**
	 * Vrátí uživatele podle ID.
	 */
	public Osoba getOsoba(int id);

	/**
	 * Vrátí všechny uživatele
	 */
	public List getAllUsers();

	/**
	 * Odhlásí aktuálního uživatele.
	 */
	public void logout();

}