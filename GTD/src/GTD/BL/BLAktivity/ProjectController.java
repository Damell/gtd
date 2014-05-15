package GTD.BL.BLAktivity;

import GTD.BL.BLInterfaces.*;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLEntity.*;
import GTD.DL.DLInterfaces.IDAOState;
import java.util.List;

/**
 * Třída implementuje interface IProjectController.
 * @version 1.0
 */
public class ProjectController implements IProjectController {

	private ProjectAdmin spravceProjektu;
	private IDAOState DAOStav;

	/**
	 *
	 */
	public ProjectController(){
		spravceProjektu = new ProjectAdmin();
		DAOStav = new DAOState();
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
	 * @return 
		 */
	@Override
	public boolean addProject(String nazev, String popis, int vlastnik, int rodicID, List<Person> skupina, Activity cinnost) {
		Project rodic = null;
		if (rodicID != -1) {
			rodic = spravceProjektu.getProjekt(rodicID);
		}
		Project newProjekt = new Project(nazev, popis, DAOStav.getProjektAktivniID(), vlastnik, skupina, rodic);
		return spravceProjektu.addProjekt(newProjekt, cinnost);
	}

	/**
	 * Smaže projekt (resp. označí jako smazaný).
	 * 
	 * @param projekt
	 * @return 
	 */
	@Override
	public boolean deleteProject(Project projekt){
		return spravceProjektu.deleteProjekt(projekt);
	}

	/**
	 * Změní název a/nebo popis projektu.
	 * 
	 * @param projekt
	 * @return 
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
	 * @return 
	 */
	@Override
	public Project getProject(int id){
		return spravceProjektu.getProjekt(id);
	}

	/**
	 * Změní vlastníka projektu. Změnit vlastníka projektu může jen jeho vlastník nebo
	 * vlastník prvního nadřazeného projektu.
	 * 
	 * @param projekt
	 * @param novyVlastnik
	 * @return 
	 */
	@Override
	public boolean changeOwner(Project projekt, Person novyVlastnik){
		projekt.setVlastnikID(novyVlastnik.getId());
		return spravceProjektu.updateProjekt(projekt);
	}

	/**
	 * Označí projekt jako "dokončený".
	 * 
	 * @param projekt
	 * @return 
	 */
	@Override
	public boolean finishProject(Project projekt){
		projekt.setStav(DAOStav.getProjektDokoncenyID());
		return spravceProjektu.finishProjekt(projekt);
	}

	/**
	 * Vrátí všechny projekty patřící dané osobě.
	 * 
	 * @param osoba
	 * @return 
	 */
	@Override
	public List getProjectsOfPerson(Person osoba){
		return spravceProjektu.getProjektyOsoby(osoba);
	}

	/**
	 * Vrátí všechny projekty
	 * 
	 * @return 
	 */
	@Override
	public List getAllProjects(){
		return spravceProjektu.getAllProjekty();
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

}