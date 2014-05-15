package GTD.BL.BLOsoby;
import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DatabaseConnection;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOPerson;
import java.util.List;

/**
 * Třída zapouzdřuje metody pro ukládání a načítání osob z databáze.
 * @version 1.0
 */
public class PersonAdmin {

	private IDAOPerson DAOOsoba;
	/**
	 * Aktuálně přihlášený uživatel.
	 */
	private Person loggedPerson;

	/**
	 *
	 */
	public PersonAdmin(){
		DAOOsoba = new DAOPerson();
	}

	/**
	 * Vytvorí nového uživatele.
	 * 
	 * @param osoba
	 * @return 
	 */
	public boolean addOsoba(Person osoba){
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 * @return 
	 */
	public boolean deactivateOsoba(Person osoba){
		return false;
                //DAO metoda pripravena
	}

	/**
	 * Vrátí uživatele.
	 * 
	 * @param id
	 * @return 
	 */
	public Person getOsoba(int id){
		return DAOOsoba.getPerson(id);
	}
        /**
	 * Vrátí prihlaseneho uživatele.
	 * 
	 * @return 
	 */
	public Person getOsobaPrihlasena(){
		return DAOOsoba.getPerson(DAOOsoba.getPersonID());
	}

	/**
	 * Vrátí všechny uživatele
	 * @return 
	 */
	public List getAllUsers() {
		return DAOOsoba.getAllPersons();
	}

	/**
	 * Uloží zmeny profilu uživatele.
	 * 
	 * @param osoba
	 * @return 
	 */
	public boolean updateOsoba(Person osoba){
		return false;
	}

	/**
	 * Zkusí přihlásit uživatele pomocí zadaných přihlašovacích údajů.
	 * 
	 * @param login
	 * @param heslo
	 * @return 
	 */
	public boolean loginOsoba(String login, String heslo){
		return DatabaseConnection.login(login, heslo);
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

}