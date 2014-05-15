package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.*;

/**
 * Rozhraní pro komunikaci přímo s hlavní třídou GUI (PL) (v BL používáno po změně
 * dat pro pokyn k aktualizaci dat v GUI).
 * @author GTD team
 * @version 1.0
 */
public interface IGTDGUI {

	/**
	 * Refreshes all concerned views
	 */
	public void refresh();

	/**
	 * Shows view with actions concerning activities
	 */
	public void showActivities();

	/**
	 * Shows process activity dialog
	 * 
	 * @param activity
	 */
	public void showProcessActivity(Activity activity);

	/**
	 * Shows view with all tasks and projects
	 */
	public void showUkolyProjekty();

	/**
	 * Shows tasks of the logged in user
	 */
	public void showMojeUkoly();

	/**
	 * Shows the login view
	 */
	public void showPrihlaseni();

	/**
	 * Shows an error message
	 * @param error
	 */
	public void showError(String error);

}