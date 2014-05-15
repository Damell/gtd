package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface pro správu Kontextu v databázi.
 * @author GTD team
 * @version 1.0
 */
public interface IDAOContext {

	/**
	 * Vytvorí nový kontext zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean createContext(Context kontext);

	/**
	 * Smaže kontext z databáze.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean deleteContext(Context kontext);

	/**
	 * Vrátí všechny kontexty v systému.
	 * @return 
	 */
	public List getAllContexts();

	/**
	 * Vrátí kontext podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Context getContext(int id);

	/**
	 * Uloží zmenený kontext.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean updateContext(Context kontext);

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getContextsOfPerson(Person osoba);

}