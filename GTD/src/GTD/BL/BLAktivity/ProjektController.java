package GTD.BL.BLAktivity;

import GTD.BL.BLInterfaces.*;
import GTD.DL.DLEntity.*;
import java.util.List;

/**
 * Třída implementuje interface IProjektController.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:23
 */
public class ProjektController implements IProjektController {

	private SpravceProjektu spravceProjektu;
	private IGTDGUI GUI;

	public ProjektController(){

	}

	/**
	 * Přidá nový projekt zadaných vlastností.
	 * 
	 * @param projekt
	 * @param cinnost    Činnost, ze které projekt vznikl (volitelné).
	 */
	@Override
	public boolean addProjekt(Projekt projekt, Cinnost cinnost) {
		return false;
	}

	/**
	 * Smaže projekt (resp. označí jako smazaný).
	 * 
	 * @param projekt
	 */
	@Override
	public boolean deleteProjekt(Projekt projekt){
		return false;
	}

	/**
	 * Změní název a/nebo popis projektu.
	 * 
	 * @param projekt
	 */
	@Override
	public boolean updateProjekt(Projekt projekt){
		return false;
	}

	/**
	 * Vrátí konkrétní projekt (GUI toto používá pro nastavení aktuálně zobrazeného
	 * projektu).
	 * 
	 * @param id
	 */
	@Override
	public boolean getCurrentProjekt(int id){
		return false;
	}

	/**
	 * Změní vlastníka projektu. Změnit vlastníka projektu může jen jeho vlastník nebo
	 * vlastník prvního nadřazeného projektu.
	 * 
	 * @param projekt
	 * @param novyVlastnik
	 */
	@Override
	public boolean changeOwner(Projekt projekt, Osoba novyVlastnik){
		return false;
	}

	/**
	 * Označí projekt jako "dokončený".
	 * 
	 * @param projekt
	 */
	@Override
	public boolean finishProjekt(Projekt projekt){
		return false;
	}

	/**
	 * Vrátí všechny projekty patřící dané osobě.
	 * 
	 * @param osoba
	 */
	@Override
	public List getProjektyOsoby(Osoba osoba){
		return null;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

}