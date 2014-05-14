package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import java.util.List;

/**
 * Interface pro správu Projektu v databázi.
 * @version 1.0
 */
public interface IDAOProjekt {

	/**
	 * Vytvorí nový projekt zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean createProjekt(Projekt projekt);

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
	 * a podprojekty.
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean deleteProjekt(Projekt projekt);

	/**
	 * Vrátí všechny projekty v systému.
         * 
	 * @return 
	 */
	public List<Projekt> getAllProjekty();

	/**
	 * Vrátí projekt podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Projekt getProjekt(int id);

	/**
	 * Uloží zmenený projekt.
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean updateProjekt(Projekt projekt);

	/**
	 * Vrátí všechny projekty patřící zadané osobe.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List<Projekt> getProjektyOsoby(Osoba osoba);

}