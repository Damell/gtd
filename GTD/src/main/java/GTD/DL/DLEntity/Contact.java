package GTD.DL.DLEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(nullable = false)
	private ContactType typ;
	
	/**
	 * vlastník kontaktu
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Person osoba;
	
	


	public void finalize() throws Throwable {

	}

	/**
	 * Konstruktor kontaktu
	 */
	public Contact(){

	}

	public Contact(String kontakt, ContactType typ, Person osoba)
	{
		this.kontakt = kontakt;
		this.typ = typ;
		this.osoba = osoba;
	}

	
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
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

//	@Override
//	public boolean equals(Object obj)
//	{
//		if (obj == null) return false;
//		if (this.getClass() != obj.getClass()) return false;
//		if (obj == this) return true;
//		
//		Contact other = (Contact)obj;
//		return (
//				this.id == other.getId()
//				&& this.kontakt.equals(other.getKontakt())
//				&& this.typ.equals(other.getTyp())
//		);
//	}

	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 59 * hash + this.id;
		hash = 59 * hash + Objects.hashCode(this.kontakt);
		hash = 59 * hash + Objects.hashCode(this.typ);
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
		final Contact other = (Contact) obj;
		if (this.id != other.id) {
			return false;
		}
		if (!Objects.equals(this.kontakt, other.kontakt)) {
			return false;
		}
		if (!Objects.equals(this.typ, other.typ)) {
			return false;
		}
		if (!Objects.equals(this.osoba, other.osoba)) {
			return false;
		}
		return true;
	}
	
	


}