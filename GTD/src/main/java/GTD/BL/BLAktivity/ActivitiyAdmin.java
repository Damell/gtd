package GTD.BL.BLAktivity;

import GTD.DL.DLInterfaces.IDAOActivity;
import GTD.DL.DLInterfaces.IDAOState;
import GTD.DL.DLDAO.DAOActivity;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Trída pro manipulaci s cinnostmi.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:50
 */
public class ActivitiyAdmin {

	private IDAOActivity DAOCinnost;
	private IDAOState DAOStav;
	public DAOActivity m_DAOCinnost;
	/**
	 * Správce osob - pomocí něj přistupují ostatní správci k přihlášenému uživateli.
	 */
	private PersonAdmin spravceOsob;



	public void finalize() throws Throwable {

	}

	public ActivitiyAdmin(){

	}

	/**
	 * Vytvorí novou cinnost.
	 * @return
	 * 
	 * @param cinnost
	 */
	public boolean addCinnost(Activity cinnost){
		return false;
	}

	/**
	 * Označí činnost jako "archivovanou". Toto může udělat pouze vlastník činnosti.
	 * @return
	 * 
	 * @param cinnost
	 */
	public boolean archiveCinnost(Activity cinnost){
		return false;
	}

	/**
	 * Smaže činnost. Toto může udělat pouze vlastník činnosti.
	 * @return
	 * 
	 * @param cinnost
	 */
	public boolean deleteCinnost(Activity cinnost){
		return false;
	}

	/**
	 * Vrátí cinnost na základě jejího ID.
	 * @return
	 * 
	 * @param id
	 */
	public Activity getCinnost(int id){
		return null;
	}

	/**
	 * Vrátí všechny cinnosti osoby.
	 * @return
	 * 
	 * @param osoba
	 */
	public List<Activity> getCinnostiOsoby(Person osoba){
		return null;
	}

	/**
	 * Označí činnost jako "odloženou". Toto může udělat pouze vlastník činnosti.
	 * @return
	 * 
	 * @param cinnost
	 */
	public boolean postponeCinnost(Activity cinnost){
		return false;
	}

	/**
	 * Označí činnost jako "zpracovanou". Toto může udělat pouze vlastník činnosti.
	 * @return
	 * 
	 * @param cinnost
	 */
	public boolean processCinnost(Activity cinnost){
		return false;
	}

	/**
	 * Uloží změněnou činnost. Toto může udělat pouze vlastník činnosti.
	 * @return
	 * 
	 * @param cinnost
	 */
	public boolean updateCinnost(Activity cinnost){
		return false;
	}

}