package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import java.util.List;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Projekty.
 * @version 1.0
 */
public interface IProjektController {

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
	public boolean addProjekt(String nazev, String popis, int vlastnik, int rodicID, List<Osoba> skupina, Cinnost cinnost);

	/**
	 * Smaže projekt (resp. označí jako smazaný).
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean deleteProjekt(Projekt projekt);

	/**
	 * Změní název a/nebo popis projektu.
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean updateProjekt(Projekt projekt);

	/**
	 * Vrátí konkrétní projekt (GUI toto používá pro nastavení aktuálně zobrazeného
	 * projektu).
	 * 
	 * @param id
	 * @return 
	 */
	public Projekt getProjekt(int id);

	/**
	 * Změní vlastníka projektu.
	 * 
	 * @param projekt
	 * @param novyVlastnik
	 * @return 
	 */
	public boolean changeOwner(Projekt projekt, Osoba novyVlastnik);

	/**
	 * Označí projekt jako "dokončený".
	 * 
	 * @param projekt
	 * @return 
	 */
	public boolean finishProjekt(Projekt projekt);

	/**
	 * Vrátí všechny projekty patřící dané osobě.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getProjektyOsoby(Osoba osoba);

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