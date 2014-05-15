package GTD.DL.DLEntity;

import java.util.List;

/**
 * Trída predstavuje osobu, která je registrována v systému GTD.
 *
 * @author GTD team
 * @version 1.0
 */
public class Person {

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
    private List<Contact> kontakty;

    /**
     *
     */
    public Context m_Kontext;

    /**
     *
     */
    public Activity m_Cinnost;

    /**
     * Konstruktor osoby
     */
    public Person() {

    }

    /**
     * Konstruktor osoby
     *
     * @param osoba
     */
    public Person(Person osoba) {
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

    public Person(int id, String login, String jmeno, String prijmeni) {
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
    public List<Contact> getKontakty() {
        return kontakty;
    }

    /**
     * Vrátí kontext
     *
     * @return m_Kontext
     */
    public Context getM_Kontext() {
        return m_Kontext;
    }

    /**
     * Vrátí činnost
     *
     * @return m_Cinnost
     */
    public Activity getM_Cinnost() {
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
