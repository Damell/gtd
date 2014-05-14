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
     * Konstruktor osoby
     */
    public Osoba() {

    }

    /**
     * Konstruktor osoby
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
     * Vrátí id osoby
     *
     * @return id
     */
    public int getId() {
        return id;
    }
    /*
     * Nastav osobu
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
     * Vrátí login osoby
     *
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Vrátí jméno osoby
     *
     * @return jmeno
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Vrátí příjmení osoby
     *
     * @return prijmeni
     */
    public String getPrijmeni() {
        return prijmeni;
    }

    /**
     * Vrátí kontakty ososby
     *
     * @return List<Kontakt>
     */
    public List<Kontakt> getKontakty() {
        return kontakty;
    }

    /**
     * Vrátí kontext
     *
     * @return m_Kontext
     */
    public Kontext getM_Kontext() {
        return m_Kontext;
    }

    /**
     * Vrátí činnost
     *
     * @return m_Cinnost
     */
    public Cinnost getM_Cinnost() {
        return m_Cinnost;
    }

    /**
     * Vrátí jméno a příjmení
     *
     * @return jmeno_prijmeni
     */
    @Override
    public String toString() {
        return jmeno + " " + prijmeni;
    }

}
