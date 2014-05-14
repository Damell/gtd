package GTD.DL.DLEntity;

import java.util.List;

/**
 * Trída predstavuje osobu, která je registrována v systému GTD.
 *
 * @version 1.0
 */
public class Osoba {

    /**
     * unikátní id v celém systému
     */
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
     * Kontakty na uživatele - od každého typu max. 1 (v budoucích iteracích se
     * budou požívat pro různé notifikace).
     */
    private List<Kontakt> kontakty;

	/**
	 *
	 */
	public Kontext m_Kontext;

	/**
	 *
	 */
	public Cinnost m_Cinnost;

	/**
	 *
	 */
	public Osoba() {

    }

	/**
	 *
	 * @param osoba
	 */
	public Osoba(Osoba osoba) {
        this.id = osoba.id;
        this.login = osoba.login;
        this.jmeno = osoba.jmeno;
        this.prijmeni = osoba.prijmeni;
    }

	/**
	 *
	 * @return
	 */
	public int getId() {
        return id;
    }
    /*
     * Nastav osobu
     */

	/**
	 *
	 * @param id
	 * @param login
	 * @param jmeno
	 * @param prijmeni
	 */
	public Osoba(int id, String login, String jmeno, String prijmeni) {
        this.id = id;
        this.login = login;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
    }

	/**
	 *
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 *
	 * @return
	 */
	public String getJmeno() {
		return jmeno;
	}

	/**
	 *
	 * @return
	 */
	public String getPrijmeni() {
		return prijmeni;
	}

	/**
	 *
	 * @return
	 */
	public List<Kontakt> getKontakty() {
		return kontakty;
	}

	/**
	 *
	 * @return
	 */
	public Kontext getM_Kontext() {
		return m_Kontext;
	}

	/**
	 *
	 * @return
	 */
	public Cinnost getM_Cinnost() {
		return m_Cinnost;
	}

	@Override
	public String toString() {
		return jmeno + " " + prijmeni;
	}

	

}
