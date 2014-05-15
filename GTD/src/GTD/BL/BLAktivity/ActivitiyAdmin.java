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
 * @author GTD team
 * @version 1.0
 */
public class ActivitiyAdmin {

	private IDAOActivity DAOActivity;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private PersonAdmin personAdmin;

	private IDAOState DAOState;

	/**
	 *
	 */
	public ActivitiyAdmin(){
		DAOActivity = new DAOActivity();
		DAOState = new DAOState();
	}

	/**
	 * Vytvorí novou cinnost.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean addActivity(Activity cinnost){
		return DAOActivity.createActivity(cinnost);
	}

	/**
	 * Smaže činnost. Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean deleteActivity(Activity cinnost){
		return DAOActivity.deleteActivity(cinnost);
	}

	/**
	 * Vrátí cinnost na základě jejího ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Activity getActivity(int id){
		return null;
	}

	/**
	 * Uloží změněnou činnost. Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean updateActivity(Activity cinnost){
		return false;
	}

	/**
	 * Vrátí všechny cinnosti osoby.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List<Activity> getActivitiesOfPerson(Person osoba){
		return DAOActivity.getActivitiesOfPerson(osoba);
	}

	/**
	 * Označí činnost jako "zpracovanou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean processActivity(Activity cinnost){
		cinnost.setStav(DAOState.getCinnostZpracovanaID());
		return DAOActivity.updateActivity(cinnost);
	}

	/**
	 * Označí činnost jako "archivovanou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean archiveActivity(Activity cinnost){
		cinnost.setStav(DAOState.getCinnostArchivovanaID());
		return DAOActivity.updateActivity(cinnost);
	}

	/**
	 * Označí činnost jako "odloženou". Toto může udělat pouze vlastník činnosti.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean postponeActivity(Activity cinnost){
		cinnost.setStav(DAOState.getCinnostOdlozenaID());
		return DAOActivity.updateActivity(cinnost);
	}

}