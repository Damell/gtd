package GTD.DL.DLDAO;
import GTD.DL.DLEntity.Kontext;
import GTD.DL.DLEntity.Ukol;
import GTD.DL.DLInterfaces.IDAOUkol;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání úkolu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:21
 */
public class DAOUkol implements IDAOUkol {

	public DAOUkol(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Vytvorí nový úkol zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param ukol
	 */
	public boolean createUkol(Ukol ukol){
		return false;
	}

	/**
	 * Smaže úkol z databáze (resp. označí jako smazaný).
	 * 
	 * @param ukol
	 */
	public boolean deleteUkol(Ukol ukol){
		return false;
	}

	/**
	 * Vrátí všechny úkoly v systému.
	 */
	public List getAllUkoly(){
		return null;
	}

	/**
	 * Vrátí úkol podle jeho ID.
	 * 
	 * @param id
	 */
	public Ukol getUkol(int id){
		return null;
	}

	/**
	 * Uloží zmenený úkol.
	 * 
	 * @param ukol
	 */
	public boolean updateUkol(Ukol ukol){
		return false;
	}

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * 
	 * @param kontext
	 */
	public List<Ukol> getUkolyKontextu(Kontext kontext){
		return null;
	}

}