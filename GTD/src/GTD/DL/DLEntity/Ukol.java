package GTD.DL.DLEntity;

import java.util.Date;

/**
 * Třída predstavující úkol - realizovatelnou akci.
 *
 * @version 1.0
 */
public class Ukol extends Aktivita {

    /**
     * Záznam o úkolu v kalendáři.
     */
    private Interval kalendar;
    /**
     * Kontext úkolu.
     */
    private Kontext kontext;
    /**
     * Projekt úkolu.
     */
    private int id_projekt;
    private String projekt_nazev;
    private String projekt_popis;
	/**
	 * Tvůrce úkolu (může se lišit od vlastníka - což je přiřazená osoba)
	 */
    private int id_tvurce;

	/**
	 *
	 */
	public Ukol() {
    }

    /*
     * Inicializace ukolu
     */

	/**
	 *
	 * @param id
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param stavPopis
	 * @param vlastnik_id
	 * @param id_projekt
	 */
	
    public Ukol(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id, int id_projekt) {
        super(id, nazev, popis, stav, stavPopis, vlastnik_id);
        this.id_projekt = id_projekt;
    }

	/**
	 *
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param tvurce_id
	 * @param vlastnik_id
	 * @param id_projekt
	 */
	public Ukol(String nazev, String popis, int stav, int tvurce_id, int vlastnik_id, int id_projekt) {
		super(nazev, popis, stav, vlastnik_id);
		this.id_projekt = id_projekt;
		this.id_tvurce = tvurce_id;
	}

    /*
     * Nastav uzivatelsky kontext ukolu
     */

	/**
	 *
	 * @param id
	 * @param nazev
	 */
	
    public void setKontext(int id, String nazev) {
        if (kontext == null) {
            kontext = new Kontext();
        }
        kontext.setKontext(id, nazev);
    }
    /*
     * Nastav interval ukolu
     */

	/**
	 *
	 * @param from
	 * @param to
	 */
	public void setInterval(Date from, Date to) {
        if (kalendar == null) {
            kalendar = new Interval();
        }
        kalendar.setInterval(from, to);
    }

	/**
	 *
	 * @param id_projekt
	 */
	public void setProjekt(int id_projekt) {
        this.id_projekt = id_projekt;
    }

	/**
	 *
	 * @param id_projekt
	 * @param nazev
	 * @param popis
	 */
	public void setProjekt(int id_projekt, String nazev, String popis) {
        this.id_projekt = id_projekt;
        this.projekt_nazev = nazev;
        this.projekt_popis = popis;
    }

	/**
	 *
	 * @return
	 */
	public Interval getKalendar() {
        return kalendar;
    }

	/**
	 *
	 * @return
	 */
	public Kontext getKontext() {
        return kontext;
    }

	/**
	 *
	 * @return
	 */
	public int getProjekt() {
        return id_projekt;
    }

	/**
	 *
	 * @return
	 */
	public String getProjektNazev() {
        return projekt_nazev;
    }

	/**
	 *
	 * @return
	 */
	public String getProjektPopis() {
        return projekt_popis;
    }

	/**
	 *
	 * @return
	 */
	public int getTvurce() {
        return id_tvurce;
    }
}
