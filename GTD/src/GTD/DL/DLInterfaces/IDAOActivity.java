package GTD.DL.DLInterfaces;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface pro správu Cinností v databázi.
 * @version 1.0
 */
public interface IDAOActivity {

	/**
	 * Vytvorí novou cinnost zadaných vlastností a uloží ji do databáze.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean createCinnost(Activity cinnost);

	/**
	 * Smaže cinnost z databáze.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean deleteCinnost(Activity cinnost);

	/**
	 * Vrátí všechny cinnosti v systému.
	 * @return 
	 */
	public List getAllCinnosti();

	/**
	 * Vrátí cinnost podle jejího ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Activity getCinnost(int id);

	/**
	 * Uloží zmenenou cinnost.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean updateCinnost(Activity cinnost);

	/**
	 * Vrátí všechny cinnosti patrící zadané osobe.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getCinnostiOsoby(Person osoba);

}