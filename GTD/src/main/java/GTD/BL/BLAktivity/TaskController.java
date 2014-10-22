package GTD.BL.BLAktivity;

import GTD.DL.DLInterfaces.IDAOState;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Interval;
import GTD.BL.BLInterfaces.ITaskController;
import java.util.List;

/**
 * Třída implementuje interface ITaskController.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
public class TaskController implements ITaskController {

	private IDAOState DAOStav;
	private IGTDGUI GUI;
	private TaskAdmin spravceUkolu;



	public void finalize() throws Throwable {

	}

	public TaskController(){

	}

	/**
	 * 
	 * @param ukol
	 */
	@Override
	public boolean activateTask(Task ukol){
		return false;
	}

	/**
	 * Přidá nový úkol zadaných vlastností.
	 * @return
	 * 
	 * @param nazev
	 * @param popis
	 * @param vlastnikId
	 * @param projektId
	 * @param cinnost    Činnost, ze které úkol vznikl (volitelné).
	 */
	@Override
	public boolean addTask(String nazev, String popis, int vlastnikId, int projektId, Activity cinnost){
		return false;
	}

	/**
	 * Vytvoří úkol a hned ho označí jako "hotový" (používá se při zpracování činnosti,
	 * pokud ji mohu a chci dokončit do 2 minut).
	 * @return
	 * 
	 * @param nazev
	 * @param popis
	 * @param projektId
	 * @param cinnost    Činnost, ze které úkol vznikl (volitelné).
	 */
	@Override
	public boolean addTwoMinutesTask(String nazev, String popis, int projektId, Activity cinnost){
		return false;
	}

	/**
	 * Smaže úkol (resp. označí jako smazaný).
	 * @return
	 * 
	 * @param ukol
	 */
	@Override
	public boolean deleteTask(Task ukol){
		return false;
	}

	/**
	 * Označí úkol jako "dokončený".
	 * @return
	 * 
	 * @param ukol
	 */
	@Override
	public boolean finishTask(Task ukol){
		return false;
	}

	/**
	 * Vrátí všechny úkoly
	 * @return
	 */
	@Override
	public List getAllUkoly(){
		return null;
	}

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * @return
	 * 
	 * @param osoba
	 */
	@Override
	public List getUkolyOsoby(Person osoba){
		return null;
	}

	/**
	 * Změní vlastníka úkolu.
	 * @return
	 * 
	 * @param ukol
	 * @param novyVlastnik
	 */
	@Override
	public boolean changeOwner(Task ukol, Person novyVlastnik){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

	/**
	 * Nastaví kontext úkolu.
	 * @return
	 * 
	 * @param ukol
	 * @param kontext
	 */
	@Override
	public boolean setContext(Task ukol, Context kontext){
		return false;
	}

	/**
	 * Přidá úkol do kalendáře.
	 * @return
	 * 
	 * @param ukol
	 * @param interval
	 */
	@Override
	public boolean scheduleTask(Task ukol, Interval interval){
		return false;
	}

	/**
	 * Změní název a/nebo popis úkolu.
	 * @return
	 * 
	 * @param ukol
	 */
	@Override
	public boolean updateTask(Task ukol){
		return false;
	}

}