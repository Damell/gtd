package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface pro správu Osob v databázi.
 *
 * @author GTD team
 * @version 1.0
 */
public interface IDAOPerson {

    /**
     * Vytvoří nového uživatele.
     *
     * @param osoba
	 * @return 
     */
    public boolean createPerson(Person osoba);

    /**
     * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
     *
     * @param osoba
	 * @return 
     */
    public boolean deactivatePerson(Person osoba);

    /**
     * Vrátí všechny osoby.
	 * @return 
     */
    public List getAllPersons();

    /**
     * Vrátí osobu podle jejího ID.
     *
     * @param id
	 * @return 
     */
    public Person getPerson(int id);

    /**
     * Vrátí ID prihlasene osoby.
     *
	 * @return 
     */
    public int getPersonID();

    /**
     * Vrátí uživatele na základě uživatelského jména
     *
     * @param username
	 * @return 
     */
    public Person getPerson(String username);

    /**
     * Uloží změny osoby.
     *
     * @param osoba
	 * @return 
     */
    public boolean updatePerson(Person osoba);

    /**
     * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
     *
     * @param login
	 * @return 
     */
    public boolean checkNewLogin(String login);

    /**
     * Zkontroluje přihlašovací údaje. Zda je ucet povolen.
     *
     * @param login
	 * @return 
     */
    public boolean checkLogin(String login);

}
