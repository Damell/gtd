package GTD.BL.BLAktivity;

import GTD.BL.BLInterfaces.*;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLEntity.*;
import GTD.DL.DLInterfaces.IDAOState;
import java.util.List;

/**
 * Třída implementuje interface IProjectController.
 * @author GTD team
 * @version 1.0
 */
public class ProjectController implements IProjectController {

	private ProjectAdmin projectAdmin;
	private IDAOState daoState;

	/**
	 *
	 */
	public ProjectController(){
		projectAdmin = new ProjectAdmin();
		daoState = new DAOState();
	}

	/**
	 * Přidá nový projekt zadaných vlastností.
	 * 
	 * @param nazev
	 * @param popis
	 * @param vlastnik
	 * @param rodicID
	 * @param skupina
	 * @param cinnost    Činnost, ze které projekt vznikl (volitelné).
		 */
	@Override
	public boolean addProject(String nazev, String popis, int vlastnik, int rodicID, List<Person> skupina, Activity cinnost) {
		Project rodic = null;
		if (rodicID != -1) {
			rodic = projectAdmin.getProject(rodicID);
		}
		Project newProjekt = new Project(nazev, popis, daoState.getProjectActiveID(), vlastnik, skupina, rodic);
		return projectAdmin.addProject(newProjekt, cinnost);
	}

	/**
	 * Smaže projekt (resp. označí jako smazaný).
	 * 
	 * @param projekt
	 */
	@Override
	public boolean deleteProject(Project projekt){
		return projectAdmin.deleteProject(projekt);
	}

	/**
	 * Změní název a/nebo popis projektu.
	 * 
	 * @param projekt
	 */
	@Override
	public boolean updateProject(Project projekt){
		return false;
	}

	/**
	 * Vrátí konkrétní projekt (GUI toto používá pro nastavení aktuálně zobrazeného
	 * projektu).
	 * 
	 * @param id
	 */
	@Override
	public Project getProject(int id){
		return projectAdmin.getProject(id);
	}

	/**
	 * Změní vlastníka projektu. Změnit vlastníka projektu může jen jeho vlastník nebo
	 * vlastník prvního nadřazeného projektu.
	 * 
	 * @param projekt
	 * @param novyVlastnik
	 */
	@Override
	public boolean changeOwner(Project projekt, Person novyVlastnik){
		projekt.setVlastnikID(novyVlastnik.getId());
		return projectAdmin.updateProject(projekt);
	}

	/**
	 * Označí projekt jako "dokončený".
	 * 
	 * @param projekt
	 */
	@Override
	public boolean finishProject(Project projekt){
		projekt.setStav(daoState.getProjectFinishedID());
		return projectAdmin.finishProject(projekt);
	}

	/**
	 * Vrátí všechny projekty patřící dané osobě.
	 * 
	 * @param osoba
	 */
	@Override
	public List getProjectsOfPerson(Person osoba){
		return projectAdmin.getProjectsOfPerson(osoba);
	}

	/**
	 * Vrátí všechny projekty
	 * 
	 */
	@Override
	public List getAllProjects(){
		return projectAdmin.getAllProjects();
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

}