package GTD.BL.BLAktivity;

import GTD.DL.DLInterfaces.IDAOActivity;
import GTD.DL.DLInterfaces.IDAOState;
import GTD.DL.DLDAO.DAOActivity;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.ActivityState;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Trída pro manipulaci s cinnostmi.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:50
 */
public class ActivitiyAdmin {

	private IDAOActivity DAOCinnost;
	private IDAOState DAOStav;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private PersonAdmin spravceOsob;

	

	public void finalize() throws Throwable {

	}

	public ActivitiyAdmin(){

	}

	public void setDAOCinnost(IDAOActivity dao) {
		DAOCinnost = dao;
	}

	public void setDAOStav(IDAOState DAOStav)
	{
		this.DAOStav = DAOStav;
	}
	
	
	/**
	 * Vytvorí novou cinnost.
	 * @return
	 * 
	 * @param cinnost
	 */
	public void addCinnost(Activity cinnost){
		DAOCinnost.create(cinnost);
	}

	/**
	 * Označí činnost jako "archivovanou". Toto může udělat pouze vlastník činnosti.
	 * @return
	 * 
	 * @param cinnost
	 */
	public void archiveCinnost(Activity cinnost){
		ActivityState archivovana = DAOStav.getCinnostArchivovana();
		cinnost.setStav(archivovana);
		DAOCinnost.update(cinnost);
	}

	/**
	 * Smaže činnost. Toto může udělat pouze vlastník činnosti.
	 * @return
	 * 
	 * @param cinnost
	 */
	public void deleteCinnost(Activity cinnost){
		DAOCinnost.delete(cinnost);
	}

	/**
	 * Vrátí cinnost na základě jejího ID.
	 * @return
	 * 
	 * @param id
	 */
	public Activity getCinnost(int id){
		return DAOCinnost.get(id);
	}

	/**
	 * Vrátí všechny cinnosti osoby.
	 * @return
	 * 
	 * @param osoba
	 */
	public List<Activity> getCinnostiOsoby(Person osoba){
		return DAOCinnost.getCinnostiOsoby(osoba);
	}

	/**
	 * Označí činnost jako "odloženou". Toto může udělat pouze vlastník činnosti.
	 * @return
	 * 
	 * @param cinnost
	 */
	public void postponeCinnost(Activity cinnost){
		ActivityState odlozena = DAOStav.getCinnostOdlozena();
		cinnost.setStav(odlozena);
		DAOCinnost.update(cinnost);
	}

	/**
	 * Označí činnost jako "zpracovanou". Toto může udělat pouze vlastník činnosti.
	 * @return
	 * 
	 * @param cinnost
	 */
	public void processCinnost(Activity cinnost){
		ActivityState zpracovana = DAOStav.getCinnostZpracovana();
		cinnost.setStav(zpracovana);
		DAOCinnost.update(cinnost);
	}

	/**
	 * Uloží změněnou činnost. Toto může udělat pouze vlastník činnosti.
	 * @return
	 * 
	 * @param cinnost
	 */
	public void updateCinnost(Activity cinnost){ // TODO steklsim updateCinnnost() je asi k nicemu
		DAOCinnost.update(cinnost);
	}

}