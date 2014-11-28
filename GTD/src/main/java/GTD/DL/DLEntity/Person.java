package GTD.DL.DLEntity;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Trída predstavuje osobu, která je registrována v systému GTD.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:54
 */
@Entity
@Table(name = "person")
public class Person {

	/**
	 * unikátní id v celém systému
	 */
	@Id
	@GeneratedValue
	private int id;
	/**
	 * Křestní jméno uživatele.
	 */
	@Column(length = 20, nullable = false)
	private String jmeno;
	/**
	 * Kontakty na uživatele - od každého typu max. 1 (v budoucích iteracích se budou
	 * požívat pro různé notifikace).
	 */
	@OneToMany(mappedBy = "osoba", cascade = CascadeType.ALL)
	private List<Contact> kontakty;
	/**
	 * uživatelské jméno, unikátní v celém systému
	 */
	@Column(length = 20, nullable = false, unique = true)
	private String login;
	
	/**
	 * Příjmení uživatele.
	 */
	@Column(length = 20, nullable = false)
	private String prijmeni;

	/**
	 * stav osoby
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private PersonState stav;

	public void finalize() throws Throwable {

	}
	
	/**
	 * Konstruktor osoby
	 */
	public Person(){

	}

//	/**
//	 * Nastav osobu
//	 * 
//	 * @param id
//	 * @param login
//	 * @param jmeno
//	 * @param prijmeni    prijmeni
//	 */
//	public Person(int id, String login, String jmeno, String prijmeni){
//
//	}

	public Person(String jmeno, String prijmeni, String login, PersonState stav)
	{
		this.jmeno = jmeno;
		this.login = login;
		this.prijmeni = prijmeni;
		this.stav = stav;
	}
	
	

//	/**
//	 * kopirujici konstruktor
//	 * 
//	 * @param osoba    osoba
//	 */
//	public Person(Person osoba){
//		this(osoba.getJmeno(), osoba.getPrijmeni(), osoba.getLogin(), osoba.getStav());
//		kontakty = osoba.getKontakty();
//	}


	/**
	 * Vrátí id osoby
	 * @return id
	 */
	public int getId(){
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * Vrátí jméno osoby
	 * @return jmeno
	 */
	public String getJmeno(){
		return jmeno;
	}

	/**
	 * Vrátí kontakty ososby
	 * @return List<Kontakt>
	 */
	public List<Contact> getKontakty(){
		return kontakty;
	}

	/**
	 * Vrátí login osoby
	 * @return login
	 */
	public String getLogin(){
		return login;
	}


	/**
	 * Vrátí příjmení osoby
	 * @return prijmeni
	 */
	public String getPrijmeni(){
		return prijmeni;
	}

	/**
	 * Vrátí jméno a příjmení
	 * @return jmeno_prijmeni
	 */
	@Override
	public String toString(){
		return "Osoba: id=" + id + ", login=" + login + ", jmeno=" + jmeno + " " + prijmeni;
	}

	public PersonState getStav()
	{
		return stav;
	}

	public void setJmeno(String jmeno)
	{
		this.jmeno = jmeno;
	}

	public void setKontakty(List<Contact> kontakty)
	{
		this.kontakty = kontakty;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public void setPrijmeni(String prijmeni)
	{
		this.prijmeni = prijmeni;
	}

	public void setStav(PersonState stav)
	{
		this.stav = stav;
	}

	public void addKontakt(Contact kontakt)
	{
		// TODO steklsim Set misto Listu?
		kontakty.add(kontakt);
	}

	public boolean removeKontakt(Contact kontakt)
	{
		return kontakty.remove(kontakt);
	}
	
	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 37 * hash + this.id;
		hash = 37 * hash + Objects.hashCode(this.jmeno);
		hash = 37 * hash + Objects.hashCode(this.kontakty);
		hash = 37 * hash + Objects.hashCode(this.login);
		hash = 37 * hash + Objects.hashCode(this.prijmeni);
		hash = 37 * hash + Objects.hashCode(this.stav);
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
		final Person other = (Person) obj;
		if (this.id != other.id) {
			return false;
		}
		if (!Objects.equals(this.jmeno, other.jmeno)) {
			return false;
		}
		if (!Objects.equals(this.kontakty, other.kontakty)) {
			return false;
		}
		if (!Objects.equals(this.login, other.login)) {
			return false;
		}
		if (!Objects.equals(this.prijmeni, other.prijmeni)) {
			return false;
		}
		if (!Objects.equals(this.stav, other.stav)) {
			return false;
		}
		return true;
	}
	
	
	
}