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
	 */
	public boolean createContext(Context kontext);

	/**
	 * Smaže kontext z databáze.
	 * 
	 * @param kontext
	 */
	public boolean deleteContext(Context kontext);

	/**
	 * Vrátí všechny kontexty v systému.
	 */
	public List getAllContexts();

	/**
	 * Vrátí kontext podle jeho ID.
	 * 
	 * @param id
	 */
	public Context getContext(int id);

	/**
	 * Uloží zmenený kontext.
	 * 
	 * @param kontext
	 */
	public boolean updateContext(Context kontext);

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * 
	 * @param osoba
	 */
	public List getContextsOfPerson(Person osoba);

}