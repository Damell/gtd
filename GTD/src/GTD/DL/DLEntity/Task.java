package GTD.DL.DLEntity;

import java.util.Date;

/**
 * Třída predstavující úkol - realizovatelnou akci.
 *
 * @version 1.0
 */
public class Task extends Action {

    /**
     * Záznam o úkolu v kalendáři.
     */
    private Interval kalendar;
    /**
     * Context úkolu.
     */
    private Context kontext;
    /**
     * Projekt úkolu.
     */
    private int id_projekt;
    /**
     * Nazev projektu ukolu
     */
    private String projekt_nazev;
    /**
     * Popis projektu ukolu
     */
    private String projekt_popis;
    /**
     * Tvůrce úkolu (může se lišit od vlastníka - což je přiřazená osoba)
     */
    private int id_tvurce;

    /**
     * Konstruktor ukolu
     */
    public Task() {
    }

    /*
     * Inicializace ukolu
     *
     * @param id
     * @param nazev
     * @param popis
     * @param stav
     * @param stavPopis
     * @param vlastnik_id
     * @param id_projekt
     */
    public Task(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id, int id_projekt) {
        super(id, nazev, popis, stav, stavPopis, vlastnik_id);
        this.id_projekt = id_projekt;
    }

    /**
     * Inicializace ukolu
     * 
     * @param nazev
     * @param popis
     * @param stav
     * @param tvurce_id
     * @param vlastnik_id
     * @param id_projekt
     */
    public Task(String nazev, String popis, int stav, int tvurce_id, int vlastnik_id, int id_projekt) {
        super(nazev, popis, stav, vlastnik_id);
        this.id_projekt = id_projekt;
        this.id_tvurce = tvurce_id;
    }

    /*
     * Nastav uzivatelsky kontext ukolu
     *
     * @param id
     * @param nazev
     */
    public void setKontext(int id, String nazev) {
        if (kontext == null) {
            kontext = new Context();
        }
        kontext.setKontext(id, nazev);
    }
    /*
     * Nastav interval ukolu
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
     * Nastav id projetku k ukolu
     * 
     * @param id_projekt
     */
    public void setProjekt(int id_projekt) {
        this.id_projekt = id_projekt;
    }

    /**
     * Nastav projekt ukolu
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
     * Vrati kalendat ukolu
     *
     * @return kalendar
     */
    public Interval getKalendar() {
        return kalendar;
    }

    /**
     * Vrati kontext ukolu
     * 
     * @return kontext
     */
    public Context getKontext() {
        return kontext;
    }

    /**
     * Vrati projekt úkolu
     * 
     * @return id
     */
    public int getProjekt() {
        return id_projekt;
    }

    /**
     * Vrátí název projektu
     * 
     * @return nazev
     */
    public String getProjektNazev() {
        return projekt_nazev;
    }

    /**
     * Vrátí popis projektu
     * 
     * @return nazev
     */
    public String getProjektPopis() {
        return projekt_popis;
    }

    /**
     * Vrati id osoby tvurce úkolu
     * 
     * @return id
     */
    public int getTvurce() {
        return id_tvurce;
    }
}
