package GTD.PL.PLView;

/**
 * Rozhraní pro okno (záložku) uživatelského rozhraní.
 * @version 1.0
 */
public interface IView {

	/**
	 * Aktualizuje pohled.
	 */
	public void refresh();

	/**
	 * Zobrazí daný pohled.
	 */
	public void showView();

}