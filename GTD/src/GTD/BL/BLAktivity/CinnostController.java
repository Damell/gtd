package GTD.BL.BLAktivity;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.BL.BLInterfaces.ICinnostController;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
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

	public CinnostController(){
		spravceCinnosti = new SpravceCinnosti();
	}

	/**
	 * Přidá novou činnost zadaných vlastností.
	 * 
	 * @param cinnost
	 */
	public boolean addCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Smaže činnost.
	 * 
	 * @param cinnost
	 */
	public boolean deleteCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Vrátí činnosti konkrétní osoby.
	 * 
	 * @param osoba
	 */
	public List<Cinnost> getCinnostiOsoby(Osoba osoba){
		return spravceCinnosti.getCinnostiOsoby(osoba);
	}

	/**
	 * Označí činnost jako "zpracovanou".
	 * 
	 * @param cinnost
	 */
	public boolean processCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Označí činnost jako "archivovanou".
	 * 
	 * @param cinnost
	 */
	public boolean archiveCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Uloží změněnou činnost (změna jména/popisu).
	 * 
	 * @param cinnost
	 */
	public boolean updateCinnost(Cinnost cinnost){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh(){

	}

	/**
	 * Označí činnost jako "odloženou".
	 * 
	 * @param cinnost
	 */
	@Override
	public boolean postponeCinnost(Cinnost cinnost){
		return false;
	}

}