package GTD.BL.BLAktivity;
import GTD.BL.BLOsoby.SpravceOsob;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLDAO.DAOCinnost;
import GTD.DL.DLDAO.DAOStav;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOCinnost;
import GTD.DL.DLInterfaces.IDAOStav;
import java.util.List;

/**
 * Trída pro manipulaci s cinnostmi.
 * @version 1.0
 */
public class SpravceCinnosti {

	private IDAOCinnost DAOCinnost;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private SpravceOsob spravceOsob;

	/**
	 *
	 */
	public DAOCinnost m_DAOCinnost;
        private IDAOStav DAOStav;

	/**
	 *
	 */
	public SpravceCinnosti(){
		DAOCinnost = new DAOCinnost();
                DAOStav = new DAOStav();
	}

	/**
	 * Vytvorí novou cinnost.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean addCinnost(Cinnost cinnost){
		return DAOCinnost.createCinnost(cinnost);
	}

	/**
	 * Smaže činnost. Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean deleteCinnost(Cinnost cinnost){
		return DAOCinnost.deleteCinnost(cinnost);
	}

	/**
	 * Vrátí cinnost na základě jejího ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Cinnost getCinnost(int id){
		return null;
	}

	/**
	 * Uloží změněnou činnost. Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean updateCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Vrátí všechny cinnosti osoby.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List<Cinnost> getCinnostiOsoby(Osoba osoba){
		return DAOCinnost.getCinnostiOsoby(osoba);
	}

	/**
	 * Označí činnost jako "zpracovanou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean processCinnost(Cinnost cinnost){
		cinnost.setStav(DAOStav.getCinnostZpracovanaID());
		return DAOCinnost.updateCinnost(cinnost);
	}

	/**
	 * Označí činnost jako "archivovanou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean archiveCinnost(Cinnost cinnost){
		cinnost.setStav(DAOStav.getCinnostArchivovanaID());
		return DAOCinnost.updateCinnost(cinnost);
	}

	/**
	 * Označí činnost jako "odloženou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean postponeCinnost(Cinnost cinnost){
		cinnost.setStav(DAOStav.getCinnostOdlozenaID());
		return DAOCinnost.updateCinnost(cinnost);
	}

}