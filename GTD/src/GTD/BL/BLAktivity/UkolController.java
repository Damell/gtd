package GTD.BL.BLAktivity;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.BL.BLInterfaces.IUkolController;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Ukol;
import java.util.List;

/**
 * Třída implementuje interface IUkolController.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class UkolController implements IUkolController {

	private SpravceUkolu spravceUkolu;
	private IGTDGUI GUI;

	public UkolController(){
		spravceUkolu = new SpravceUkolu();
	}

	/**
	 * Přidá nový úkol zadaných vlastností.
	 * 
	 * @param ukol
	 * @param cinnost    Činnost, ze které úkol vznikl (volitelné).
	 */
	public boolean addUkol(Ukol ukol, Cinnost cinnost){
		return false;
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
		return false;
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
	 * Vytvoří úkol a hned ho označí jako "hotový" (používá se při zpracování činnosti,
	 * pokud ji mohu a chci dokončit do 2 minut).
	 * 
	 * @param ukol
	 */
	public boolean addTwoMinutesUkol(Ukol ukol){
		return false;
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

}