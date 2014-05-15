package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Task;
import java.util.List;

/**
 * Interface pro správu Úkolu v databázi.
 * @author GTD team
 * @version 1.0
 */
public interface IDAOTask {

	/**
	 * Vytvorí nový úkol zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean createTask(Task ukol);

	/**
	 * Smaže úkol z databáze (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean deleteTask(Task ukol);

	/**
	 * Vrátí všechny úkoly v systému.
	 * @return 
	 */
	public List getAllTasks();

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getTasksOfPerson(Person osoba);

	/**
	 * Vrátí úkol podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Task getTask(int id);

	/**
	 * Uloží zmenený úkol.
	 * 
	 * @param úkol
	 * @return 
	 */
	public boolean updateTask(Task úkol);

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	public List getTasksWithContext(Context kontext);

}