package GTD.DL.DLDAO;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOCinnost;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání cinností z databáze.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:20
 */
public class DAOCinnost implements IDAOCinnost {

	public DAOCinnost(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Vytvorí novou cinnost zadaných vlastností a uloží ji do databáze.
	 * 
	 * @param cinnost
	 */
	public boolean createCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Smaže cinnost z databáze.
	 * 
	 * @param cinnost
	 */
	public boolean deleteCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Vrátí všechny cinnosti v systému.
	 */
	public List getAllCinnosti(){
		return null;
	}

	/**
	 * Vrátí cinnost podle jejího ID.
	 * 
	 * @param id
	 */
	public Cinnost getCinnost(int id){
		return null;
	}

	/**
	 * Uloží zmenenou cinnost.
	 * 
	 * @param cinnost
	 */
	public boolean updateCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Vrátí všechny cinnosti patrící zadané osobe.
	 * 
	 * @param osoba
	 */
	public List getCinnostiOsoby(Osoba osoba){
		return null;
	}

}