package GTD.DL.DLEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 * Trída predstavuje kontakt na osobu evidovanou v systému GTD.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:50
 */
@Entity
public class Contact {

	@Id
	@GeneratedValue
	private int id;
	/**
	 * Contact pro zasílání upozornění
	 */
	@Column(length = 100, nullable = false)
	private String kontakt;
	/**
	 * typ spojení (email, telefon, ...)
	 */
	@ManyToOne
	private ContactType typ;
	
	/**
	 * vlastník kontaktu
	 */
	@ManyToOne
	private Person osoba;
	
	


	public void finalize() throws Throwable {

	}

	/**
	 * Konstruktor kontaktu
	 */
	public Contact(){

	}

	
	public int getId()
	{
		return id;
	}
	
	public String getKontakt()
	{
		return kontakt;
	}

	public void setKontakt(String kontakt)
	{
		this.kontakt = kontakt;
	}

	public ContactType getTyp()
	{
		return typ;
	}

	public void setTyp(ContactType typ)
	{
		this.typ = typ;
	}

	public Person getOsoba()
	{
		return osoba;
	}

	public void setOsoba(Person osoba)
	{
		this.osoba = osoba;
	}
	
	

}