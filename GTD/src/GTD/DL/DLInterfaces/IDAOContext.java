package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface pro správu Kontextu v databázi.
 * @version 1.0
 */
public interface IDAOContext {

	/**
	 * Vytvorí nový kontext zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean createKontext(Context kontext);

	/**
	 * Smaže kontext z databáze.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean deleteKontext(Context kontext);

	/**
	 * Vrátí všechny kontexty v systému.
	 * @return 
	 */
	public List getAllKontexty();

	/**
	 * Vrátí kontext podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Context getKontext(int id);

	/**
	 * Uloží zmenený kontext.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean updateKontext(Context kontext);

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getKontextyOsoby(Person osoba);

}