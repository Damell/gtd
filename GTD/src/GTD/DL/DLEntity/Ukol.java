package GTD.DL.DLEntity;

import java.util.Date;

/**
 * Třída predstavující úkol - realizovatelnou akci.
 *
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
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

    public Ukol() {
    }

    /*
     * Inicializace ukolu
     */
    public Ukol(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id, int id_projekt) {
        super(id, nazev, popis, stav, stavPopis, vlastnik_id);
        this.id_projekt = id_projekt;
    }

    /*
     * Nastav uzivatelsky kontext ukolu
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

    public void setInterval(Date from, Date to) {
        if (kalendar == null) {
            kalendar = new Interval();
        }
        kalendar.setInterval(from, to);
    }

    public void setProjekt(int id_projekt) {
        this.id_projekt = id_projekt;
    }

    public void setProjekt(int id_projekt, String nazev, String popis) {
        this.id_projekt = id_projekt;
        this.projekt_nazev = nazev;
        this.projekt_popis = popis;
    }

    public Interval getKalendar() {
        return kalendar;
    }

    public Kontext getKontext() {
        return kontext;
    }

    public int getProjekt() {
        return id_projekt;
    }
    public String getProjektNazev() {
        return projekt_nazev;
    }
    public String getProjektPopis() {
        return projekt_popis;
    }
}
