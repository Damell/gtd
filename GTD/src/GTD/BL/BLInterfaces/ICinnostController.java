package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import java.util.List;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Činnostmi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface ICinnostController {

	/**
	 * Přidá novou činnost zadaných vlastností.
	 * 
	 * @param cinnost
	 */
	public boolean addCinnost(Cinnost cinnost);

	/**
	 * Smaže činnost.
	 * 
	 * @param cinnost
	 */
	public boolean deleteCinnost(Cinnost cinnost);

	/**
	 * Vrátí činnosti konkrétní osoby.
	 * 
	 * @param osoba
	 */
	public List getCinnostiOsoby(Osoba osoba);

	/**
	 * Označí činnost jako "zpracovanou".
	 * 
	 * @param cinnost
	 */
	public boolean processCinnost(Cinnost cinnost);

	/**
	 * Označí činnost jako "archivovanou".
	 * 
	 * @param cinnost
	 */
	public boolean archiveCinnost(Cinnost cinnost);

	/**
	 * Uloží změněnou činnost (změna jména/popisu).
	 * 
	 * @param cinnost
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
	 */
	public boolean postponeCinnost(Cinnost cinnost);

}