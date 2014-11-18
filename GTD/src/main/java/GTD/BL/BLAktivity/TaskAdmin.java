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
	 * @return
	 * 
	 * @param ukol
	 * @param cinnost    Činnost, ze které úkol vznikl (pokud existuje) - používá se
	 * pro označení činnosti jako "zpracované".
	 */
	public void addUkol(Task ukol, Activity cinnost){
		DAOUkol.create(ukol);
		spravceCinnosti.processCinnost(cinnost); // TODO steklsim pokud processCinnost() hodi vyjimku, nemel by se zrusit ukol?
	}

	/**
	 * Smaže úkol (resp. označí jako smazaný). Mazat úkol může pouze jeho vlastník a
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * @return
	 * 
	 * @param ukol
	 */
	public void deleteUkol(Task ukol){
		DAOUkol.delete(ukol);
	}

	/**
	 * Vrátí všechny úkoly
	 * @return
	 */
	public List getAllUkoly(){
		return DAOUkol.getAll();
	}

	/**
	 * Vrátí úkol.
	 * @return
	 * 
	 * @param id
	 */
	public Task getUkol(int id){
		return DAOUkol.get(id);
	}

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * @return
	 * 
	 * @param kontext
	 */
	public List getUkolyKontextu(Context kontext){
		return DAOUkol.getUkolyKontextu(kontext);
	}

	/**
	 * @return
	 * 
	 * @param osoba
	 */
	public List getUkolyOsoby(Person osoba){
		return DAOUkol.getUkolyOsoby(osoba);
	}

	/**
	 * Nastaví kontext úkolu. Toto může udělat pouze vlastník úkolu.
	 * @return
	 * 
	 * @param ukol
	 * @param kontext
	 */
	public void setKontext(Task ukol, Context kontext){
		if (!ukol.getVlastnik().equals(kontext.getVlastnik())) {
			throw new RuntimeException("Zmenit kontext ukolu muze pouze jeho vlastnik"); 
		// TODO steklsim tady hodit nejakou Spring exception? (az bude Spring)
		}
		ukol.setKontext(kontext);
		DAOUkol.update(ukol);
	}

	/**
	 * Uloží změněný úkol (změna názvu/popisu). Změnit úkol může  jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * @return
	 * 
	 * @param ukol
	 */
	public void updateUkol(Task ukol){ // TODO steklsim metoda zatim na nic
		DAOUkol.update(ukol);
	}

}