package GTD.DL.DLInterfaces;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface pro správu Cinností v databázi.
 * @author GTD team
 * @version 1.0
 */
public interface IDAOActivity {

	/**
	 * Vytvorí novou cinnost zadaných vlastností a uloží ji do databáze.
	 * 
	 * @param cinnost
	 */
	public boolean createActivity(Activity cinnost);

	/**
	 * Smaže cinnost z databáze.
	 * 
	 * @param cinnost
	 */
	public boolean deleteActivity(Activity cinnost);

	/**
	 * Vrátí všechny cinnosti v systému.
	 */
	public List getAllActivities();

	/**
	 * Vrátí cinnost podle jejího ID.
	 * 
	 * @param id
	 */
	public Activity getActivity(int id);

	/**
	 * Uloží zmenenou cinnost.
	 * 
	 * @param cinnost
	 */
	public boolean updateActivity(Activity cinnost);

	/**
	 * Vrátí všechny cinnosti patrící zadané osobe.
	 * 
	 * @param osoba
	 */
	public List getActivitiesOfPerson(Person osoba);

}