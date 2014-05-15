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
 * @version 1.0
 */
public class ProjectAdmin {

	private IDAOProject DAOProjekt;
	/**
	 * Odkaz na ActivitiyAdmin - při přidání projektu vzniklého z činnosti je třeba
 tuto činnost označit jako "zpracovanou" - to zařídí právě ActivitiyAdmin.
	 */
	private ActivitiyAdmin spravceCinnosti;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private PersonAdmin spravceOsob;

	/**
	 *
	 */
	public ProjectAdmin(){
		DAOProjekt = new DAOProject();
		spravceCinnosti = new ActivitiyAdmin();
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
	public boolean addProjekt(Project projekt, Activity cinnost){
		if (cinnost != null) {
			spravceCinnosti.deleteCinnost(cinnost);
		}
		return DAOProjekt.createProjekt(projekt);
	}

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
 a podprojekty. Project může mazat jeho vlastník nebo vlastník nadřazeného
 projektu (v 1.úrovni).
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean deleteProjekt(Project projekt){
		return DAOProjekt.deleteProjekt(projekt);
	}

	/**
	 * Vrátí projekt podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Project getProjekt(int id){
		return DAOProjekt.getProjekt(id);
	}

	/**
	 * Uloží změněný projekt (změna názvu/popisu). Měnit projekt může  jeho vlastník
	 * nebo vlastník nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean updateProjekt(Project projekt){
		return DAOProjekt.updateProjekt(projekt);
	}

	/**
	 * Vrátí všechny projekty patrící zadané osobe.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getProjektyOsoby(Person osoba){
		return DAOProjekt.getProjektyOsoby(osoba);
	}

	/**
	 * Vrátí všechny projekty
	 * 
	 * @return 
	 */
	public List getAllProjekty(){
		return DAOProjekt.getAllProjekty();
	}

	/**
	 * Označí projekt jako "dokončený". Dokončit projekt může jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean finishProjekt(Project projekt){
		return DAOProjekt.updateProjekt(projekt);
	}

}