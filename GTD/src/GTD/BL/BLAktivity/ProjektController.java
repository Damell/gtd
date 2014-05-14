package GTD.BL.BLAktivity;

import GTD.BL.BLInterfaces.*;
import GTD.DL.DLDAO.DAOStav;
import GTD.DL.DLEntity.*;
import GTD.DL.DLInterfaces.IDAOStav;
import java.util.List;

/**
 * Třída implementuje interface IProjektController.
 * @version 1.0
 */
public class ProjektController implements IProjektController {

	private SpravceProjektu spravceProjektu;
	private IDAOStav DAOStav;

	/**
	 *
	 */
	public ProjektController(){
		spravceProjektu = new SpravceProjektu();
		DAOStav = new DAOStav();
	}

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
	 * @return 
	 */
	@Override
	public boolean deleteProjekt(Projekt projekt){
		return spravceProjektu.deleteProjekt(projekt);
	}

	/**
	 * Změní název a/nebo popis projektu.
	 * 
	 * @param projekt
	 * @return 
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
	 * @return 
	 */
	@Override
	public Projekt getProjekt(int id){
		return spravceProjektu.getProjekt(id);
	}

	/**
	 * Změní vlastníka projektu. Změnit vlastníka projektu může jen jeho vlastník nebo
	 * vlastník prvního nadřazeného projektu.
	 * 
	 * @param projekt
	 * @param novyVlastnik
	 * @return 
	 */
	@Override
	public boolean changeOwner(Projekt projekt, Osoba novyVlastnik){
		projekt.setVlastnikID(novyVlastnik.getId());
		return spravceProjektu.updateProjekt(projekt);
	}

	/**
	 * Označí projekt jako "dokončený".
	 * 
	 * @param projekt
	 * @return 
	 */
	@Override
	public boolean finishProjekt(Projekt projekt){
		projekt.setStav(DAOStav.getProjektDokoncenyID());
		return spravceProjektu.finishProjekt(projekt);
	}

	/**
	 * Vrátí všechny projekty patřící dané osobě.
	 * 
	 * @param osoba
	 * @return 
	 */
	@Override
	public List getProjektyOsoby(Osoba osoba){
		return spravceProjektu.getProjektyOsoby(osoba);
	}

	/**
	 * Vrátí všechny projekty
	 * 
	 * @return 
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