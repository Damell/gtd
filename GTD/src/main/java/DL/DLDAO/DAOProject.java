package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOProject;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání projektu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class DAOProject implements IDAOProject {



	public void finalize() throws Throwable {

	}

	/**
	 * Konstruktor projektu
	 */
	public DAOProject(){

	}

	/**
	 * Vytvorí nový projekt zadaných vlastností a uloží ho do databáze.
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean createProjekt(Project projekt){
		return false;
	}

	/**
	 * Smaže projekt (resp. označí jako smazaný) z databáze spolu se všemi jeho úkoly
	 * a podprojekty.
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean deleteProjekt(Project projekt){
		return false;
	}

	/**
	 * Vrátí všechny projekty v systému.
	 * @return List<Projekt>
	 */
	public List<Project> getAllProjekty(){
		return null;
	}

	/**
	 * Vrátí projekt podle jeho ID.
	 * @return projekt
	 * 
	 * @param id
	 */
	public Project getProjekt(int id){
		return null;
	}

	/**
	 * Vrátí všechny projekty patřící zadané osobe.
	 * @return List<Projekt>
	 * 
	 * @param osoba
	 */
	public List<Project> getProjektyOsoby(Person osoba){
		return null;
	}

	/**
	 * Uloží zmenený projekt.
	 * @return
	 * 
	 * @param projekt
	 */
	public boolean updateProjekt(Project projekt){
		return false;
	}

}