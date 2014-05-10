package GTD.BL.BLAktivity;

import GTD.BL.BLInterfaces.*;
import GTD.DL.DLDAO.DAOStav;
import GTD.DL.DLEntity.*;
import GTD.DL.DLInterfaces.IDAOStav;
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
        private IDAOStav DAOStav;

	public ProjektController(){
		spravceProjektu = new SpravceProjektu();
                DAOStav = new DAOStav();
	}

	/**
	 * Přidá nový projekt zadaných vlastností.
	 * 
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param vlastnik
	 * @param rodicID
	 * @param cinnost    Činnost, ze které projekt vznikl (volitelné).
		 */
	@Override
	public boolean addProjekt(String nazev, String popis, int vlastnik, int rodicID, List<Osoba> skupina, Cinnost cinnost) {
		Projekt rodic = null;
		if (rodicID != -1) {
			rodic = spravceProjektu.getProjekt(rodicID);
		}
		Projekt newProjekt = new Projekt(nazev, popis, DAOStav.getProjektAktivniID(), vlastnik, skupina, rodic);
		return spravceProjektu.addProjekt(newProjekt, cinnost);
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
		return spravceProjektu.getProjektyOsoby(osoba);
	}

	/**
	 * Vrátí všechny projekty
	 * 
	 */
	@Override
	public List getAllProjekty(){
		return spravceProjektu.getAllProjekty();
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

}