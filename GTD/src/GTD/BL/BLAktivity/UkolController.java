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
 * @version 1.0
 */
public class UkolController implements IUkolController {

	private SpravceUkolu spravceUkolu;
	private IDAOStav DAOStav;
	private IGTDGUI GUI;

	/**
	 *
	 */
	public UkolController(){
		spravceUkolu = new SpravceUkolu();
		DAOStav = new DAOStav();
	}

	/**
	 * Přidá nový úkol zadaných vlastností.
	 * 
	 * @param nazev
	 * @param popis
	 * @param vlastnikId
	 * @param projektId
	 * @param cinnost    Činnost, ze které úkol vznikl (volitelné).
	 * @return 
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
	 * @return 
	 */
	@Override
	public boolean addTwoMinutesUkol(String nazev, String popis, int projektId, Cinnost cinnost){
		Ukol ukol = new Ukol(nazev, popis, DAOStav.getUkolHotovyID(), GTDGUI.getMyself().getId(), GTDGUI.getMyself().getId(), projektId);
		return spravceUkolu.addUkol(ukol, cinnost);
	}

	/**
	 * Smaže úkol (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 * @return 
	 */
	@Override
	public boolean deleteUkol(Ukol ukol){
		return spravceUkolu.deleteUkol(ukol);
	}

	/**
	 * Změní název a/nebo popis úkolu.
	 * 
	 * @param ukol
	 * @return 
	 */
	@Override
	public boolean updateUkol(Ukol ukol){
		return false;
	}

	@Override
	public boolean activateUkol(Ukol ukol) {
		ukol.setStav(DAOStav.getUkolAktivniID());
		return spravceUkolu.updateUkol(ukol);
	}

	/**
	 * Přidá úkol do kalendáře.
	 * 
	 * @param ukol
	 * @param interval
	 * @return 
	 */
	@Override
	public boolean scheduleUkol(Ukol ukol, Interval interval){
		ukol.setInterval(interval.getFrom(), interval.getTo());
		ukol.setStav(DAOStav.getUkolVKalendariID());
		return spravceUkolu.updateUkol(ukol);
	}

	/**
	 * Změní vlastníka úkolu.
	 * 
	 * @param ukol
	 * @param novyVlastnik
	 * @return 
	 */
	@Override
	public boolean changeOwner(Ukol ukol, Osoba novyVlastnik){
		ukol.setVlastnikID(novyVlastnik.getId());
		return spravceUkolu.updateUkol(ukol);
	}

	/**
	 * Označí úkol jako "dokončený".
	 * 
	 * @param ukol
	 * @return 
	 */
	@Override
	public boolean finishUkol(Ukol ukol){
		ukol.setStav(DAOStav.getUkolHotovyID());
		return spravceUkolu.updateUkol(ukol);
	}

	/**
	 * Nastaví kontext úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 * @return 
	 */
	@Override
	public boolean setKontext(Ukol ukol, Kontext kontext){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * 
	 * @param osoba
	 * @return 
	 */
	@Override
	public List getUkolyOsoby(Osoba osoba) {
		return spravceUkolu.getUkolyOsoby(osoba);
	}

	/**
	 * Vrátí všechny úkoly
	 * @return 
	 */
	@Override
	public List getAllUkoly() {
		return spravceUkolu.getAllUkoly();
	}


}