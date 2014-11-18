package GTD.BL.BLAktivity;

import GTD.DL.DLInterfaces.IDAOProject;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.ProjectState;
import GTD.DL.DLInterfaces.IDAOState;
import java.util.List;

/**
 * Trída pro manipulaci s projekty.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
public class ProjectAdmin {

	private IDAOProject DAOProjekt;
	private IDAOState DAOStav;
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

	public void setDAOProjekt(IDAOProject DAOProjekt)
	{
		this.DAOProjekt = DAOProjekt;
	}

	public void setSpravceCinnosti(ActivitiyAdmin spravceCinnosti)
	{
		this.spravceCinnosti = spravceCinnosti;
	}

	public void setDAOStav(IDAOState DAOStav)
	{
		this.DAOStav = DAOStav;
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
	public void addProjekt(Project projekt, Activity cinnost){
		if (projekt.getVlastnik() != null && !projekt.getVlastnik().equals(cinnost.getVlastnik())) {
			throw new RuntimeException("Vytvorit projekt z cinnosti muze pouze jeji vlastnik"); 
		// TODO steklsim tady hodit nejakou Spring exception? (az bude Spring)
		}
		DAOProjekt.create(projekt);
		spravceCinnosti.processCinnost(cinnost); // TODO steklsim pokud processCinnost() hodi vyjimku, nemel by se zrusit projekt?
	}

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
	 * a podprojekty. Project může mazat jeho vlastník nebo vlastník nadřazeného
	 * projektu (v 1.úrovni).
	 * @return
	 * 
	 * @param projekt
	 */
	public void deleteProjekt(Project projekt){ // TODO steklsim v docBlocku je projekt "oznacen jako smazany" - nemame na to stav
		DAOProjekt.delete(projekt);
	}

	/**
	 * Označí projekt jako "dokončený". Dokončit projekt může jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * @return
	 * 
	 * @param projekt
	 */
	public void finishProjekt(Project projekt){
		ProjectState dokonceny = DAOStav.getProjektDokonceny();
		projekt.setStav(dokonceny);
		DAOProjekt.update(projekt);
	}

	/**
	 * Vrátí všechny projekty
	 * @return
	 */
	public List<Project> getAllProjekty(){
		return DAOProjekt.getAll();
	}

	/**
	 * Vrátí projekt podle jeho ID.
	 * @return
	 * 
	 * @param id
	 */
	public Project getProjekt(int id){
		return DAOProjekt.get(id);
	}

	/**
	 * Vrátí všechny projekty patrící zadané osobe.
	 * @return
	 * 
	 * @param osoba
	 */
	public List<Project> getProjektyOsoby(Person osoba){
		return DAOProjekt.getProjektyOsoby(osoba);
	}

	/**
	 * Uloží změněný projekt (změna názvu/popisu). Měnit projekt může  jeho vlastník
	 * nebo vlastník nadřazeného projektu (v 1.úrovni).
	 * @return
	 * 
	 * @param projekt
	 */
	public void updateProjekt(Project projekt){ // TODO steklsim metoda zatim na nic
		DAOProjekt.update(projekt);
	}
	
}