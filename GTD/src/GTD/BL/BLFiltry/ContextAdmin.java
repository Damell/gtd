package GTD.BL.BLFiltry;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOContext;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání kontextu z databáze.
 * @version 1.0
 */
public class ContextAdmin {

	private IDAOContext DAOKontext;

	/**
	 *
	 */
	public ContextAdmin(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Přidá osobě nový kontext.
	 * 
	 * @param kontext
	 * @param osoba
	 * @return 
	 */
	public boolean addKontext(Context kontext, Person osoba){
		return false;
	}

	/**
	 * Smaže kontext.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean deleteKontext(Context kontext){
		return false;
	}

	/**
	 * Vrátí kontext podle jeho ID.
	 * 
	 * @param id
	 * @return 
	 */
	public Context getKontext(int id){
		return null;
	}

	/**
	 * Uloží zmenený kontext.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean updateKontext(Context kontext){
		return false;
	}

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * 
	 * @param osoba
	 * @return 
	 */
	public List getKontextyOsoby(Person osoba){
		return null;
	}

}