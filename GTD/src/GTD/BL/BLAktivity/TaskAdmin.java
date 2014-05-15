package GTD.BL.BLAktivity;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLInterfaces.IDAOTask;
import GTD.DL.DLEntity.*;
import java.util.List;

/**
 * Trída pro manipulaci s úkoly.
 * @author GTD team
 * @version 1.0
 */
public class TaskAdmin {

	private IDAOTask DAOUkol;
	/**
	 * Odkaz na ActivitiyAdmin - při přidání úkolu vzniklého z činnosti je třeba tuto
 činnost označit jako "zpracovanou" - to zařídí právě ActivitiyAdmin.
	 */
	private ActivitiyAdmin activityAdmin;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private PersonAdmin spravceOsob;

	/**
	 *
	 */
	public TaskAdmin(){
		activityAdmin = new ActivitiyAdmin();
		DAOUkol = new DAOTask();
	}

	/**
	 * Vytvoří nový úkol. Úkol v projektu může vytvořit pouze vlastník tohoto projektu.
	 * 
	 * 
	 * @param ukol
	 * @param cinnost    Činnost, ze které úkol vznikl (pokud existuje) - používá se
	 * pro označení činnosti jako "zpracované".
	 * @return 
	 */
	public boolean addTask(Task ukol, Activity cinnost){
		if (DAOUkol.createTask(ukol)) {
			activityAdmin.deleteActivity(cinnost);
			return true;
		}
		return false;
	}

	/**
	 * Smaže úkol (resp. označí jako smazaný). Mazat úkol může pouze jeho vlastník a
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean deleteTask(Task ukol){
		return DAOUkol.deleteTask(ukol);
	}

	/**
	 * Vrátí úkol.
	 * 
	 * @param id
	 * @return 
	 */
	public Task getTask(int id){
		return DAOUkol.getTask(id);
	}

	/**
	 * Uloží změněný úkol (změna názvu/popisu). Změnit úkol může  jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean updateTask(Task ukol){
		return DAOUkol.updateTask(ukol);
	}

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	public List getTasksWithContext(Context kontext){
		return DAOUkol.getTasksWithContext(kontext);
	}

	/**
	 * Vrátí všechny úkoly
	 * @return 
	 */
	public List getAllTasks(){
		return DAOUkol.getAllTasks();
	}

	/**
	 * Nastaví kontext úkolu. Toto může udělat pouze vlastník úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 * @return 
	 */
	public boolean setContext(Task ukol, Context kontext){
		return false;
	}

	/**
	 *
	 * @param osoba
	 * @return
	 */
	public List getTasksOfPerson(Person osoba) {
		return DAOUkol.getTasksOfPerson(osoba);
	}

}