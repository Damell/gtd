package GTD.BL.BLAktivity;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLDAO.DAOProject;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLInterfaces.IDAOProject;
import java.util.List;

/**
 * Trída pro manipulaci s projekty.
 * @author GTD team
 * @version 1.0
 */
public class ProjectAdmin {

	private IDAOProject daoProject;
	/**
	 * Odkaz na ActivitiyAdmin - při přidání projektu vzniklého z činnosti je třeba
 tuto činnost označit jako "zpracovanou" - to zařídí právě ActivitiyAdmin.
	 */
	private ActivitiyAdmin activityAdmin;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private PersonAdmin personAdmin;

	/**
	 *
	 */
	public ProjectAdmin(){
		daoProject = new DAOProject();
		activityAdmin = new ActivitiyAdmin();
	}

	/**
	 * Vytvorí nový projekt zadaných vlastností a uloží ho do databáze. Podprojekt v
	 * projektu může vytvořit pouze vlastník tohoto nadřazeného projektu.
	 * 
	 * @param projekt
	 * @param cinnost    Činnost, ze které projekt vznikl (pokud existuje) - používá
	 * se pro označení činnosti jako "zpracované".
	 * @return 
	 */
	public boolean addProject(Project projekt, Activity cinnost){
		if (cinnost != null) {
			activityAdmin.deleteActivity(cinnost);
		}
		return daoProject.createProject(projekt);
	}

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
 a podprojekty. Project může mazat jeho vlastník nebo vlastník nadřazeného
 projektu (v 1.úrovni).
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean deleteProject(Project projekt){
		return daoProject.deleteProject(projekt);
	}

	/**
	 * Vrátí projekt podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Project getProject(int id){
		return daoProject.getProject(id);
	}

	/**
	 * Uloží změněný projekt (změna názvu/popisu). Měnit projekt může  jeho vlastník
	 * nebo vlastník nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean updateProject(Project projekt){
		return daoProject.updateProject(projekt);
	}

	/**
	 * Vrátí všechny projekty patrící zadané osobe.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getProjectsOfPerson(Person osoba){
		return daoProject.getProjectsOfPerson(osoba);
	}

	/**
	 * Vrátí všechny projekty
	 * 
	 * @return 
	 */
	public List getAllProjects(){
		return daoProject.getAllProjects();
	}

	/**
	 * Označí projekt jako "dokončený". Dokončit projekt může jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean finishProject(Project projekt){
		return daoProject.updateProject(projekt);
	}

}