package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Kontexty.
 * @version 1.0
 */
public interface IKontextController {

	/**
	 * Přidá nový kotext zadané osobě.
	 * 
	 * @param kontext
	 * @param osoba
	 * @return 
	 */
	public boolean addKontext(Kontext kontext, Osoba osoba);

	/**
	 * Smaže kontext.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean deleteKontext(Kontext kontext);

	/**
	 * Změní název kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean updateKontext(Kontext kontext);

	/**
	 * Pošle GUI pokyn k obnovení.
	 */
	public void refresh();

}