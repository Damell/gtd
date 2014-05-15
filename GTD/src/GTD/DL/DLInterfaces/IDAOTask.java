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
	public boolean createUkol(Task ukol);

	/**
	 * Smaže úkol z databáze (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean deleteUkol(Task ukol);

	/**
	 * Vrátí všechny úkoly v systému.
	 * @return 
	 */
	public List getAllUkoly();

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getUkolyOsoby(Person osoba);

	/**
	 * Vrátí úkol podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Task getUkol(int id);

	/**
	 * Uloží zmenený úkol.
	 * 
	 * @param úkol
	 * @return 
	 */
	public boolean updateUkol(Task úkol);

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	public List getUkolyKontextu(Context kontext);

}