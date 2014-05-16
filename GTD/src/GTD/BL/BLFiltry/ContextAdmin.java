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

	private IDAOContext daoKontext;

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
	 */
	public boolean addContext(Context kontext, Person person) {
		return false;
	}

	/**
	 * Smaže kontext.
	 *
	 * @param kontext
	 */
	public boolean deleteContext(Context kontext) {
		return false;
	}

	/**
	 * Vrátí kontext podle jeho ID.
	 *
	 * @param id
	 */
	public Context getContext(int id) {
		return null;
	}

	/**
	 * Uloží zmenený kontext.
	 *
	 * @param kontext
	 */
	public boolean updateContext(Context kontext) {
		return false;
	}

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 *
	 * @param osoba
	 */
	public List getContextsOfPerson(Person osoba) {
		return null;
	}

}
