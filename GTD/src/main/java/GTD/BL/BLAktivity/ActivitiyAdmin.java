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

	public ActivitiyAdmin(IDAOActivity DAOCinnost, IDAOState DAOStav, PersonAdmin spravceOsob)
	{
		this.DAOCinnost = DAOCinnost;
		this.DAOStav = DAOStav;
		this.spravceOsob = spravceOsob;
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
	 * @param user logged-in user
	 * 
	 * @param cinnost
	 */
	public void addCinnost(Activity cinnost, Person user){
		if (cinnost.getVlastnik().equals(user)) {
			DAOCinnost.create(cinnost);
		} else {
			throw new SecurityException("Activity owned by '" 
				+ cinnost.getVlastnik().getLogin() + "' can't be added by '" 
				+ user.getLogin() + "'");
		}
	}

	/**
	 * Označí činnost jako "archivovanou". Toto může udělat pouze vlastník činnosti.
	 * @param user logged-in user
	 * 
	 * @param cinnost
	 */
	public void archiveCinnost(Activity cinnost, Person user){
		if (cinnost.getVlastnik().equals(user)) {
			ActivityState archivovana = DAOStav.getCinnostArchivovana();
			cinnost.setStav(archivovana);
			DAOCinnost.update(cinnost);
		} else {
			throw new SecurityException("Activity owned by '" 
				+ cinnost.getVlastnik().getLogin() + "' can't be archived by '" 
				+ user.getLogin() + "'");
		}
	}

	/**
	 * Smaže činnost. Toto může udělat pouze vlastník činnosti.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param cinnost
	 */
	public void deleteCinnost(Activity cinnost, Person user){
		if (cinnost.getVlastnik().equals(user)) DAOCinnost.delete(cinnost);
		else throw new SecurityException("Activity owned by '" 
				+ cinnost.getVlastnik().getLogin() + "' can't be deleted by '" 
				+ user.getLogin() + "'");
	}

	/**
	 * Vrátí cinnost na základě jejího ID.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param id
	 */
	public Activity getCinnost(int id, Person user){
		Activity cinnost = DAOCinnost.get(id);
		if (cinnost != null) {
			if (!cinnost.getVlastnik().equals(user)) {
				throw new SecurityException("Activity owned by '" 
				+ cinnost.getVlastnik().getLogin() + "' can't be read by '" 
				+ user.getLogin() + "'");
			}
		}
		return cinnost;
	}

	/**
	 * Vrátí všechny cinnosti osoby.
	 * @param user logged-in user
	 * @return
	 * 
	 */
	public List<Activity> getCinnostiOsoby(Person user){
		return DAOCinnost.getCinnostiOsoby(user);
	}

	/**
	 * Označí činnost jako "odloženou". Toto může udělat pouze vlastník činnosti.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param cinnost
	 */
	public void postponeCinnost(Activity cinnost, Person user){
		if (cinnost.getVlastnik().equals(user)) {
			ActivityState odlozena = DAOStav.getCinnostOdlozena();
			cinnost.setStav(odlozena);
			DAOCinnost.update(cinnost);
		} else {
			throw new SecurityException("Activity owned by '" 
				+ cinnost.getVlastnik().getLogin() + "' can't be postponed by '" 
				+ user.getLogin() + "'");
		}
	}

	/**
	 * Označí činnost jako "zpracovanou". Toto může udělat pouze vlastník činnosti.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param cinnost
	 */
	public void processCinnost(Activity cinnost, Person user){
		if (cinnost.getVlastnik().equals(user)) {
			ActivityState zpracovana = DAOStav.getCinnostZpracovana();
			cinnost.setStav(zpracovana);
			DAOCinnost.update(cinnost);
		} else {
			throw new SecurityException("Activity owned by '" 
				+ cinnost.getVlastnik().getLogin() + "' can't be processed by '" 
				+ user.getLogin() + "'");
		}
	}

	/**
	 * Uloží změněnou činnost. Toto může udělat pouze vlastník činnosti.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param cinnost
	 */
	public void updateCinnost(Activity cinnost, Person user){ // TODO steklsim updateCinnnost() je asi k nicemu
		if (cinnost.getVlastnik().equals(user)) {
			DAOCinnost.update(cinnost);
		} else {
			throw new SecurityException("Activity owned by '" 
				+ cinnost.getVlastnik().getLogin() + "' can't be updated by '" 
				+ user.getLogin() + "'");
		}
	}

}