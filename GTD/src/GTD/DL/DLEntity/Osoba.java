package GTD.DL.DLEntity;

import java.util.List;

/**
 * Trída predstavuje osobu, která je registrována v systému GTD.
 * @author andel
 * @version 1.0
 * @created 26-4-2014 14:51:23
 */
public class Osoba {

	private int id;
	/**
	 * uživatelské jméno, unikátní v celém systému
	 */
	private String login;
	/**
	 * Křestní jméno uživatele.
	 */
	private String jmeno;
	/**
	 * Příjmení uživatele.
	 */
	private String prijmeni;
	/**
	 * Heslo - používá se spolu s loginem pro přihlášení do systému
	 */
	private String heslo;
	/**
	 * Kontakty na uživatele - od každého typu max. 1 (v budoucích iteracích se budou
	 * požívat pro různé notifikace).
	 */
	private List<Kontakt> kontakty;
	public Kontext m_Kontext;
	public Cinnost m_Cinnost;

	public Osoba(){

	}

}