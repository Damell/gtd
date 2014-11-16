package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface pro správu Cinností v databázi.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:53
 */
public interface IDAOActivity extends IDAOGeneric<Activity>
{
	public List<Activity> getAll();
	
	public Activity get(int id);
	
	
//	/**
//	 * Vytvorí novou cinnost zadaných vlastností a uloží ji do databáze.
//	 * @return
//	 * 
//	 * @param cinnost
//	 */
//	public boolean createCinnost(Activity cinnost);
//
//	/**
//	 * Smaže cinnost z databáze.
//	 * @return
//	 * 
//	 * @param cinnost
//	 */
//	public boolean deleteCinnost(Activity cinnost);
//
//	/**
//	 * Vrátí všechny cinnosti v systému.
//	 * @return
//	 */
//	public List getAllCinnosti();
//
//	/**
//	 * Vrátí cinnost podle jejího ID.
//	 * @return
//	 * 
//	 * @param id
//	 */
//	public Activity getCinnost(int id);

	/**
	 * Vrátí všechny cinnosti patrící zadané osobe.
	 * @return
	 * 
	 * @param osoba
	 */
	public List<Activity> getCinnostiOsoby(Person osoba);

//	/**
//	 * Uloží zmenenou cinnost.
//	 * @return
//	 * 
//	 * @param cinnost
//	 */
//	public boolean updateCinnost(Activity cinnost);

}