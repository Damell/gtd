package GTD.BL.BLAktivity;
import GTD.BL.BLOsoby.SpravceOsob;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLDAO.DAOCinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOCinnost;
import java.util.List;

/**
 * Trída pro manipulaci s cinnostmi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:23
 */
public class SpravceCinnosti {

	private IDAOCinnost DAOCinnost;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private SpravceOsob spravceOsob;
	public DAOCinnost m_DAOCinnost;

	public SpravceCinnosti(){
		DAOCinnost = new DAOCinnost();
	}

	/**
	 * Vytvorí novou cinnost.
	 * 
	 * @param cinnost
	 */
	public boolean addCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Smaže činnost. Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 */
	public boolean deleteCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Vrátí cinnost na základě jejího ID.
	 * 
	 * @param id
	 */
	public Cinnost getCinnost(int id){
		return null;
	}

	/**
	 * Uloží změněnou činnost. Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 */
	public boolean updateCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Vrátí všechny cinnosti osoby.
	 * 
	 * @param osoba
	 */
	public List<Cinnost> getCinnostiOsoby(Osoba osoba){
		return DAOCinnost.getCinnostiOsoby(osoba);
	}

	/**
	 * Označí činnost jako "zpracovanou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 */
	public boolean processCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Označí činnost jako "archivovanou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 */
	public boolean archiveCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Označí činnost jako "odloženou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 */
	public boolean postponeCinnost(Cinnost cinnost){
		return false;
	}

}