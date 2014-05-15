package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;

/**
 * Interface definuje způsob komunikace mezi BL a PL týkající se manipulace s
 * Kontexty.
 * @version 1.0
 */
public interface IContextController {

	/**
	 * Přidá nový kotext zadané osobě.
	 * 
	 * @param kontext
	 * @param osoba
	 * @return 
	 */
	public boolean addKontext(Context kontext, Person osoba);

	/**
	 * Smaže kontext.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean deleteKontext(Context kontext);

	/**
	 * Změní název kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean updateKontext(Context kontext);

	/**
	 * Pošle GUI pokyn k obnovení.
	 */
	public void refresh();

}