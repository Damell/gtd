package GTD.BL.BLFiltry;
import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOKontext;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání kontextu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class SpravceKontextu {

	private IDAOKontext DAOKontext;

	public SpravceKontextu(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Přidá osobě nový kontext.
	 * 
	 * @param kontext
	 * @param osoba
	 */
	public boolean addKontext(Kontext kontext, Osoba osoba){
		return false;
	}

	/**
	 * Smaže kontext.
	 * 
	 * @param kontext
	 */
	public boolean deleteKontext(Kontext kontext){
		return false;
	}

	/**
	 * Vrátí kontext podle jeho ID.
	 * 
	 * @param id
	 */
	public Kontext getKontext(int id){
		return null;
	}

	/**
	 * Uloží zmenený kontext.
	 * 
	 * @param kontext
	 */
	public boolean updateKontext(Kontext kontext){
		return false;
	}

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * 
	 * @param osoba
	 */
	public List getKontextyOsoby(Osoba osoba){
		return null;
	}

}