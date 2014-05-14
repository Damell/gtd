package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import java.util.List;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Činnostmi.
 * @version 1.0
 */
public interface ICinnostController {

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
	public boolean deleteCinnost(Cinnost cinnost);

	/**
	 * Vrátí činnosti konkrétní osoby.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getCinnostiOsoby(Osoba osoba);

	/**
	 * Označí činnost jako "zpracovanou".
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean processCinnost(Cinnost cinnost);

	/**
	 * Označí činnost jako "archivovanou".
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean archiveCinnost(Cinnost cinnost);

	/**
	 * Uloží změněnou činnost (změna jména/popisu).
	 * 
	 * @param cinnost
	 * @return 
	 */
	public boolean updateCinnost(Cinnost cinnost);

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
	public boolean postponeCinnost(Cinnost cinnost);

}