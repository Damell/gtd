package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Činnostmi.
 * @version 1.0
 */
public interface IActivityController {

	/**
	 * Přidá novou činnost zadaných vlastností.
	 * 
	 * @param nazev 
	 * @param popis 
	 * @return  
	 */
	public boolean addCinnost(String nazev, String popis);

	/**
	 * Smaže činnost.
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean deleteCinnost(Activity cinnost);

	/**
	 * Vrátí činnosti konkrétní osoby.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getCinnostiOsoby(Person osoba);

	/**
	 * Označí činnost jako "zpracovanou".
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean processCinnost(Activity cinnost);

	/**
	 * Označí činnost jako "archivovanou".
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean archiveCinnost(Activity cinnost);

	/**
	 * Uloží změněnou činnost (změna jména/popisu).
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean updateCinnost(Activity cinnost);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

	/**
	 * Označí činnost jako "odloženou".
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean postponeCinnost(Activity cinnost);

}