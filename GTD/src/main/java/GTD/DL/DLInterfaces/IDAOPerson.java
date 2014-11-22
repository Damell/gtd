package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface pro správu Osob v databázi.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:53
 */
public interface IDAOPerson extends IDAOGeneric<Person>
{
	public List<Person> getAll();
	
	public Person get(int id);
	
	


	/**
	 * Vrátí uživatele na základě uživatelského jména
	 * @return
	 * 
	 * @param username
	 */
	public Person getOsoba(String username);




//	/**
//	 * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
//	 * @return
//	 * 
//	 * @param login
//	 */
//	public boolean checkNewLogin(String login);
//
//	/**
//	 * Zkontroluje přihlašovací údaje. Zda je ucet povolen. 
//	 * @return
//	 * 
//	 * @param login
//	 */
//	public boolean checkPrihlaseni(String login);

//	/**
//	 * Uloží změny osoby.
//	 * @return
//	 * 
//	 * @param osoba
//	 */
//	public boolean updateOsoba(Person osoba);

}