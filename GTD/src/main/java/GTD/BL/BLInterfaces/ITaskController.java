package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Interval;

/**
 * Interface defines the way BL and PL communicates concerning tasks
 * @author GTD team
 * @version 1.0
 * @created 19-10-2014 12:30:54
 */
public interface ITaskController {

	/**
	 * Označí úkol jako aktivní.
	 * @return boolean
	 * 
	 * @param task
	 */
	public boolean activateTask(Task task);

	/**
	 * Přidá nový úkol zadaných vlastností.
	 * @return boolean
	 * 
	 * @param title
	 * @param desc
	 * @param ownerId
	 * @param projektId
	 * @param activity    Činnost, ze které úkol vznikl (volitelné).
	 */
	public boolean addTask(String title, String desc, int ownerId, int projektId, Activity activity);

	/**
	 * Vytvoří úkol a hned ho označí jako "hotový" (používá se při zpracování činnosti,
	 * pokud ji mohu a chci dokončit do 2 minut).
	 * @return boolean
	 * 
	 * @param nazev
	 * @param popis
	 * @param projektId
	 * @param activity    Činnost, ze které úkol vznikl (volitelné).
	 */
	public boolean addTwoMinutesTask(String nazev, String popis, int projektId, Activity activity);

	/**
	 * Smaže úkol (resp. označí jako smazaný).
	 * @return boolean
	 * 
	 * @param task
	 */
	public boolean deleteTask(Task task);

	/**
	 * Označí úkol jako "dokončený".
	 * @return boolean
	 * 
	 * @param task
	 */
	public boolean finishTask(Task task);

	/**
	 * Vrátí všechny úkoly
	 * @return List of all tasks
	 */
	public List getAllUkoly();

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * @return List of person's tasks
	 * 
	 * @param person
	 */
	public List getUkolyOsoby(Person person);

	/**
	 * Změní vlastníka úkolu.
	 * @return boolean
	 * 
	 * @param task
	 * @param newOwner
	 */
	public boolean changeOwner(Task task, Person newOwner);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

	/**
	 * Nastaví kontext úkolu.
	 * @return boolean
	 * 
	 * @param task
	 * @param context
	 */
	public boolean setContext(Task task, Context context);

	/**
	 * Přidá úkol do kalendáře.
	 * @return boolean
	 * 
	 * @param task
	 * @param interval
	 */
	public boolean scheduleTask(Task task, Interval interval);

	/**
	 * Změní název a/nebo popis úkolu.
	 * @return boolean
	 * 
	 * @param task
	 */
	public boolean updateTask(Task task);

}