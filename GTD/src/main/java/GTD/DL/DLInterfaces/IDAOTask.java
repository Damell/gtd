package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface pro správu Úkolu v databázi.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:53
 */
public interface IDAOTask extends IDAOGeneric<Task> 
{

	public List<Task> getAll();
	
	public Task get(int id);
	
//	/**
//	 * Vytvorí nový úkol zadaných vlastností a uloží ho do databáze.
//	 * @return
//	 * 
//	 * @param ukol
//	 */
//	public boolean createUkol(Task ukol);
//
//	/**
//	 * Smaže úkol z databáze (resp. označí jako smazaný).
//	 * @return
//	 * 
//	 * @param ukol
//	 */
//	public boolean deleteUkol(Task ukol);
//
//	/**
//	 * Vrátí všechny úkoly v systému.
//	 * @return
//	 */
//	public List getAllUkoly();
//
//	/**
//	 * Vrátí úkol podle jeho ID.
//	 * @return
//	 * 
//	 * @param id
//	 */
//	public Task getUkol(int id);

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * @return
	 * 
	 * @param kontext
	 */
	public List getUkolyKontextu(Context kontext);

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * @return
	 * 
	 * @param osoba
	 */
	public List getUkolyOsoby(Person osoba);

//	/**
//	 * Uloží zmenený úkol.
//	 * @return
//	 * 
//	 * @param úkol
//	 */
//	public boolean updateUkol(Task úkol);

}