package GTD.DL.DLEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;


/**
 * Tato trída predstavuje spolecný nadtyp pro trídy Kontext, Složka (není rešena)
 * a Kategorie (není rešena).
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:52
 */
@MappedSuperclass
public abstract class Filter { // TODO steklsim nemela by tohle byt abstraktni trida?

	@Id
	@GeneratedValue
	private int id;
	/**
	 * Název filtru (filtry jedné osoby musí mít různá jména).
	 */
	@Column(length = 100, nullable = false)
	private String nazev;

	/**
	 * Vlastník filtru (filtry má každý uživatel své)
	 */
	@ManyToOne
	private Person vlastnik;

	public void finalize() throws Throwable {

	}

	/**
	 * Konstruktor filtru
	 */
	public Filter(){

	}

//	/**
//	 * Vrati id filtru
//	 * @return id
//	 */
//	public int getFiltrId(){
//		return 0;
//	}
//
//	/**
//	 * Vrati nazev filtru
//	 * @return nazev
//	 */
//	public String getFiltrNazev(){
//		return "";
//	}

	public int getId()
	{
		return id;
	}
	
	

	/**
	 * Nastavi id a nazev filtru
	 * 
	 * @param id
	 * @param nazev    nazev
	 */
	public void setFiltr(int id, String nazev){

	}

	public String getNazev()
	{
		return nazev;
	}

	public void setNazev(String nazev)
	{
		this.nazev = nazev;
	}

	public Person getVlastnik()
	{
		return vlastnik;
	}

	public void setVlastnik(Person vlastnik)
	{
		this.vlastnik = vlastnik;
	}
	
	

}