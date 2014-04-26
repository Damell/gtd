package GTD.BL.BLOsoby;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOOsoba;

/**
 * Třída zapouzdřuje metody pro ukládání a načítání osob z databáze.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class SpravceOsob {

	private IDAOOsoba DAOOsoba;
	/**
	 * Aktuálně přihlášený uživatel.
	 */
	private Osoba prihlasenaOsoba;

	public SpravceOsob(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Vytvorí nového uživatele.
	 * 
	 * @param osoba
	 */
	public boolean addOsoba(Osoba osoba){
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 */
	public boolean deactivateOsoba(Osoba osoba){
		return false;
	}

	/**
	 * Vrátí uživatele.
	 * 
	 * @param id
	 */
	public Osoba getOsoba(int id){
		return null;
	}

	/**
	 * Uloží zmeny profilu uživatele.
	 * 
	 * @param osoba
	 */
	public boolean updateOsoba(Osoba osoba){
		return false;
	}

	/**
	 * Zkusí přihlásit uživatele pomocí zadaných přihlašovacích údajů.
	 * 
	 * @param login
	 * @param heslo
	 */
	public boolean loginOsoba(String login, String heslo){
		return false;
	}

	/**
	 * Odhlásí aktuálního uživatele.
	 */
	public void logout(){

	}

	/**
	 * Zkontroluje, jestli už neexistuje osoba s daným uživ. jménem.
	 * 
	 * @param login
	 */
	private boolean checkNewLogin(String login){
		return false;
	}

	/**
	 * Zkontroluje přihlašovací údaje.
	 * 
	 * @param login
	 * @param heslo
	 */
	private boolean checkPrihlaseni(String login, String heslo){
		return false;
	}

}