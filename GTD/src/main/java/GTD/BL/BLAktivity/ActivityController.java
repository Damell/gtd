package GTD.BL.BLAktivity;

import GTD.DL.DLInterfaces.IDAOState;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import GTD.BL.BLInterfaces.IActivityController;
import java.util.List;

/**
 * Třída implementuje interface IActivityController.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:50
 */
public class ActivityController implements IActivityController {

	private IDAOState DAOStav;
	private IGTDGUI GUI;
	private ActivitiyAdmin spravceCinnosti;



	public void finalize() throws Throwable {

	}

	public ActivityController(){

	}

	/**
	 * Přidá novou činnost zadaných vlastností.
	 * 
	 * @param nazev
	 * @param popis    popis
	 */
	@Override
	public boolean addActivity(String nazev, String popis){
		return false;
	}

	/**
	 * Označí činnost jako "archivovanou".
	 * 
	 * @param cinnost    cinnost
	 */
	@Override
	public boolean archiveActivity(Activity cinnost){
		return false;
	}

	/**
	 * Smaže činnost.
	 * 
	 * @param cinnost    cinnost
	 */
	@Override
	public boolean deleteActivity(Activity cinnost){
		return false;
	}

	/**
	 * Vrátí činnosti konkrétní osoby.
	 * 
	 * @param osoba    osoba
	 */
	@Override
	public List<Activity> getActivitiesOfPerson(Person osoba){
		return null;
	}

	/**
	 * Označí činnost jako "odloženou".
	 * 
	 * @param cinnost    cinnost
	 */
	@Override
	public boolean postponeActivity(Activity cinnost){
		return false;
	}

	/**
	 * Označí činnost jako "zpracovanou".
	 * 
	 * @param cinnost    cinnost
	 */
	@Override
	public boolean processActivity(Activity cinnost){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

	/**
	 * Uloží změněnou činnost (změna jména/popisu).
	 * 
	 * @param cinnost    cinnost
	 */
	@Override
	public boolean updateActivity(Activity cinnost){
		return false;
	}

}