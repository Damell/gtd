package GTD.DL.DLEntity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 * Třída predstavující úkol - realizovatelnou akci.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
@Entity
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
	private TaskState stav;


	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * Inicializace ukolu
	 * 
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param tvurce_id
	 * @param vlastnik_id
	 * @param id_projekt    id_projekt
	 */
	public Task(String nazev, String popis, int stav, int tvurce_id, int vlastnik_id, int id_projekt){

	}

	/**
	 * Inicializace ukolu
	 * 
	 * @param id
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param stavPopis
	 * @param vlastnik_id
	 * @param id_projekt    id_projekt
	 */
	public Task(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id, int id_projekt){

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

	

}