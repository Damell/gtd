package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOPerson;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání osob z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class DAOPerson implements IDAOPerson {



	public void finalize() throws Throwable {

	}

	/**
	 * Konstruktor osoby
	 */
	public DAOPerson(){

	}

	/**
	 * Vytvoří nového uživatele.
	 * @return
	 * 
	 * @param osoba
	 */
	public boolean createOsoba(Person osoba){
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
	 * Vrátí všechny osoby.
	 * @return List<Osoba>
	 */
	public List<Person> getAllOsoby(){
		return null;
	}

	/**
	 * Vráti osoby dle zadaného loginu
	 * @param login
	 * @return osoba
	 * 
	 * @param username
	 */
	@Override
	public Person getOsoba(String username){
		return null;
	}

	/**
	 * Vrátí osobu podle jejího ID.
	 * @return osoba
	 * 
	 * @param id
	 */
	public Person getOsoba(int id){
		return null;
	}

	/**
	 * Vrátí ID prihlasene ososby.
	 * @return id
	 */
	public int getOsobaID(){
		return 0;
	}

	/**
	 * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
	 * @return
	 * 
	 * @param login
	 */
	public boolean checkNewLogin(String login){
		return false;
	}

	/**
	 * Zkontroluje přihlašovací údaje. Zda je ucet povolen.
	 * @return true=povolen, false=nepovolen
	 * 
	 * @param login
	 */
	public boolean checkPrihlaseni(String login){
		return false;
	}

	/**
	 * Uloží změny osoby.
	 * @return
	 * 
	 * @param osoba
	 */
	public boolean updateOsoba(Person osoba){
		return false;
	}

}