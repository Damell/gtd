package GTD.DL.DLEntity;
import Analýza.Doménový model.Osoba;

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
	private string login;
	/**
	 * Křestní jméno uživatele.
	 */
	private string jmeno;
	/**
	 * Příjmení uživatele.
	 */
	private string prijmeni;
	/**
	 * Heslo - používá se spolu s loginem pro přihlášení do systému
	 */
	private string heslo;
	/**
	 * Kontakty na uživatele - od každého typu max. 1 (v budoucích iteracích se budou
	 * požívat pro různé notifikace).
	 */
	private List<Kontakt> kontakty;
	public Kontext m_Kontext;
	public Cinnost m_Cinnost;

	public Osoba(){

	}

	public void finalize() throws Throwable {

	}

}