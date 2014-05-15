package GTD.BL.BLAktivity;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLInterfaces.IDAOTask;
import GTD.DL.DLEntity.*;
import java.util.List;

/**
 * Trída pro manipulaci s úkoly.
 * @version 1.0
 */
public class TaskAdmin {

	private IDAOTask DAOUkol;
	/**
	 * Odkaz na ActivitiyAdmin - při přidání úkolu vzniklého z činnosti je třeba tuto
 činnost označit jako "zpracovanou" - to zařídí právě ActivitiyAdmin.
	 */
	private ActivitiyAdmin spravceCinnosti;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private PersonAdmin spravceOsob;

	/**
	 *
	 */
	public TaskAdmin(){
		spravceCinnosti = new ActivitiyAdmin();
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
	public boolean addUkol(Task ukol, Activity cinnost){
		if (DAOUkol.createUkol(ukol)) {
			spravceCinnosti.deleteCinnost(cinnost);
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
	public boolean deleteUkol(Task ukol){
		return DAOUkol.deleteUkol(ukol);
	}

	/**
	 * Vrátí úkol.
	 * 
	 * @param id
	 * @return 
	 */
	public Task getUkol(int id){
		return DAOUkol.getUkol(id);
	}

	/**
	 * Uloží změněný úkol (změna názvu/popisu). Změnit úkol může  jeho vlastník nebo
	 * vlastník nadřazeného projektu (v 1.úrovni).
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean updateUkol(Task ukol){
		return DAOUkol.updateUkol(ukol);
	}

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	public List getUkolyKontextu(Context kontext){
		return DAOUkol.getUkolyKontextu(kontext);
	}

	/**
	 * Vrátí všechny úkoly
	 * @return 
	 */
	public List getAllUkoly(){
		return DAOUkol.getAllUkoly();
	}

	/**
	 * Nastaví kontext úkolu. Toto může udělat pouze vlastník úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 * @return 
	 */
	public boolean setKontext(Task ukol, Context kontext){
		return false;
	}

	/**
	 *
	 * @param osoba
	 * @return
	 */
	public List getUkolyOsoby(Person osoba) {
		return DAOUkol.getUkolyOsoby(osoba);
	}

}