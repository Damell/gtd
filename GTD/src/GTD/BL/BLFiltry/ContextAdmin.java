package GTD.BL.BLFiltry;

import GTD.DL.DLInterfaces.IDAOContext;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Context;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání kontextu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class ContextAdmin {

	private IDAOContext DAOKontext;

	public ContextAdmin(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	/**
	 * Přidá osobě nový kontext.
	 * @return
	 * 
	 * @param kontext
	 * @param osoba
	 */
	public boolean addKontext(Context kontext, Person osoba){
		return false;
	}

	/**
	 * Smaže kontext.
	 * @return
	 * 
	 * @param kontext
	 */
	public boolean deleteKontext(Context kontext){
		return false;
	}

	/**
	 * Vrátí kontext podle jeho ID.
	 * @return
	 * 
	 * @param id
	 */
	public Context getKontext(int id){
		return null;
	}

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * @return
	 * 
	 * @param osoba
	 */
	public List getKontextyOsoby(Person osoba){
		return null;
	}

	/**
	 * Uloží zmenený kontext.
	 * @return
	 * 
	 * @param kontext
	 */
	public boolean updateKontext(Context kontext){
		return false;
	}

}