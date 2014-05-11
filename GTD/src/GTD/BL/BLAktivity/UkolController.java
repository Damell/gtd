package GTD.BL.BLAktivity;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.BL.BLInterfaces.IUkolController;
import GTD.DL.DLDAO.DAOStav;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Ukol;
import GTD.DL.DLInterfaces.IDAOStav;
import GTD.PL.PLView.GTDGUI;
import java.util.List;

/**
 * Třída implementuje interface IUkolController.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class UkolController implements IUkolController {

	private SpravceUkolu spravceUkolu;
	private IDAOStav DAOStav;
	private IGTDGUI GUI;

	public UkolController(){
		spravceUkolu = new SpravceUkolu();
		DAOStav = new DAOStav();
	}

	/**
	 * Přidá nový úkol zadaných vlastností.
	 * 
	 * @param ukol
	 * @param cinnost    Činnost, ze které úkol vznikl (volitelné).
	 */
	@Override
	public boolean addUkol(String nazev, String popis, int vlastnikId, int projektId, Cinnost cinnost){

		if (vlastnikId == -1) vlastnikId = GTDGUI.getMyself().getId();
		Ukol ukol = new Ukol(nazev, popis, DAOStav.getUkolVytvorenyID(), GTDGUI.getMyself().getId(), vlastnikId, projektId);

		return spravceUkolu.addUkol(ukol, cinnost);
	}

	/**
	 * Vytvoří úkol a hned ho označí jako "hotový" (používá se při zpracování činnosti,
	 * pokud ji mohu a chci dokončit do 2 minut).
	 * 
	 * @param nazev
	 * @param popis
	 * @param projektId
	 * @param cinnost    Činnost, ze které úkol vznikl (volitelné).
	 */
	public boolean addTwoMinutesUkol(String nazev, String popis, int projektId, Cinnost cinnost){
		Ukol ukol = new Ukol(nazev, popis, DAOStav.getUkolHotovyID(), GTDGUI.getMyself().getId(), GTDGUI.getMyself().getId(), projektId);
		return spravceUkolu.addUkol(ukol, cinnost);
	}

	/**
	 * Smaže úkol (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 */
	public boolean deleteUkol(Ukol ukol){
		return false;
	}

	/**
	 * Změní název a/nebo popis úkolu.
	 * 
	 * @param ukol
	 */
	public boolean updateUkol(Ukol ukol){
		return false;
	}

	/**
	 * Přidá úkol do kalendáře.
	 * 
	 * @param ukol
	 * @param interval
	 */
	public boolean scheduleUkol(Ukol ukol, Interval interval){
		return false;
	}

	/**
	 * Změní vlastníka úkolu.
	 * 
	 * @param ukol
	 * @param novyVlastnik
	 */
	public boolean changeOwner(Ukol ukol, Osoba novyVlastnik){
		return false;
	}

	/**
	 * Označí úkol jako "dokončený".
	 * 
	 * @param ukol
	 */
	public boolean finishUkol(Ukol ukol){
		ukol.setStav(DAOStav.getUkolHotovyID());
		return spravceUkolu.finishUkol(ukol);
	}

	/**
	 * Nastaví kontext úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 */
	public boolean setKontext(Ukol ukol, Kontext kontext){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh(){

	}

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * 
	 * @param osoba
	 */
	@Override
	public List getUkolyOsoby(Osoba osoba) {
		return spravceUkolu.getUkolyOsoby(osoba);
	}

	/**
	 * Vrátí všechny úkoly
	 */
	@Override
	public List getAllUkoly() {
		return spravceUkolu.getAllUkoly();
	}

}