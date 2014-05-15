package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Task;
import java.util.List;

/**
 * Interface defines the way BL and PL communicates concerning tasks
 * @author GTD team
 * @version 1.0
 */
public interface ITaskController {

	/**
	 * Přidá nový úkol zadaných vlastností.
	 * 
	 * @param title
	 * @param desc
	 * @param activity    Činnost, ze které úkol vznikl (volitelné).
	 * @param projektId
	 * @param ownerId
	 * @return boolean
	 */
	public boolean addTask(String title, String desc, int ownerId, int projektId, Activity activity);

	/**
	 * Smaže úkol (resp. označí jako smazaný).
	 * 
	 * @param task
	 * @return boolean
	 */
	public boolean deleteTask(Task task);

	/**
	 * Změní název a/nebo popis úkolu.
	 * 
	 * @param task
	 * @return boolean
	 */
	public boolean updateTask(Task task);

	/**
	 * Označí úkol jako aktivní.
	 * 
	 * @param task
	 * @return boolean
	 */
	public boolean activateTask(Task task);

	/**
	 * Přidá úkol do kalendáře.
	 * 
	 * @param task
	 * @param interval
	 * @return boolean
	 */
	public boolean scheduleTask(Task task, Interval interval);

	/**
	 * Změní vlastníka úkolu.
	 * 
	 * @param task
	 * @param newOwner
	 * @return boolean
	 */
	public boolean changeOwner(Task task, Person newOwner);

	/**
	 * Označí úkol jako "dokončený".
	 * 
	 * @param task
	 * @return boolean
	 */
	public boolean finishTask(Task task);

	/**
	 * Nastaví kontext úkolu.
	 * 
	 * @param task
	 * @param context
	 * @return boolean
	 */
	public boolean setContext(Task task, Context context);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

	/**
	 * Vytvoří úkol a hned ho označí jako "hotový" (používá se při zpracování činnosti,
	 * pokud ji mohu a chci dokončit do 2 minut).
	 * 
	 * @param nazev
	 * @param popis
	 * @param projektId
	 * @param activity    Činnost, ze které úkol vznikl (volitelné).
	 * @return boolean
	 */
	public boolean addTwoMinutesTask(String nazev, String popis, int projektId, Activity activity);

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * 
	 * @param person
	 * @return List of person's tasks
	 */
	public List getUkolyOsoby(Person person);

	/**
	 * Vrátí všechny úkoly
	 * @return List of all tasks
	 */
	public List getAllUkoly();
}