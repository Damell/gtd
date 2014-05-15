package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import java.util.List;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Projekty.
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
	 * @return 
	 */
	public boolean addProjekt(String nazev, String popis, int vlastnik, int rodicID, List<Person> skupina, Activity cinnost);

	/**
	 * Smaže projekt (resp. označí jako smazaný).
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean deleteProjekt(Project projekt);

	/**
	 * Změní název a/nebo popis projektu.
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean updateProjekt(Project projekt);

	/**
	 * Vrátí konkrétní projekt (GUI toto používá pro nastavení aktuálně zobrazeného
	 * projektu).
	 * 
	 * @param id
	 * @return 
	 */
	public Project getProjekt(int id);

	/**
	 * Změní vlastníka projektu.
	 * 
	 * @param projekt
	 * @param novyVlastnik
	 * @return 
	 */
	public boolean changeOwner(Project projekt, Person novyVlastnik);

	/**
	 * Označí projekt jako "dokončený".
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean finishProjekt(Project projekt);

	/**
	 * Vrátí všechny projekty patřící dané osobě.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getProjektyOsoby(Person osoba);

	/**
	 * Vrátí všechny projekty 
	 * 
	 * @return 
	 */
	public List getAllProjekty();

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

}