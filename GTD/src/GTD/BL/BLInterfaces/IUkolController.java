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
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IUkolController {

	/**
	 * Přidá nový úkol zadaných vlastností.
	 * 
	 * @param ukol
	 * @param cinnost    Činnost, ze které úkol vznikl (volitelné).
	 */
	public boolean addUkol(Ukol ukol, Cinnost cinnost);

	/**
	 * Smaže úkol (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 */
	public boolean deleteUkol(Ukol ukol);

	/**
	 * Změní název a/nebo popis úkolu.
	 * 
	 * @param ukol
	 */
	public boolean updateUkol(Ukol ukol);

	/**
	 * Přidá úkol do kalendáře.
	 * 
	 * @param ukol
	 * @param interval
	 */
	public boolean scheduleUkol(Ukol ukol, Interval interval);

	/**
	 * Změní vlastníka úkolu.
	 * 
	 * @param ukol
	 * @param novyVlastnik
	 */
	public boolean changeOwner(Ukol ukol, Osoba novyVlastnik);

	/**
	 * Označí úkol jako "dokončený".
	 * 
	 * @param ukol
	 */
	public boolean finishUkol(Ukol ukol);

	/**
	 * Nastaví kontext úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 */
	public boolean setKontext(Ukol ukol, Kontext kontext);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

	/**
	 * Vytvoří úkol a hned ho označí jako "hotový" (používá se při zpracování činnosti,
	 * pokud ji mohu a chci dokončit od 2 minut).
	 * 
	 * @param ukol
	 */
	public boolean addTwoMinutesUkol(Ukol ukol);

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * 
	 * @param osoba
	 */
	public List getUkolyOsoby(Osoba osoba);

}