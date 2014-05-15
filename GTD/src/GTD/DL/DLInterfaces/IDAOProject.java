package GTD.DL.DLInterfaces;

import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import java.util.List;

/**
 * Interface pro správu Projektu v databázi.
 * @author GTD team
 * @version 1.0
 */
public interface IDAOProject {

	/**
	 * Vytvorí nový projekt zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean createProjekt(Project projekt);

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
	 * a podprojekty.
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean deleteProjekt(Project projekt);

	/**
	 * Vrátí všechny projekty v systému.
         * 
	 * @return 
	 */
	public List<Project> getAllProjekty();

	/**
	 * Vrátí projekt podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Project getProjekt(int id);

	/**
	 * Uloží zmenený projekt.
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean updateProjekt(Project projekt);

	/**
	 * Vrátí všechny projekty patřící zadané osobe.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List<Project> getProjektyOsoby(Person osoba);

}