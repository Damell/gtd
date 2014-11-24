package GTD.BL.BLAktivity;

import GTD.DL.DLInterfaces.IDAOTask;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.ActivityState;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import java.util.List;
import javax.security.auth.login.LoginException;

/**
 * Trída pro manipulaci s úkoly.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
public class TaskAdmin {

	private IDAOTask DAOUkol;
	/**
	 * Odkaz na ActivitiyAdmin - při přidání úkolu vzniklého z činnosti je třeba tuto
	 * činnost označit jako "zpracovanou" - to zařídí právě ActivitiyAdmin.
	 */
	private ActivitiyAdmin spravceCinnosti;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private PersonAdmin spravceOsob;



	public void finalize() throws Throwable {

	}

	public TaskAdmin(){

	}

	public void setDAOUkol(IDAOTask DAOUkol)
	{
		this.DAOUkol = DAOUkol;
	}

	public void setSpravceCinnosti(ActivitiyAdmin spravceCinnosti)
	{
		this.spravceCinnosti = spravceCinnosti;
	}

	
	
	/**
	 * Vytvoří nový úkol. Úkol v projektu může vytvořit pouze vlastník tohoto projektu.
	 * 
	 * @param user logged-in user
	 * @return
	 * 
	 * @param ukol
	 * @param cinnost    Činnost, ze které úkol vznikl (pokud existuje) - používá se
	 * pro označení činnosti jako "zpracované".
	 */
	public void addUkol(Task ukol, Activity cinnost, Person user){
		if (ukol.getVlastnik().equals(cinnost.getVlastnik()) 
				&& ukol.getProjekt().getVlastnik().equals(user)) {
			DAOUkol.create(ukol);
			spravceCinnosti.processCinnost(cinnost, user); // TODO steklsim pokud processCinnost() hodi vyjimku, nemel by se zrusit ukol?
		// TODO steklsim tady hodit nejakou Spring exception? (az bude Spring)
		} else {
			throw new SecurityException("User '" + user.getLogin()
				+ "' can't add a task to a project owned by '" 
				+ ukol.getVlastnik().getLogin() + "'");
		}
	}

	/**
	 * Smaže úkol (resp. označí jako smazaný). Mazat úkol může pouze jeho vlastník a
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * @param user logged-in user
	 * @return
	 * 
	 * @param ukol
	 */
	public void deleteUkol(Task ukol, Person user){
		if (ukol.getVlastnik().equals(user) 
				|| ukol.getProjekt().getVlastnik().equals(user)) {
			DAOUkol.delete(ukol);
		} else {
			throw new SecurityException("Task owned by '" 
				+ ukol.getVlastnik().getLogin() + "' can't be deleted by '" 
				+ user.getLogin() + "'");
		}
		
	}

	/**
	 * Vrátí všechny úkoly
	 * @return
	 */
	public List<Task> getAllUkoly(){
		// if user has role ROLE_ADMIN
		return DAOUkol.getAll();
	}

	/**
	 * Vrátí úkol.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param id
	 */
	public Task getUkol(int id, Person user){
		Task ukol = DAOUkol.get(id);
		if (ukol != null) {
			if (!ukol.getVlastnik().equals(user) 
					&& !ukol.getProjekt().getVlastnik().equals(user)) { // TODO steklsim authorization not needed here maybe?
				throw new SecurityException("Task owned by '" 
						+ ukol.getVlastnik().getLogin() + "' can't be read by '" 
						+ user.getLogin() + "'");
			}
		}
		return ukol;
	}

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param kontext
	 */
	public List getUkolyKontextu(Context kontext, Person user){
		if (kontext.getVlastnik().equals(user)) {
			return DAOUkol.getUkolyKontextu(kontext);	
		} else {
			throw new SecurityException("User '" + user.getLogin() 
					+ "' can't access context owned by '"
					+ kontext.getVlastnik().getLogin() + "'");
		}
	}

	/**
	 * @return
	 * 
	 * @param user logged-in user
	 */
	public List getUkolyOsoby(Person user){
		return DAOUkol.getUkolyOsoby(user);
	}

	/**
	 * Nastaví kontext úkolu. Toto může udělat pouze vlastník úkolu.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param ukol
	 * @param kontext
	 */
	public void setKontext(Task ukol, Context kontext, Person user){
		if (ukol.getVlastnik().equals(kontext.getVlastnik())
				&& ukol.getVlastnik().equals(user)) {
			ukol.setKontext(kontext);
			DAOUkol.update(ukol);
		// TODO steklsim tady hodit nejakou Spring exception? (az bude Spring)
		} else {
			throw new SecurityException("Task owned by '" 
				+ ukol.getVlastnik().getLogin() + "' can't be altered by '" 
				+ user.getLogin() + "'");
		}
	}

	/**
	 * Uloží změněný úkol (změna názvu/popisu). Změnit úkol může  jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * @param user logged-in user
	 * @return
	 * 
	 * @param ukol
	 */
	public void updateUkol(Task ukol, Person user){ // TODO steklsim metoda zatim na nic
		if (ukol.getVlastnik().equals(user) 
				|| ukol.getProjekt().getVlastnik().equals(user)) {
			DAOUkol.update(ukol);
		} else {
			throw new SecurityException("Task owned by '" 
				+ ukol.getVlastnik().getLogin() + "' can't be updated by '" 
				+ user.getLogin() + "'");
		}
	}

}