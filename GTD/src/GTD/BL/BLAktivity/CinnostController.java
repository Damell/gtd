package GTD.BL.BLAktivity;
import GTD.BL.BLInterfaces.ICinnostController;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.DL.DLDAO.DAOStav;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOStav;
import GTD.PL.PLView.GTDGUI;
import java.util.List;

/**
 * Třída implementuje interface ICinnostController.
 * @version 1.0
 */
public class CinnostController implements ICinnostController {

	private SpravceCinnosti spravceCinnosti;
	private IGTDGUI GUI;
	private IDAOStav DAOStav;

	/**
	 *
	 */
	public CinnostController(){
		spravceCinnosti = new SpravceCinnosti();
		DAOStav = new DAOStav();
	}

	/**
	 * Přidá novou činnost zadaných vlastností.
	 * 
	 * @param nazev
	 * @param popis
	 */
	@Override
	public boolean addCinnost(String nazev, String popis){
		Cinnost newCinnost = new Cinnost(nazev, popis, DAOStav.getCinnostKeZpracovaniID(), GTDGUI.getMyself().getId());
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