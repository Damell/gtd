package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Osoba;
import java.util.List;

/**
 * Interface pro správu Osob v databázi.
 *
 * @version 1.0
 */
public interface IDAOOsoba {

    /**
     * Vytvoří nového uživatele.
     *
     * @param osoba
	 * @return 
     */
    public boolean createOsoba(Osoba osoba);

    /**
     * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
     *
     * @param osoba
	 * @return 
     */
    public boolean deactivateOsoba(Osoba osoba);

    /**
     * Vrátí všechny osoby.
	 * @return 
     */
    public List getAllOsoby();

    /**
     * Vrátí osobu podle jejího ID.
     *
     * @param id
	 * @return 
     */
    public Osoba getOsoba(int id);

    /**
     * Vrátí ID prihlasene osoby.
     *
	 * @return 
     */
    public int getOsobaID();

    /**
     * Vrátí uživatele na základě uživatelského jména
     *
     * @param username
	 * @return 
     */
    public Osoba getOsoba(String username);

    /**
     * Uloží změny osoby.
     *
     * @param osoba
	 * @return 
     */
    public boolean updateOsoba(Osoba osoba);

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
    public boolean checkPrihlaseni(String login);

}
