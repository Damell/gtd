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
     */
    public boolean createPerson(Person osoba);

    /**
     * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
     *
     * @param osoba
     */
    public boolean deactivatePerson(Person osoba);

    /**
     * Vrátí všechny osoby.
     */
    public List getAllPersons();

    /**
     * Vrátí osobu podle jejího ID.
     *
     * @param id
     */
    public Person getPerson(int id);

    /**
     * Vrátí ID prihlasene osoby.
     *
     */
    public int getPersonID();

    /**
     * Vrátí uživatele na základě uživatelského jména
     *
     * @param username
     */
    public Person getPerson(String username);

    /**
     * Uloží změny osoby.
     *
     * @param osoba
     */
    public boolean updatePerson(Person osoba);

    /**
     * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
     *
     * @param login
     */
    public boolean checkNewLogin(String login);

    /**
     * Zkontroluje přihlašovací údaje. Zda je ucet povolen.
     *
     * @param login
     */
    public boolean checkLogin(String login);

}
