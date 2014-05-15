package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Task;
import java.util.List;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Úkoly.
 * @version 1.0
 */
public interface ITaskController {

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
	public boolean addUkol(String nazev, String popis, int vlastnikId, int projektId, Activity cinnost);

	/**
	 * Smaže úkol (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean deleteUkol(Task ukol);

	/**
	 * Změní název a/nebo popis úkolu.
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean updateUkol(Task ukol);

	/**
	 * Označí úkol jako aktivní.
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean activateUkol(Task ukol);

	/**
	 * Přidá úkol do kalendáře.
	 * 
	 * @param ukol
	 * @param interval
	 * @return 
	 */
	public boolean scheduleUkol(Task ukol, Interval interval);

	/**
	 * Změní vlastníka úkolu.
	 * 
	 * @param ukol
	 * @param novyVlastnik
	 * @return 
	 */
	public boolean changeOwner(Task ukol, Person novyVlastnik);

	/**
	 * Označí úkol jako "dokončený".
	 * 
	 * @param ukol
	 * @return 
	 */
	public boolean finishUkol(Task ukol);

	/**
	 * Nastaví kontext úkolu.
	 * 
	 * @param ukol
	 * @param kontext
	 * @return 
	 */
	public boolean setKontext(Task ukol, Context kontext);

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
	public boolean addTwoMinutesUkol(String nazev, String popis, int projektId, Activity cinnost);

	/**
	 * Vrátí všechny úkoly přiřazené dané osobě
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getUkolyOsoby(Person osoba);

	/**
	 * Vrátí všechny úkoly
	 * @return 
	 */
	public List getAllUkoly();
}