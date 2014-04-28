package GTD.DL.DLDAO;
import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLInterfaces.IDAOKontext;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání kontextu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:20
 */
public class DAOKontext implements IDAOKontext {

	public DAOKontext(){

	}

	/**
	 * Vytvorí nový kontext zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param kontext
	 */
	public boolean createKontext(Kontext kontext){
		return false;
	}

	/**
	 * Smaže kontext z databáze.
	 * 
	 * @param kontext
	 */
	public boolean deleteKontext(Kontext kontext){
		return false;
	}

	/**
	 * Vrátí všechny kontexty v systému.
	 */
	public List getAllKontexty(){
		return null;
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