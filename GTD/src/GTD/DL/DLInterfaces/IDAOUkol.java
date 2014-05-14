package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Ukol;
import java.util.List;

/**
 * Interface pro správu Úkolu v databázi.
 * @version 1.0
 */
public interface IDAOUkol {

	/**
	 * Vytvorí nový úkol zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean createUkol(Ukol ukol);

	/**
	 * Smaže úkol z databáze (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean deleteUkol(Ukol ukol);

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
	public List getUkolyOsoby(Osoba osoba);

	/**
	 * Vrátí úkol podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Ukol getUkol(int id);

	/**
	 * Uloží zmenený úkol.
	 * 
	 * @param úkol
	 * @return 
	 */
	public boolean updateUkol(Ukol úkol);

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	public List getUkolyKontextu(Kontext kontext);

}