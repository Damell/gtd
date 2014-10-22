package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface pro správu Projektu v databázi.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:53
 */
public interface IDAOProject {

	/**
	 * Vytvorí nový projekt zadaných vlastností a uloží ho do databáze.
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean createProjekt(Project projekt);

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
	 * a podprojekty.
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean deleteProjekt(Project projekt);

	/**
	 * Vrátí všechny projekty v systému.
	 * @return
	 */
	public List<Project> getAllProjekty();

	/**
	 * Vrátí projekt podle jeho ID.
	 * @return
	 * 
	 * @param id
	 */
	public Project getProjekt(int id);

	/**
	 * Vrátí všechny projekty patřící zadané osobe.
	 * @return
	 * 
	 * @param osoba
	 */
	public List<Project> getProjektyOsoby(Person osoba);

	/**
	 * Uloží zmenený projekt.
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean updateProjekt(Project projekt);

}