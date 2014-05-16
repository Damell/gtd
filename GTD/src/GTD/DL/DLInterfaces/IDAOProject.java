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
	 */
	public boolean createProject(Project projekt);

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
	 * a podprojekty.
	 * 
	 * @param projekt
	 */
	public boolean deleteProject(Project projekt);

	/**
	 * Vrátí všechny projekty v systému.
         * 
	 */
	public List<Project> getAllProjects();

	/**
	 * Vrátí projekt podle jeho ID.
	 * 
	 * @param id
	 */
	public Project getProject(int id);

	/**
	 * Uloží zmenený projekt.
	 * 
	 * @param projekt
	 */
	public boolean updateProject(Project projekt);

	/**
	 * Vrátí všechny projekty patřící zadané osobe.
	 * 
	 * @param osoba
	 */
	public List<Project> getProjectsOfPerson(Person osoba);

}