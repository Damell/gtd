package GTD.BL.BLAktivity;

import GTD.DL.DLInterfaces.IDAOTask;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;

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

	/**
	 * Vytvoří nový úkol. Úkol v projektu může vytvořit pouze vlastník tohoto projektu.
	 * 
	 * @return
	 * 
	 * @param ukol
	 * @param cinnost    Činnost, ze které úkol vznikl (pokud existuje) - používá se
	 * pro označení činnosti jako "zpracované".
	 */
	public boolean addUkol(Task ukol, Activity cinnost){
		return false;
	}

	/**
	 * Smaže úkol (resp. označí jako smazaný). Mazat úkol může pouze jeho vlastník a
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * @return
	 * 
	 * @param ukol
	 */
	public boolean deleteUkol(Task ukol){
		return false;
	}

	/**
	 * Vrátí všechny úkoly
	 * @return
	 */
	public List getAllUkoly(){
		return null;
	}

	/**
	 * Vrátí úkol.
	 * @return
	 * 
	 * @param id
	 */
	public Task getUkol(int id){
		return null;
	}

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * @return
	 * 
	 * @param kontext
	 */
	public List getUkolyKontextu(Context kontext){
		return null;
	}

	/**
	 * @return
	 * 
	 * @param osoba
	 */
	public List getUkolyOsoby(Person osoba){
		return null;
	}

	/**
	 * Nastaví kontext úkolu. Toto může udělat pouze vlastník úkolu.
	 * @return
	 * 
	 * @param ukol
	 * @param kontext
	 */
	public boolean setKontext(Task ukol, Context kontext){
		return false;
	}

	/**
	 * Uloží změněný úkol (změna názvu/popisu). Změnit úkol může  jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * @return
	 * 
	 * @param ukol
	 */
	public boolean updateUkol(Task ukol){
		return false;
	}

}