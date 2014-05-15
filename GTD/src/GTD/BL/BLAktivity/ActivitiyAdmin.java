package GTD.BL.BLAktivity;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLDAO.DAOActivity;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOActivity;
import GTD.DL.DLInterfaces.IDAOState;
import java.util.List;

/**
 * Trída pro manipulaci s cinnostmi.
 * @version 1.0
 */
public class ActivitiyAdmin {

	private IDAOActivity DAOCinnost;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private PersonAdmin spravceOsob;

	/**
	 *
	 */
	public DAOActivity m_DAOCinnost;
        private IDAOState DAOStav;

	/**
	 *
	 */
	public ActivitiyAdmin(){
		DAOCinnost = new DAOActivity();
                DAOStav = new DAOState();
	}

	/**
	 * Vytvorí novou cinnost.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean addCinnost(Activity cinnost){
		return DAOCinnost.createCinnost(cinnost);
	}

	/**
	 * Smaže činnost. Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean deleteCinnost(Activity cinnost){
		return DAOCinnost.deleteCinnost(cinnost);
	}

	/**
	 * Vrátí cinnost na základě jejího ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Activity getCinnost(int id){
		return null;
	}

	/**
	 * Uloží změněnou činnost. Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean updateCinnost(Activity cinnost){
		return false;
	}

	/**
	 * Vrátí všechny cinnosti osoby.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List<Activity> getCinnostiOsoby(Person osoba){
		return DAOCinnost.getCinnostiOsoby(osoba);
	}

	/**
	 * Označí činnost jako "zpracovanou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean processCinnost(Activity cinnost){
		cinnost.setStav(DAOStav.getCinnostZpracovanaID());
		return DAOCinnost.updateCinnost(cinnost);
	}

	/**
	 * Označí činnost jako "archivovanou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean archiveCinnost(Activity cinnost){
		cinnost.setStav(DAOStav.getCinnostArchivovanaID());
		return DAOCinnost.updateCinnost(cinnost);
	}

	/**
	 * Označí činnost jako "odloženou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean postponeCinnost(Activity cinnost){
		cinnost.setStav(DAOStav.getCinnostOdlozenaID());
		return DAOCinnost.updateCinnost(cinnost);
	}

}