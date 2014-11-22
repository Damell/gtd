package GTD.BL.BLOsoby;

import GTD.DL.DLDAO.DAOException;
import GTD.DL.DLInterfaces.IDAOPerson;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.PersonState;
import GTD.DL.DLInterfaces.IDAOState;
import java.util.List;

/**
 * Třída zapouzdřuje metody pro ukládání a načítání osob z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:54
 */
public class PersonAdmin {

	private IDAOPerson DAOOsoba;
	private IDAOState DAOStav;
	/**
	 * Aktuálně přihlášený uživatel.
	 */
	private Person prihlasenaOsoba;



	public void finalize() throws Throwable {

	}

	public PersonAdmin(){

	}

	public void setDAOOsoba(IDAOPerson DAOOsoba)
	{
		this.DAOOsoba = DAOOsoba;
	}

	public void setDAOStav(IDAOState DAOStav)
	{
		this.DAOStav = DAOStav;
	}
	
	

	/**
	 * Vytvorí nového uživatele.
	 * @return
	 * 
	 * @param osoba
	 */
	public void addOsoba(Person osoba){
		DAOOsoba.create(osoba);
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * @return
	 * 
	 * @param osoba
	 */
	public void deactivateOsoba(Person osoba){
		// if logged-in user has role ROLE_ADMIN
		PersonState deactivated = DAOStav.getOsobaNeaktivni();
		osoba.setStav(deactivated);
		DAOOsoba.update(osoba);
	}

	/**
	 * Vrátí všechny uživatele
	 * @return
	 */
	public List<Person> getAllUsers(){
		return DAOOsoba.getAll();
	}

	/**
	 * Vrátí uživatele.
	 * @return
	 * 
	 * @param id
	 */
	public Person getOsoba(int id){
		return DAOOsoba.get(id);
	}

	/**
	 * Vrátí prihlaseneho uživatele.
	 * @return
	 */
	public Person getOsobaPrihlasena(){
		throw new UnsupportedOperationException("Not supported yet."); 
		// TODO steklsim bude getOsobaPrihlasena() k necemu?
	}

	/**
	 * Zkontroluje, jestli už neexistuje osoba s daným uživ. jménem.
	 * 
	 * @param login    login
	 */
	private boolean checkNewLogin(String login){
		return (DAOOsoba.getOsoba(login) == null);
	}

	/**
	 * Zkontroluje přihlašovací údaje.
	 * 
	 * @param login
	 * @param heslo    heslo
	 */
	private boolean checkPrihlaseni(String login, String heslo){
		throw new UnsupportedOperationException("Not supported yet."); 
		// TODO steklsim bude autentizace v PersonAdminu? (podle me by nemela)
	}

	/**
	 * Zkusí přihlásit uživatele pomocí zadaných přihlašovacích údajů.
	 * @return
	 * 
	 * @param login
	 * @param heslo
	 */
	public boolean loginOsoba(String login, String heslo){
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	/**
	 * Odhlásí aktuálního uživatele.
	 */
	public void logout(){
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Uloží zmeny profilu uživatele.
	 * @return
	 * 
	 * @param osoba
	 */
	public void updateOsoba(Person osoba){
		DAOOsoba.update(osoba); // TODO steklsim je updateOsoba() v PersonAdminu k necemu?
	}

}