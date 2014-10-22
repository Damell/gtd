package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOContext;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání kontextu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class DAOContext implements IDAOContext {



	public void finalize() throws Throwable {

	}

	/**
	 * Kontruktor kontextu
	 */
	public DAOContext(){

	}

	/**
	 * Vytvorí nový kontext zadaných vlastností a uloží ho do databáze.
	 * @return
	 * 
	 * @param kontext
	 */
	public boolean createKontext(Context kontext){
		return false;
	}

	/**
	 * Smaže kontext z databáze.
	 * @return
	 * 
	 * @param kontext
	 */
	public boolean deleteKontext(Context kontext){
		return false;
	}

	/**
	 * Vrátí všechny kontexty v systému.
	 * @return List<Kontext>
	 */
	public List getAllKontexty(){
		return null;
	}

	/**
	 * Vrátí kontext podle jeho ID.
	 * @return kontext
	 * 
	 * @param id
	 */
	public Context getKontext(int id){
		return null;
	}

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * @return List<Kontext>
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