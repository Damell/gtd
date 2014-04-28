package GTD.DL.DLDAO;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import GTD.DL.DLInterfaces.IDAOProjekt;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání projektu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:21
 */
public class DAOProjekt implements IDAOProjekt {

	public DAOProjekt(){

	}

	/**
	 * Vytvorí nový projekt zadaných vlastností a uloží ho do databáze.
	 * 
	 * @param projekt
	 */
	public boolean createProjekt(Projekt projekt){
		return false;
	}

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
	 * a podprojekty.
	 * 
	 * @param projekt
	 */
	public boolean deleteProjekt(Projekt projekt){
		return false;
	}

	/**
	 * Vrátí všechny projekty v systému.
	 */
	public List<Projekt> getAllProjekty(){
		return null;
	}

	/**
	 * Vrátí projekt podle jeho ID.
	 * 
	 * @param id
	 */
	public Projekt getProjekt(int id){
		return null;
	}

	/**
	 * Uloží zmenený projekt.
	 * 
	 * @param projekt
	 */
	public boolean updateProjekt(Projekt projekt){
		return false;
	}

	/**
	 * Vrátí všechny projekty patřící zadané osobe.
	 * 
	 * @param osoba
	 */
	public Projekt getProjektyOsoby(Osoba osoba){
		return null;
	}

}