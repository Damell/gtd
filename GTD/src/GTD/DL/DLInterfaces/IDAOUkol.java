package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Ukol;
import java.util.List;

/**
 * Interface pro správu Úkolu v databázi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IDAOUkol {

	/**
	 * Vytvorí nový úkol zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param ukol
	 */
	public boolean createUkol(Ukol ukol);

	/**
	 * Smaže úkol z databáze (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 */
	public boolean deleteUkol(Ukol ukol);

	/**
	 * Vrátí všechny úkoly v systému.
	 */
	public List getAllUkoly();

	/**
	 * Vrátí úkol podle jeho ID.
	 * 
	 * @param id
	 */
	public Ukol getUkol(int id);

	/**
	 * Uloží zmenený úkol.
	 * 
	 * @param úkol
	 */
	public boolean updateUkol(Ukol úkol);

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * 
	 * @param kontext
	 */
	public List getUkolyKontextu(Kontext kontext);

}