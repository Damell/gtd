package GTD.DL.DLEntity;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * Třída predstavuje projekt - množinu souvisejících úkolu. Project muže krome
 * úkolu obsahovat i další projekty (pocet úrovní není omezen). Vlastník projektu
 * může delegovat jeho úkoly a podprojekty (v 1.úrovni).
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
@Entity
public class Project extends Action {

	/**
	 * Podprojekty projektu.
	 */
	@OneToMany(mappedBy = "rodic")
	private List<Project> projekty;
	/**
	 * Rodič - nadřazený projekt.
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Project rodic;
	/**
	 * Skupina osob pracujících na projektu - slouží pro delegování aktivit v rámci
	 * projektu.
	 */
	@ManyToMany
	private List<Person> skupina;
	/**
	 * Úkoly projektu.
	 */
	@OneToMany(mappedBy = "projekt")
	private List<Task> ukoly;
	
	/**
	 * stav projektu
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private ProjectState stav;


	public void finalize() throws Throwable {
		super.finalize();
	}
	
	/**
	 * Konstruktor projektu
	 */
	public Project(){

	}
	
	public Project(String nazev, String popis, Person vlastnik, Project rodic, ProjectState stav)
	{
		super(nazev, popis, vlastnik);
		this.rodic = rodic;
		this.stav = stav;
	}
	

	/**
	 * Pridej osobu do projektu
	 * 
	 * @param osoba    osoba
	 */
	public void addOsoba(Person osoba){
		// TODO steklsim nemela by byt this.skupina spis Set?
		skupina.add(osoba);
	}
	
	public boolean removeOsoba(Person osoba)
	{
		return skupina.remove(osoba);
	}


	/**
	 * Pridej podprojekt do projektu
	 * 
	 * @param projekt    projekt
	 */
	public void addProjekt(Project projekt){
		// TODO steklsim nemelo by byt this.projekty spis Set?
		projekty.add(projekt);
	}
	
	public boolean removeProjekt(Project projekt)
	{
		return projekty.remove(projekt);
	}


	/**
	 * Pridej ukol do projektu
	 * 
	 * @param ukol    ukol
	 */
	public void addUkol(Task ukol){
		// TODO steklsim nemelo by byt this.ukoly spis Set?
		ukoly.add(ukol);
	}

	public boolean removeUkol(Task ukol)
	{
		// TODO steklsim nemelo by byt this.ukoly spis Set?
		return ukoly.remove(ukol);
	}
	
	/**
	 * Vrátí podprojekty
	 * @return List<Projekt>
	 */
	public List<Project> getProjekty(){
		return projekty;
	}

	public void setProjekty(List<Project> projekty)
	{
		this.projekty = projekty;
	}

	
	
	/**
	 * Vrati id rodice projektu
	 * @return id
	 */
	public Project getRodic(){
		return rodic;
	}

	public void setRodic(Project rodic)
	{
		this.rodic = rodic;
	}
	
	

	/**
	 * Vrati skupinu v projektu
	 * @return List<Person>
	 */
	public List<Person> getSkupina(){
		return skupina;
	}

	public void setSkupina(List<Person> skupina)
	{
		this.skupina = skupina;
	}
	
	

	/**
	 * Vratí úkoly v projekty
	 * @returnList<Ukol>
	 */
	public List<Task> getUkoly(){
		return null;
	}

	public void setUkoly(List<Task> ukoly)
	{
		this.ukoly = ukoly;
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

	public ProjectState getStav()
	{
		return stav;
	}

	public void setStav(ProjectState stav)
	{
		this.stav = stav;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.skupina);
		hash = 37 * hash + Objects.hashCode(this.stav);
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!super.equals(obj)) return false;
		
		final Project other = (Project) obj;
		if (!Objects.equals(this.projekty, other.projekty)) {
			return false;
		}
		if (!Objects.equals(this.rodic, other.rodic)) {
			return false;
		}
		if (!Objects.equals(this.skupina, other.skupina)) {
			return false;
		}
		if (!Objects.equals(this.ukoly, other.ukoly)) {
			return false;
		}
		if (!Objects.equals(this.stav, other.stav)) {
			return false;
		}
		return true;
	}
	
	

}