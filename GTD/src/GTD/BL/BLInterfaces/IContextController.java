package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;

/**
 * Interface defines the way BL and PL communicates concerning contexts
 * @author GTD team
 * @version 1.0
 */
public interface IContextController {

	/**
	 * Přidá nový kotext zadané osobě.
	 * 
	 * @param context
	 * @param person
	 * @return boolean
	 */
	public boolean addContext(Context context, Person person);

	/**
	 * Smaže kontext.
	 * 
	 * @param context
	 * @return boolean
	 */
	public boolean deleteContext(Context context);

	/**
	 * Změní název kontextu.
	 * 
	 * @param context
	 * @return boolean
	 */
	public boolean updateContext(Context context);

	/**
	 * Pošle GUI pokyn k obnovení.
	 */
	public void refresh();

}