package GTD.PL.PLView;

/**
 * Interface for tabs and panels in the GUI
 * Gives access to easy refreshing of the view when data changes
 * @author GTD team
 * @version 1.0
 */
public interface IView {

	/**
	 * Refreshes the view
	 */
	public void refresh();

	/**
	 * Makes the view visible
	 */
	public void showView();

}