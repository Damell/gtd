package GTD.DL.DLEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;


/**
 * Tato trída predstavuje spolecný nadtyp pro trídy Cinnost, Úkol a Projekt.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:50
 */
@MappedSuperclass
public abstract class Action { 

	@Id
	@GeneratedValue
	private int id;
	/**
	 * Název aktivity
	 */
	@Column(length = 100, nullable = false)
	private String nazev;
	/**
	 * Popis aktivity
	 */
	@Column(length = 1000, nullable = true)
	private String popis;
	
	
	/**
	 * Vlastník aktivity - má právo ji upravovat (změnit název/popis, smazat, v
	 * případě úkolu a projektu dokončit a delegovat).
	 */
	@ManyToOne
	private Person vlastnik;



	public void finalize() throws Throwable {

	}
	
	/**
	 * Konstruktor aktivity
	 */
	public Action(){

	}

	/**
	 * Konstruktor aktivity
	 * 
	 * @param nazev
	 * @param popis
	 * @param vlastnik 
	 */
	public Action(String nazev, String popis, Person vlastnik){
		this.nazev = nazev;
		this.popis = popis;
		this.vlastnik = vlastnik;
	}

//	/**
//	 * Konstruktor aktivity
//	 * 
//	 * @param id
//	 * @param nazev
//	 * @param popis
//	 * @param stav
//	 * @param stavPopis
//	 * @param vlastnik_id    vlastnik_id
//	 */
//	public Action(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id){
//
//	}

	/**
	 * Vrátí id aktivity
	 * @return id
	 */
	public int getId(){
		return id;
	}

	/**
	 * Vrátí název aktivity
	 * @return nazev
	 */
	public String getNazev(){
		return nazev;
	}

	public void setNazev(String nazev)
	{
		this.nazev = nazev;
	}
	
	

	/**
	 * Vrátí popis aktivity
	 * @return popis
	 */
	public String getPopis(){
		return popis;
	}

	public void setPopis(String popis)
	{
		this.popis = popis;
	}
	
	

//	/**
//	 * Vrátí id stavu aktivity
//	 * @return id
//	 */
//	public int getStav(){
//		return stav;
//	}


	/**
	 * Vrátí id vlastníka aktivity
	 * @return vlastnik_id
	 */
	public Person getVlastnik() {
		return vlastnik;
	}

//	/**
//	 * Nastav aktivitu
//	 * 
//	 * @param id
//	 * @param nazev
//	 * @param popis
//	 * @param stav
//	 * @param stavPopis
//	 * @param vlastnik_id    vlastnik_id
//	 */
//	public void setAktivita(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id){
//
//	}
//
//	/**
//	 * Nastavi id aktivity
//	 * 
//	 * @param id    id
//	 */
//	public void setId(int id){
//
//	}
//
//	/**
//	 * Nastav stav aktivity
//	 * 
//	 * @param stav    stav
//	 */
//	public void setStav(int stav){
//
//	}

	/**
	 * Nastav vlastníka aktivity
	 * 
	 * @param vlastnik
	 */
	public void setVlastnik(Person vlastnik){
		this.vlastnik = vlastnik;
	}

	/**
	 * Vrátí název a popis aktivity v jendom řetězci
	 * @return popis
	 */
	@Override
	public String toString(){
		return "Aktivita: id=" + id + ", nazev=" + nazev;
	}

	@Override
	public int hashCode()
	{
		int hash = 5;
		hash = 89 * hash + this.id;
		hash = 89 * hash + Objects.hashCode(this.nazev);
		hash = 89 * hash + Objects.hashCode(this.popis);
		hash = 89 * hash + Objects.hashCode(this.vlastnik);
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Action other = (Action) obj;
		if (this.id != other.id) {
			return false;
		}
		if (!Objects.equals(this.nazev, other.nazev)) {
			return false;
		}
		if (!Objects.equals(this.popis, other.popis)) {
			return false;
		}
		if (!Objects.equals(this.vlastnik, other.vlastnik)) {
			return false;
		}
		return true;
	}
	
	

}