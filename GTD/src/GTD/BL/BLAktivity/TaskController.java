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
 * @version 1.0
 */
public class TaskController implements ITaskController {

	private TaskAdmin spravceUkolu;
	private IDAOState DAOStav;
	private IGTDGUI GUI;

	/**
	 *
	 */
	public TaskController(){
		spravceUkolu = new TaskAdmin();
		DAOStav = new DAOState();
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
	public boolean addUkol(String nazev, String popis, int vlastnikId, int projektId, Activity cinnost){

		if (vlastnikId == -1) vlastnikId = GTDGUI.getMyself().getId();
		Task ukol = new Task(nazev, popis, DAOStav.getUkolVytvorenyID(), GTDGUI.getMyself().getId(), vlastnikId, projektId);

		return spravceUkolu.addUkol(ukol, cinnost);
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
	public boolean addTwoMinutesUkol(String nazev, String popis, int projektId, Activity cinnost){
		Task ukol = new Task(nazev, popis, DAOStav.getUkolHotovyID(), GTDGUI.getMyself().getId(), GTDGUI.getMyself().getId(), projektId);
		return spravceUkolu.addUkol(ukol, cinnost);
	}

	/**
	 * Smaže úkol (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 * @return 
	 */
	@Override
	public boolean deleteUkol(Task ukol){
		return spravceUkolu.deleteUkol(ukol);
	}

	/**
	 * Změní název a/nebo popis úkolu.
	 * 
	 * @param ukol
	 * @return 
	 */
	@Override
	public boolean updateUkol(Task ukol){
		return false;
	}

	@Override
	public boolean activateUkol(Task ukol) {
		ukol.setStav(DAOStav.getUkolAktivniID());
		return spravceUkolu.updateUkol(ukol);
	}

	/**
	 * Přidá úkol do kalendáře.
	 * 
	 * @param ukol
	 * @param interval
	 * @return 
	 */
	@Override
	public boolean scheduleUkol(Task ukol, Interval interval){
		ukol.setInterval(interval.getFrom(), interval.getTo());
		ukol.setStav(DAOStav.getUkolVKalendariID());
		return spravceUkolu.updateUkol(ukol);
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
		return spravceUkolu.updateUkol(ukol);
	}

	/**
	 * Označí úkol jako "dokončený".
	 * 
	 * @param ukol
	 * @return 
	 */
	@Override
	public boolean finishUkol(Task ukol){
		ukol.setStav(DAOStav.getUkolHotovyID());
		return spravceUkolu.updateUkol(ukol);
	}

	/**
	 * Nastaví kontext úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 * @return 
	 */
	@Override
	public boolean setKontext(Task ukol, Context kontext){
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
		return spravceUkolu.getUkolyOsoby(osoba);
	}

	/**
	 * Vrátí všechny úkoly
	 * @return 
	 */
	@Override
	public List getAllUkoly() {
		return spravceUkolu.getAllUkoly();
	}


}