package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import java.util.List;

/**
 * Interface defines the way BL and PL communicates concerning projects
 * @author GTD team
 * @version 1.0
 */
public interface IProjectController {

	/**
	 * Přidá nový projekt zadaných vlastností.
	 * 
	 * @param nazev
	 * @param popis
	 * @param vlastnik
	 * @param rodicID
	 * @param skupina
	 * @param cinnost    Činnost, ze které projekt vznikl (volitelné).
	 */
	public boolean addProject(String nazev, String popis, int vlastnik, int rodicID, List<Person> skupina, Activity cinnost);

	/**
	 * Smaže projekt (resp. označí jako smazaný).
	 * 
	 * @param projekt
	 */
	public boolean deleteProject(Project projekt);

	/**
	 * Změní název a/nebo popis projektu.
	 * 
	 * @param projekt
	 */
	public boolean updateProject(Project projekt);

	/**
	 * Vrátí konkrétní projekt (GUI toto používá pro nastavení aktuálně zobrazeného
	 * projektu).
	 * 
	 * @param id
	 */
	public Project getProject(int id);

	/**
	 * Změní vlastníka projektu.
	 * 
	 * @param projekt
	 * @param novyVlastnik
	 */
	public boolean changeOwner(Project projekt, Person novyVlastnik);

	/**
	 * Označí projekt jako "dokončený".
	 * 
	 * @param projekt
	 */
	public boolean finishProject(Project projekt);

	/**
	 * Vrátí všechny projekty patřící dané osobě.
	 * 
	 * @param osoba
	 */
	public List getProjectsOfPerson(Person osoba);

	/**
	 * Vrátí všechny projekty 
	 * 
	 */
	public List getAllProjects();

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

}