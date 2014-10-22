package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface pro správu Kontextu v databázi.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:53
 */
public interface IDAOContext {

	/**
	 * Vytvorí nový kontext zadaných vlastností a uloží ho do databáze.
	 * @return
	 * 
	 * @param kontext
	 */
	public boolean createKontext(Context kontext);

	/**
	 * Smaže kontext z databáze.
	 * @return
	 * 
	 * @param kontext
	 */
	public boolean deleteKontext(Context kontext);

	/**
	 * Vrátí všechny kontexty v systému.
	 * @return
	 */
	public List getAllKontexty();

	/**
	 * Vrátí kontext podle jeho ID.
	 * @return
	 * 
	 * @param id
	 */
	public Context getKontext(int id);

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * @return
	 * 
	 * @param osoba
	 */
	public List getKontextyOsoby(Person osoba);

	/**
	 * Uloží zmenený kontext.
	 * @return
	 * 
	 * @param kontext
	 */
	public boolean updateKontext(Context kontext);

}