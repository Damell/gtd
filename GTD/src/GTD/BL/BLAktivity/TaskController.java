package GTD.BL.BLAktivity;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.BL.BLInterfaces.ITaskController;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLInterfaces.IDAOState;
import GTD.PL.PLView.GTDGUI;
import java.util.List;

/**
 * Třída implementuje interface ITaskController.
 * @author GTD team
 * @version 1.0
 */
public class TaskController implements ITaskController {

	private TaskAdmin spravceUkolu;
	private IDAOState daoStav;

	/**
	 *
	 */
	public TaskController(){
		spravceUkolu = new TaskAdmin();
		daoStav = new DAOState();
	}

	/**
	 * Přidá nový úkol zadaných vlastností.
	 * 
	 * @param nazev
	 * @param popis
	 * @param vlastnikId
	 * @param projektId
	 * @param cinnost    Činnost, ze které úkol vznikl (volitelné).
	 * @return 
	 */
	@Override
	public boolean addTask(String nazev, String popis, int vlastnikId, int projektId, Activity cinnost){

		if (vlastnikId == -1) vlastnikId = GTDGUI.getMyself().getId();
		Task ukol = new Task(nazev, popis, daoStav.getTaskCreatedID(), GTDGUI.getMyself().getId(), vlastnikId, projektId);

		return spravceUkolu.addTask(ukol, cinnost);
	}

	/**
	 * Vytvoří úkol a hned ho označí jako "hotový" (používá se při zpracování činnosti,
	 * pokud ji mohu a chci dokončit do 2 minut).
	 * 
	 * @param nazev
	 * @param popis
	 * @param projektId
	 * @param cinnost    Činnost, ze které úkol vznikl (volitelné).
	 * @return 
	 */
	@Override
	public boolean addTwoMinutesTask(String nazev, String popis, int projektId, Activity cinnost){
		Task ukol = new Task(nazev, popis, daoStav.getTaskFinishedID(), GTDGUI.getMyself().getId(), GTDGUI.getMyself().getId(), projektId);
		return spravceUkolu.addTask(ukol, cinnost);
	}

	/**
	 * Smaže úkol (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 * @return 
	 */
	@Override
	public boolean deleteTask(Task ukol){
		return spravceUkolu.deleteTask(ukol);
	}

	/**
	 * Změní název a/nebo popis úkolu.
	 * 
	 * @param ukol
	 * @return 
	 */
	@Override
	public boolean updateTask(Task ukol){
		return false;
	}

	@Override
	public boolean activateTask(Task ukol) {
		ukol.setStav(daoStav.getTaskActiveID());
		return spravceUkolu.updateTask(ukol);
	}

	/**
	 * Přidá úkol do kalendáře.
	 * 
	 * @param ukol
	 * @param interval
	 * @return 
	 */
	@Override
	public boolean scheduleTask(Task ukol, Interval interval){
		ukol.setInterval(interval.getFrom(), interval.getTo());
		ukol.setStav(daoStav.getTaskPlannedID());
		return spravceUkolu.updateTask(ukol);
	}

	/**
	 * Změní vlastníka úkolu.
	 * 
	 * @param ukol
	 * @param novyVlastnik
	 * @return 
	 */
	@Override
	public boolean changeOwner(Task ukol, Person novyVlastnik){
		ukol.setVlastnikID(novyVlastnik.getId());
		return spravceUkolu.updateTask(ukol);
	}

	/**
	 * Označí úkol jako "dokončený".
	 * 
	 * @param ukol
	 * @return 
	 */
	@Override
	public boolean finishTask(Task ukol){
		ukol.setStav(daoStav.getTaskFinishedID());
		return spravceUkolu.updateTask(ukol);
	}

	/**
	 * Nastaví kontext úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 * @return 
	 */
	@Override
	public boolean setContext(Task ukol, Context kontext){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * 
	 * @param osoba
	 * @return 
	 */
	@Override
	public List getUkolyOsoby(Person osoba) {
		return spravceUkolu.getTasksOfPerson(osoba);
	}

	/**
	 * Vrátí všechny úkoly
	 * @return 
	 */
	@Override
	public List getAllUkoly() {
		return spravceUkolu.getAllTasks();
	}


}