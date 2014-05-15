package GTD.BL.BLFiltry;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOContext;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání kontextu z databáze.
 *
 * @author GTD team
 * @version 1.0
 */
public class ContextAdmin {

	private IDAOContext DAOKontext;

	/**
	 *
	 */
	public ContextAdmin() {

	}

	/**
	 * Přidá osobě nový kontext.
	 *
	 * @param kontext
	 * @param person
	 * @return
	 */
	public boolean addContext(Context kontext, Person person) {
		return false;
	}

	/**
	 * Smaže kontext.
	 *
	 * @param kontext
	 * @return
	 */
	public boolean deleteContext(Context kontext) {
		return false;
	}

	/**
	 * Vrátí kontext podle jeho ID.
	 *
	 * @param id
	 * @return
	 */
	public Context getContext(int id) {
		return null;
	}

	/**
	 * Uloží zmenený kontext.
	 *
	 * @param kontext
	 * @return
	 */
	public boolean updateContext(Context kontext) {
		return false;
	}

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 *
	 * @param osoba
	 * @return
	 */
	public List getContextsOfPerson(Person osoba) {
		return null;
	}

}
