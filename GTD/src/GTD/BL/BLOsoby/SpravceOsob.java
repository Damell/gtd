package GTD.BL.BLOsoby;
import GTD.DL.DLDAO.DAOOsoba;
import GTD.DL.DLDAO.DatabaseConnection;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOOsoba;
import java.util.List;

/**
 * Třída zapouzdřuje metody pro ukládání a načítání osob z databáze.
 * @version 1.0
 */
public class SpravceOsob {

	private IDAOOsoba DAOOsoba;
	/**
	 * Aktuálně přihlášený uživatel.
	 */
	private Osoba prihlasenaOsoba;

	/**
	 *
	 */
	public SpravceOsob(){
		DAOOsoba = new DAOOsoba();
	}

	/**
	 * Vytvorí nového uživatele.
	 * 
	 * @param osoba
	 * @return 
	 */
	public boolean addOsoba(Osoba osoba){
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 * @return 
	 */
	public boolean deactivateOsoba(Osoba osoba){
		return false;
                //DAO metoda pripravena
	}

	/**
	 * Vrátí uživatele.
	 * 
	 * @param id
	 * @return 
	 */
	public Osoba getOsoba(int id){
		return DAOOsoba.getOsoba(id);
	}
        /**
	 * Vrátí prihlaseneho uživatele.
	 * 
	 * @return 
	 */
	public Osoba getOsobaPrihlasena(){
		return DAOOsoba.getOsoba(DAOOsoba.getOsobaID());
	}

	/**
	 * Vrátí všechny uživatele
	 * @return 
	 */
	public List getAllUsers() {
		return DAOOsoba.getAllOsoby();
	}

	/**
	 * Uloží zmeny profilu uživatele.
	 * 
	 * @param osoba
	 * @return 
	 */
	public boolean updateOsoba(Osoba osoba){
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