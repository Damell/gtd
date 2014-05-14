package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Ukol;
import java.util.List;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Úkoly.
 * @version 1.0
 */
public interface IUkolController {

	/**
	 * Přidá nový úkol zadaných vlastností.
	 * 
	 * @param nazev
	 * @param popis
	 * @param cinnost    Činnost, ze které úkol vznikl (volitelné).
	 * @param projektId
	 * @param vlastnikId
	 * @return 
	 */
	public boolean addUkol(String nazev, String popis, int vlastnikId, int projektId, Cinnost cinnost);

	/**
	 * Smaže úkol (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean deleteUkol(Ukol ukol);

	/**
	 * Změní název a/nebo popis úkolu.
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean updateUkol(Ukol ukol);

	/**
	 * Označí úkol jako aktivní.
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean activateUkol(Ukol ukol);

	/**
	 * Přidá úkol do kalendáře.
	 * 
	 * @param ukol
	 * @param interval
	 * @return 
	 */
	public boolean scheduleUkol(Ukol ukol, Interval interval);

	/**
	 * Změní vlastníka úkolu.
	 * 
	 * @param ukol
	 * @param novyVlastnik
	 * @return 
	 */
	public boolean changeOwner(Ukol ukol, Osoba novyVlastnik);

	/**
	 * Označí úkol jako "dokončený".
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean finishUkol(Ukol ukol);

	/**
	 * Nastaví kontext úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 * @return 
	 */
	public boolean setKontext(Ukol ukol, Kontext kontext);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

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
	public boolean addTwoMinutesUkol(String nazev, String popis, int projektId, Cinnost cinnost);

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getUkolyOsoby(Osoba osoba);

	/**
	 * Vrátí všechny úkoly
	 * @return 
	 */
	public List getAllUkoly();
}