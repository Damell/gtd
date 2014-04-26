package GTD.DL.DLDAO;
import GTD.DL.DLInterfaces.IDAOOsoba;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání osob z databáze.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:21
 */
public class DAOOsoba implements IDAOOsoba {

	public DAOOsoba(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Vytvoří nového uživatele.
	 * 
	 * @param osoba
	 */
	public boolean createOsoba(Osoba osoba){
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
	 * Vrátí všechny osoby.
	 */
	public List<Osoba> getAllOsoby(){
		return null;
	}

	/**
	 * Vrátí osobu podle jejího ID.
	 * 
	 * @param id
	 */
	public Osoba getOsoba(int id){
		return null;
	}

	/**
	 * Uloží změny osoby.
	 * 
	 * @param osoba
	 */
	public boolean updateOsoba(Osoba osoba){
		return false;
	}

	/**
	 * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
	 * 
	 * @param login
	 */
	public boolean checkNewLogin(string login){
		return false;
	}

	/**
	 * Zkontroluje přihlašovací údaje.
	 * 
	 * @param login
	 * @param heslo
	 */
	public boolean checkPrihlaseni(string login, string heslo){
		return false;
	}

}