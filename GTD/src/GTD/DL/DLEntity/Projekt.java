package GTD.DL.DLEntity;

import java.util.List;

/**
 * Třída predstavuje projekt - množinu souvisejících úkolu. Projekt muže krome
 * úkolu obsahovat i další projekty (pocet úrovní není omezen). Vlastník
 * projektu může delegovat jeho úkoly a podprojekty (v 1.úrovni).
 *
 * @author andel
 * @version 1.0
 * @created 26-4-2014 14:51:23
 */
public class Projekt extends Aktivita {

    /**
     * Rodič - nadřazený projekt.
     */
    private Projekt rodic;
    /**
     * Skupina osob pracujících na projektu - slouží pro delegování aktivit v
     * rámci projektu.
     */
    private List<Osoba> skupina;
    /**
     * Úkoly projektu.
     */
    private List<Ukol> ukoly;
    /**
     * Podprojekty projektu.
     */
    private List<Projekt> projekty;

    public Projekt() {

    }

    public Projekt(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id) {
        super(id, nazev, popis, stav, stavPopis, vlastnik_id);
    }
    
    /*
    * Nastav rodice projektu
    */
    public void setProjectrodic(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id){
        this.rodic.setAktivita(id, nazev, popis, stav, stavPopis, vlastnik_id);
    }
    /*
    * Pridej osobu do projektu
    */
    public void addOsoba(Osoba osoba){
        this.skupina.add(osoba);
    }
    /*
    * Pridej ukol do projektu
    */
    public void addUkol(Ukol ukol){
        this.ukoly.add(ukol);
    }
    /*
    * Pridej podprojekt do projektu
    */
    public void addProjekt(Projekt projekt){
        this.projekty.add(projekt);
    }

    @Override
    public String toString() {
        return super.toString() + "rodic=" + rodic + ", skupina=" + skupina + ", ukoly=" + ukoly + ", projekty=" + projekty + '}';
    }

}
