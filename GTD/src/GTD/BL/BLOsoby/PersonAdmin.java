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

	private IDAOPerson daoOsoba;
	/**
	 * Aktuálně přihlášený uživatel.
	 */
	private Person loggedPerson;

	/**
	 *
	 */
	public PersonAdmin(){
		daoOsoba = new DAOPerson();
	}

	/**
	 * Vytvorí nového uživatele.
	 * 
	 * @param osoba
	 */
	public boolean addOsoba(Person osoba){
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 */
	public boolean deactivateOsoba(Person osoba){
		return false;
                //DAO metoda pripravena
	}

	/**
	 * Vrátí uživatele.
	 * 
	 * @param id
	 */
	public Person getOsoba(int id){
		return daoOsoba.getPerson(id);
	}
        /**
	 * Vrátí prihlaseneho uživatele.
	 * 
	 */
	public Person getOsobaPrihlasena(){
		return daoOsoba.getPerson(daoOsoba.getPersonID());
	}

	/**
	 * Vrátí všechny uživatele
	 */
	public List getAllUsers() {
		return daoOsoba.getAllPersons();
	}

	/**
	 * Uloží zmeny profilu uživatele.
	 * 
	 * @param osoba
	 */
	public boolean updateOsoba(Person osoba){
		return false;
	}

	/**
	 * Zkusí přihlásit uživatele pomocí zadaných přihlašovacích údajů.
	 * 
	 * @param login
	 * @param heslo
	 */
	public boolean loginOsoba(String login, String heslo, String hostname, String port, String sid){
		return DatabaseConnection.login(login, heslo, hostname, port, sid);
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