package GTD.PL.PLView;

/**
 * Rozhraní pro okno (záložku) uživatelského rozhraní.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public interface IView {

	/**
	 * Aktualizuje pohled.
	 */
	public void refresh();

	/**
	 * Zobrazí daný pohled.
	 */
	public void show();

}