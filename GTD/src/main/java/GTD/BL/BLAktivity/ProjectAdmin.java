package GTD.BL.BLAktivity;

import GTD.DL.DLInterfaces.IDAOProject;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Trída pro manipulaci s projekty.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
public class ProjectAdmin {

	private IDAOProject DAOProjekt;
	/**
	 * Odkaz na ActivitiyAdmin - při přidání projektu vzniklého z činnosti je třeba
	 * tuto činnost označit jako "zpracovanou" - to zařídí právě ActivitiyAdmin.
	 */
	private ActivitiyAdmin spravceCinnosti;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private PersonAdmin spravceOsob;



	public void finalize() throws Throwable {

	}

	public ProjectAdmin(){

	}

	/**
	 * Vytvorí nový projekt zadaných vlastností a uloží ho do databáze. Podprojekt v
	 * projektu může vytvořit pouze vlastník tohoto nadřazeného projektu.
	 * @return
	 * 
	 * @param projekt
	 * @param cinnost    Činnost, ze které projekt vznikl (pokud existuje) - používá
	 * se pro označení činnosti jako "zpracované".
	 */
	public boolean addProjekt(Project projekt, Activity cinnost){
		return false;
	}

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
	 * a podprojekty. Project může mazat jeho vlastník nebo vlastník nadřazeného
	 * projektu (v 1.úrovni).
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean deleteProjekt(Project projekt){
		return false;
	}

	/**
	 * Označí projekt jako "dokončený". Dokončit projekt může jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean finishProjekt(Project projekt){
		return false;
	}

	/**
	 * Vrátí všechny projekty
	 * @return
	 */
	public List getAllProjekty(){
		return null;
	}

	/**
	 * Vrátí projekt podle jeho ID.
	 * @return
	 * 
	 * @param id
	 */
	public Project getProjekt(int id){
		return null;
	}

	/**
	 * Vrátí všechny projekty patrící zadané osobe.
	 * @return
	 * 
	 * @param osoba
	 */
	public List getProjektyOsoby(Person osoba){
		return null;
	}

	/**
	 * Uloží změněný projekt (změna názvu/popisu). Měnit projekt může  jeho vlastník
	 * nebo vlastník nadřazeného projektu (v 1.úrovni).
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean updateProjekt(Project projekt){
		return false;
	}

}