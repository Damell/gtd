package GTD.BL.BLInterfaces;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Interface defines the way BL and PL communicates concerning activities
 * @author GTD team
 * @version 1.0
 */
public interface IActivityController {

	/**
	 * Adds new activity
	 * 
	 * @param title 
	 * @param desc 
	 * @return  
	 */
	public boolean addActivity(String title, String desc);

	/**
	 * Smaže činnost.
	 * 
	 * @param activity
	 * @return 
	 */
	public boolean deleteActivity(Activity activity);

	/**
	 * Vrátí činnosti konkrétní osoby.
	 * 
	 * @param person
	 * @return 
	 */
	public List getActivitiesOfPerson(Person person);

	/**
	 * Označí činnost jako "zpracovanou".
	 * 
	 * @param activity
	 * @return 
	 */
	public boolean processActivity(Activity activity);

	/**
	 * Označí činnost jako "archivovanou".
	 * 
	 * @param activity
	 * @return 
	 */
	public boolean archiveActivity(Activity activity);

	/**
	 * Uloží změněnou činnost (změna jména/popisu).
	 * 
	 * @param activity
	 * @return 
	 */
	public boolean updateActivity(Activity activity);

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh();

	/**
	 * Označí činnost jako "odloženou".
	 * 
	 * @param activity
	 * @return 
	 */
	public boolean postponeActivity(Activity activity);

}