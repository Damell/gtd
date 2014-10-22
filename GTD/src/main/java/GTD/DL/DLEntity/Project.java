package GTD.DL.DLEntity;

import java.util.List;


/**
 * Třída predstavuje projekt - množinu souvisejících úkolu. Project muže krome
 * úkolu obsahovat i další projekty (pocet úrovní není omezen). Vlastník projektu
 * může delegovat jeho úkoly a podprojekty (v 1.úrovni).
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
public class Project extends Action {

	/**
	 * Podprojekty projektu.
	 */
	private List<Project> projekty;
	/**
	 * Rodič - nadřazený projekt.
	 */
	private Project rodic;
	/**
	 * Skupina osob pracujících na projektu - slouží pro delegování aktivit v rámci
	 * projektu.
	 */
	private List<Person> skupina;
	/**
	 * Úkoly projektu.
	 */
	private List<Task> ukoly;


	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * Konstruktor projektu
	 * 
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param vlastnik_id
	 * @param skupina
	 * @param rodic    rodic
	 */
	public Project(String nazev, String popis, int stav, int vlastnik_id, List<Person> skupina, Project rodic){

	}

	/**
	 * Konstruktor projektu
	 * 
	 * @param id
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param stavPopis
	 * @param vlastnik_id    vlastnik_id
	 */
	public Project(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id){

	}

	/**
	 * Konstruktor projektu
	 */
	public Project(){

	}

	/**
	 * Pridej osobu do projektu
	 * 
	 * @param osoba    osoba
	 */
	public void addOsoba(Person osoba){

	}

	/**
	 * Pridej podprojekt do projektu
	 * 
	 * @param projekt    projekt
	 */
	public void addProjekt(Project projekt){

	}

	/**
	 * Pridej ukol do projektu
	 * 
	 * @param ukol    ukol
	 */
	public void addUkol(Task ukol){

	}

	/**
	 * Vrátí podprojekty
	 * @return List<Projekt>
	 */
	public List<Project> getProjekty(){
		return null;
	}

	/**
	 * Vrati id rodice projektu
	 * @return id
	 */
	public Project getRodic(){
		return null;
	}

	/**
	 * Vrati skupinu v projektu
	 * @return List<Osoba>
	 */
	public List<Person> getSkupina(){
		return null;
	}

	/**
	 * Vratí úkoly v projekty
	 * @returnList<Ukol>
	 */
	public List<Task> getUkoly(){
		return null;
	}

	/**
	 * Nastav rodice projektu
	 * 
	 * @param id
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param stavPopis
	 * @param vlastnik_id    vlastnik_id
	 */
	public void setProjectrodic(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id){

	}

	/**
	 * Vrátí název a popis projektu
	 * @return nazev_popis
	 */
	@Override
	public String toString(){
		return "";
	}

}