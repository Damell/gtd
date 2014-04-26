package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import java.util.List;

/**
 * Interface pro správu Projektu v databázi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IDAOProjekt {

	/**
	 * Vytvorí nový projekt zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param projekt
	 */
	public boolean createProjekt(Projekt projekt);

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
	 * a podprojekty.
	 * 
	 * @param projekt
	 */
	public boolean deleteProjekt(Projekt projekt);

	/**
	 * Vrátí všechny projekty v systému.
	 */
	public List<Projekt> getAllProjekty();

	/**
	 * Vrátí projekt podle jeho ID.
	 * 
	 * @param id
	 */
	public Projekt getProjekt(int id);

	/**
	 * Uloží zmenený projekt.
	 * 
	 * @param projekt
	 */
	public boolean updateProjekt(Projekt projekt);

	/**
	 * Vrátí všechny projekty patřící zadané osobe.
	 * 
	 * @param osoba
	 */
	public Projekt getProjektyOsoby(Osoba osoba);

}