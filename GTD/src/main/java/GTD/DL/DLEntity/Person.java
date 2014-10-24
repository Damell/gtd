package GTD.DL.DLEntity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


/**
 * Trída predstavuje osobu, která je registrována v systému GTD.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:54
 */
@Entity
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
	@Column(length = 20, nullable = false)
	private String login;
	
	@Transient
	public Activity m_Cinnost;	// <---- TODO steklsim what are these?
	@Transient					//    |
	public Context m_Kontext;	// <--
	/**
	 * Příjmení uživatele.
	 */
	@Column(length = 20, nullable = false)
	private String prijmeni;

	// TODO steklsim v tabulce "persons" z minuleho semestru je fk id_types - co to je za vazbu? 

	public void finalize() throws Throwable {

	}

	/**
	 * Nastav osobu
	 * 
	 * @param id
	 * @param login
	 * @param jmeno
	 * @param prijmeni    prijmeni
	 */
	public Person(int id, String login, String jmeno, String prijmeni){

	}

	/**
	 * Konstruktor osoby
	 * 
	 * @param osoba    osoba
	 */
	public Person(Person osoba){

	}

	/**
	 * Konstruktor osoby
	 */
	public Person(){

	}

	/**
	 * Vrátí id osoby
	 * @return id
	 */
	public int getId(){
		return 0;
	}

	/**
	 * Vrátí jméno osoby
	 * @return jmeno
	 */
	public String getJmeno(){
		return "";
	}

	/**
	 * Vrátí kontakty ososby
	 * @return List<Kontakt>
	 */
	public List<Contact> getKontakty(){
		return null;
	}

	/**
	 * Vrátí login osoby
	 * @return login
	 */
	public String getLogin(){
		return "";
	}

	/**
	 * Vrátí činnost
	 * @return m_Cinnost
	 */
	public Activity getM_Cinnost(){
		return null;
	}

	/**
	 * Vrátí kontext
	 * @return m_Kontext
	 */
	public Context getM_Kontext(){
		return null;
	}

	/**
	 * Vrátí příjmení osoby
	 * @return prijmeni
	 */
	public String getPrijmeni(){
		return "";
	}

	/**
	 * Vrátí jméno a příjmení
	 * @return jmeno_prijmeni
	 */
	@Override
	public String toString(){
		return "";
	}

}