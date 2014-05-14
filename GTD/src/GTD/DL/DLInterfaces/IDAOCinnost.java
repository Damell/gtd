package GTD.DL.DLInterfaces;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import java.util.List;

/**
 * Interface pro správu Cinností v databázi.
 * @version 1.0
 */
public interface IDAOCinnost {

	/**
	 * Vytvorí novou cinnost zadaných vlastností a uloží ji do databáze.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean createCinnost(Cinnost cinnost);

	/**
	 * Smaže cinnost z databáze.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean deleteCinnost(Cinnost cinnost);

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
	public Cinnost getCinnost(int id);

	/**
	 * Uloží zmenenou cinnost.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean updateCinnost(Cinnost cinnost);

	/**
	 * Vrátí všechny cinnosti patrící zadané osobe.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getCinnostiOsoby(Osoba osoba);

}