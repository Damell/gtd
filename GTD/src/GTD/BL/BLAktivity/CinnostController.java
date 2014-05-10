package GTD.BL.BLAktivity;
import GTD.BL.BLInterfaces.ICinnostController;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOTypy;
import GTD.PL.PLView.GTDGUI;
import java.util.List;

/**
 * Třída implementuje interface ICinnostController.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:20
 */
public class CinnostController implements ICinnostController {

	private SpravceCinnosti spravceCinnosti;
	private IGTDGUI GUI;
        private IDAOTypy TypId;

	public CinnostController(){
		spravceCinnosti = new SpravceCinnosti();
	}

	/**
	 * Přidá novou činnost zadaných vlastností.
	 * 
	 * @param cinnost
	 */
	@Override
	public boolean addCinnost(String nazev, String popis){
                //int id=TypId.getCinnostKeZpracovaniID();
                //System.out.println("TYP "+id);
		Cinnost newCinnost = new Cinnost(nazev, popis, 48, GTDGUI.getMyself().getId());
		return spravceCinnosti.addCinnost(newCinnost);
	}

	/**
	 * Smaže činnost.
	 * 
	 * @param cinnost
	 */
	@Override
	public boolean deleteCinnost(Cinnost cinnost){
		return spravceCinnosti.deleteCinnost(cinnost);
	}

	/**
	 * Vrátí činnosti konkrétní osoby.
	 * 
	 * @param osoba
	 */
	@Override
	public List<Cinnost> getCinnostiOsoby(Osoba osoba){
		return spravceCinnosti.getCinnostiOsoby(osoba);
	}

	/**
	 * Označí činnost jako "zpracovanou".
	 * 
	 * @param cinnost
	 */
	@Override
	public boolean processCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Označí činnost jako "archivovanou".
	 * 
	 * @param cinnost
	 */
	@Override
	public boolean archiveCinnost(Cinnost cinnost){
		return spravceCinnosti.archiveCinnost(cinnost);
	}

	/**
	 * Uloží změněnou činnost (změna jména/popisu).
	 * 
	 * @param cinnost
	 */
	@Override
	public boolean updateCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){
		GTDGUI.getGTDGUI().refresh();
	}

	/**
	 * Označí činnost jako "odloženou".
	 * 
	 * @param cinnost
	 */
	@Override
	public boolean postponeCinnost(Cinnost cinnost){
		return spravceCinnosti.postponeCinnost(cinnost);
	}

}