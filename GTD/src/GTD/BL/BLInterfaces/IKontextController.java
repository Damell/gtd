package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Kontexty.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IKontextController {

	/**
	 * Přidá nový kotext zadané osobě.
	 * 
	 * @param kontext
	 * @param osoba
	 */
	public boolean addKontext(Kontext kontext, Osoba osoba);

	/**
	 * Smaže kontext.
	 * 
	 * @param kontext
	 */
	public boolean deleteKontext(Kontext kontext);

	/**
	 * Změní název kontextu.
	 * 
	 * @param kontext
	 */
	public boolean updateKontext(Kontext kontext);

	/**
	 * Pošle GUI pokyn k obnovení.
	 */
	public void refresh();

}