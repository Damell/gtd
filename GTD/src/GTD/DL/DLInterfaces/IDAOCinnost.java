package GTD.DL.DLInterfaces;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import java.util.List;

/**
 * Interface pro správu Cinností v databázi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IDAOCinnost {

	/**
	 * Vytvorí novou cinnost zadaných vlastností a uloží ji do databáze.
	 * 
	 * @param cinnost
	 */
	public boolean createCinnost(Cinnost cinnost);

	/**
	 * Smaže cinnost z databáze.
	 * 
	 * @param cinnost
	 */
	public boolean deleteCinnost(Cinnost cinnost);

	/**
	 * Vrátí všechny cinnosti v systému.
	 */
	public List getAllCinnosti();

	/**
	 * Vrátí cinnost podle jejího ID.
	 * 
	 * @param id
	 */
	public Cinnost getCinnost(int id);

	/**
	 * Uloží zmenenou cinnost.
	 * 
	 * @param cinnost
	 */
	public boolean updateCinnost(Cinnost cinnost);

	/**
	 * Vrátí všechny cinnosti patrící zadané osobe.
	 * 
	 * @param osoba
	 */
	public List getCinnostiOsoby(Osoba osoba);

}