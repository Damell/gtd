package GTD.BL.BLOsoby;

import GTD.DL.DLInterfaces.IDAOPerson;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Třída zapouzdřuje metody pro ukládání a načítání osob z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:54
 */
public class PersonAdmin {

	private IDAOPerson DAOOsoba;
	/**
	 * Aktuálně přihlášený uživatel.
	 */
	private Person prihlasenaOsoba;



	public void finalize() throws Throwable {

	}

	public PersonAdmin(){

	}

	/**
	 * Vytvorí nového uživatele.
	 * @return
	 * 
	 * @param osoba
	 */
	public boolean addOsoba(Person osoba){
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * @return
	 * 
	 * @param osoba
	 */
	public boolean deactivateOsoba(Person osoba){
		return false;
	}

	/**
	 * Vrátí všechny uživatele
	 * @return
	 */
	public List getAllUsers(){
		return null;
	}

	/**
	 * Vrátí uživatele.
	 * @return
	 * 
	 * @param id
	 */
	public Person getOsoba(int id){
		return null;
	}

	/**
	 * Vrátí prihlaseneho uživatele.
	 * @return
	 */
	public Person getOsobaPrihlasena(){
		return null;
	}

	/**
	 * Zkontroluje, jestli už neexistuje osoba s daným uživ. jménem.
	 * 
	 * @param login    login
	 */
	private boolean checkNewLogin(String login){
		return false;
	}

	/**
	 * Zkontroluje přihlašovací údaje.
	 * 
	 * @param login
	 * @param heslo    heslo
	 */
	private boolean checkPrihlaseni(String login, String heslo){
		return false;
	}

	/**
	 * Zkusí přihlásit uživatele pomocí zadaných přihlašovacích údajů.
	 * @return
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
	 * Uloží zmeny profilu uživatele.
	 * @return
	 * 
	 * @param osoba
	 */
	public boolean updateOsoba(Person osoba){
		return false;
	}

}