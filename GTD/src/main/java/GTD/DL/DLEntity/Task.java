package GTD.DL.DLEntity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Třída predstavující úkol - realizovatelnou akci.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
@Entity
@Table(name = "task")
public class Task extends Action {

	/**
	 * Projekt úkolu.
	 */
	@ManyToOne
	private Project projekt;
	/**
	 * Tvůrce úkolu (může se lišit od vlastníka - což je přiřazená osoba)
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Person tvurce;
	/**
	 * Záznam o úkolu v kalendáři.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private Interval kalendar;
	/**
	 * Context úkolu.
	 */
	@ManyToOne
	private Context kontext;

	/**
	 * Stav úkolu
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private TaskState stav;


	public void finalize() throws Throwable {
		super.finalize();
	}

	public Task(String nazev, String popis, Person vlastnik, Project projekt, Person tvurce, TaskState stav)
	{
		super(nazev, popis, vlastnik);
		this.projekt = projekt;
		this.tvurce = tvurce;
		this.stav = stav;
	}



	/**
	 * Konstruktor ukolu
	 */
	public Task(){

	}

	/**
	 * Vrati kalendat ukolu
	 * @return kalendar
	 */
	public Interval getKalendar(){
		return kalendar;
	}

	/**
	 * Vrati kontext ukolu
	 * @return kontext
	 */
	public Context getKontext(){
		return kontext;
	}

	/**
	 * Vrati projekt úkolu
	 * @return projekt
	 */
	public Project getProjekt(){
		return projekt;
	}

	/**
	 * Vrati tvurce úkolu
	 * @return tvurce
	 */
	public Person getTvurce(){
		return tvurce;
	}

	public void setProjekt(Project projekt)
	{
		this.projekt = projekt;
	}

	public void setTvurce(Person tvurce)
	{
		this.tvurce = tvurce;
	}

	public void setKalendar(Interval kalendar)
	{
		this.kalendar = kalendar;
	}

	public void setKontext(Context kontext)
	{
		// TODO steklsim vyjimka pokud vlastnik ukolu != vlastnik kontextu 
		this.kontext = kontext;
	}

	public TaskState getStav()
	{
		return stav;
	}

	public void setStav(TaskState stav)
	{
		this.stav = stav;
	}

	@Override
	public String toString()
	{
		return "Ukol: id=" + this.getId() + ", nazev=" + this.getNazev();
	}

	@Override
	public int hashCode()
	{
		int hash = 5;
		hash = 41 * hash + Objects.hashCode(this.kalendar);
		hash = 41 * hash + Objects.hashCode(this.kontext);
		hash = 41 * hash + Objects.hashCode(this.stav);
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!super.equals(obj)) return false;
		
		final Task other = (Task) obj;
		if (!Objects.equals(this.projekt, other.projekt)) {
			return false;
		}
		if (!Objects.equals(this.tvurce, other.tvurce)) {
			return false;
		}
		if (!Objects.equals(this.kalendar, other.kalendar)) {
			return false;
		}
		if (!Objects.equals(this.kontext, other.kontext)) {
			return false;
		}
		if (!Objects.equals(this.stav, other.stav)) {
			return false;
		}
		return true;
	}

	

}