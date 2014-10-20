package GTD.BL.BLAktivity;

import GTD.DL.DLInterfaces.IDAOState;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Person;
import GTD.BL.BLInterfaces.IProjectController;

/**
 * Třída implementuje interface IProjectController.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
public class ProjectController implements IProjectController {

	private IDAOState DAOStav;
	private ProjectAdmin spravceProjektu;



	public void finalize() throws Throwable {

	}

	public ProjectController(){

	}

	/**
	 * Přidá nový projekt zadaných vlastností.
	 * @return
	 * 
	 * @param nazev
	 * @param popis
	 * @param vlastnik
	 * @param rodicID
	 * @param skupina
	 * @param cinnost    Činnost, ze které projekt vznikl (volitelné).
	 */
	@Override
	public boolean addProject(String nazev, String popis, int vlastnik, int rodicID, List<Person> skupina, Activity cinnost){
		return false;
	}

	/**
	 * Smaže projekt (resp. označí jako smazaný).
	 * @return
	 * 
	 * @param projekt
	 */
	@Override
	public boolean deleteProject(Project projekt){
		return false;
	}

	/**
	 * Označí projekt jako "dokončený".
	 * @return
	 * 
	 * @param projekt
	 */
	@Override
	public boolean finishProject(Project projekt){
		return false;
	}

	/**
	 * Vrátí všechny projekty
	 * @return
	 */
	@Override
	public List getAllProjects(){
		return null;
	}

	/**
	 * Vrátí konkrétní projekt (GUI toto používá pro nastavení aktuálně zobrazeného
	 * projektu).
	 * @return
	 * 
	 * @param id
	 */
	@Override
	public Project getProject(int id){
		return null;
	}

	/**
	 * Vrátí všechny projekty patřící dané osobě.
	 * @return
	 * 
	 * @param osoba
	 */
	@Override
	public List getProjectsOfPerson(Person osoba){
		return null;
	}

	/**
	 * Změní vlastníka projektu. Změnit vlastníka projektu může jen jeho vlastník nebo
	 * vlastník prvního nadřazeného projektu.
	 * @return
	 * 
	 * @param projekt
	 * @param novyVlastnik
	 */
	@Override
	public boolean changeOwner(Project projekt, Person novyVlastnik){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

	/**
	 * Změní název a/nebo popis projektu.
	 * @return
	 * 
	 * @param projekt
	 */
	@Override
	public boolean updateProject(Project projekt){
		return false;
	}

}