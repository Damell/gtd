package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.*;

/**
 * Rozhraní pro komunikaci přímo s hlavní třídou GUI (PL) (v BL používáno po změně
 * dat pro pokyn k aktualizaci dat v GUI).
 * @version 1.0
 */
public interface IGTDGUI {

	/**
	 * Předá dalším vrstvám GUI pokyn k obnovení.
	 */
	public void refresh();

	/**
	 * Shows view with actions concerning activities
	 */
	public void showActivities();

	/**
	 * Zobrazí dialog se zpracováním činnosti.
	 * 
	 * @param cinnost
	 */
	public void showProcessActivity(Activity cinnost);

	/**
	 * Zobrazí okno s úkoly a projekty všech osob
	 */
	public void showUkolyProjekty();

	/**
	 * Zobrazí úkoly přihlášené osoby
	 * 
	 */
	public void showMojeUkoly();

	/**
	 * Zobrazí přihlašovací okno.
	 */
	public void showPrihlaseni();

	/**
	 * Zobrazí chybovou hlášku.
	 * @param error
	 */
	public void showError(String error);

}