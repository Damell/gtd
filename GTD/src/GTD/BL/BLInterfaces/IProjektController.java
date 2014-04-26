package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import java.util.List;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Projekty.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IProjektController {

	/**
	 * Přidá nový projekt zadaných vlastností.
	 * 
	 * @param projekt
	 * @param cinnost    Činnost, ze které projekt vznikl (volitelné).
	 */
	public boolean addProjekt(Projekt projekt, Cinnost cinnost);

	/**
	 * Smaže projekt (resp. označí jako smazaný).
	 * 
	 * @param projekt
	 */
	public boolean deleteProjekt(Projekt projekt);

	/**
	 * Změní název a/nebo popis projektu.
	 * 
	 * @param projekt
	 */
	public boolean updateProjekt(Projekt projekt);

	/**
	 * Vrátí konkrétní projekt (GUI toto používá pro nastavení aktuálně zobrazeného
	 * projektu).
	 * 
	 * @param id
	 */
	public boolean getCurrentProjekt(int id);

	/**
	 * Změní vlastníka projektu.
	 * 
	 * @param projekt
	 * @param novyVlastnik
	 */
	public boolean changeOwner(Projekt projekt, Osoba novyVlastnik);

	/**
	 * Označí projekt jako "dokončený".
	 * 
	 * @param projekt
	 */
	public boolean finishProjekt(Projekt projekt);

	/**
	 * Vrátí všechny projekty patřící dané osobě.
	 * 
	 * @param osoba
	 */
	public List getProjektyOsoby(Osoba osoba);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

}