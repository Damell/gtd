package GTD.DL.DLEntity;

import java.util.List;

/**
 * Trída predstavuje osobu, která je registrována v systému GTD.
 *
 * @author andel
 * @version 1.0
 * @created 26-4-2014 14:51:23
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
    public Kontext m_Kontext;
    public Cinnost m_Cinnost;

    public Osoba() {

    }

    public Osoba(Osoba osoba) {
        this.id = osoba.id;
        this.login = osoba.login;
        this.jmeno = osoba.jmeno;
        this.prijmeni = osoba.prijmeni;
    }

    public int getId() {
        return id;
    }
    /*
     * Nastav osobu
     */

    public Osoba(int id, String login, String jmeno, String prijmeni) {
        this.id = id;
        this.login = login;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
    }

	public String getLogin() {
		return login;
	}

	public String getJmeno() {
		return jmeno;
	}

	public String getPrijmeni() {
		return prijmeni;
	}

	public List<Kontakt> getKontakty() {
		return kontakty;
	}

	public Kontext getM_Kontext() {
		return m_Kontext;
	}

	public Cinnost getM_Cinnost() {
		return m_Cinnost;
	}

	@Override
	public String toString() {
		return jmeno + " " + prijmeni;
	}

	

}
