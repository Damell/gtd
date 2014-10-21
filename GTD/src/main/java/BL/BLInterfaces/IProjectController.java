package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Person;

/**
 * Interface defines the way BL and PL communicates concerning projects
 * @author GTD team
 * @version 1.0
 * @created 19-10-2014 12:30:54
 */
public interface IProjectController {

	/**
	 * Přidá nový projekt zadaných vlastností.
	 * @return
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
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean deleteProject(Project projekt);

	/**
	 * Označí projekt jako "dokončený".
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean finishProject(Project projekt);

	/**
	 * Vrátí všechny projekty
	 * @return
	 */
	public List getAllProjects();

	/**
	 * Vrátí konkrétní projekt (GUI toto používá pro nastavení aktuálně zobrazeného
	 * projektu).
	 * @return
	 * 
	 * @param id
	 */
	public Project getProject(int id);

	/**
	 * Vrátí všechny projekty patřící dané osobě.
	 * @return
	 * 
	 * @param osoba
	 */
	public List getProjectsOfPerson(Person osoba);

	/**
	 * Změní vlastníka projektu.
	 * @return
	 * 
	 * @param projekt
	 * @param novyVlastnik
	 */
	public boolean changeOwner(Project projekt, Person novyVlastnik);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

	/**
	 * Změní název a/nebo popis projektu.
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean updateProject(Project projekt);

}