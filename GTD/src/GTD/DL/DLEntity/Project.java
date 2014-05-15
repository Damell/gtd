package GTD.DL.DLEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Třída predstavuje projekt - množinu souvisejících úkolu. Project muže krome
 úkolu obsahovat i další projekty (pocet úrovní není omezen). Vlastník
 projektu může delegovat jeho úkoly a podprojekty (v 1.úrovni).
 *
 * @version 1.0
 */
public class Project extends Action {

    /**
     * Rodič - nadřazený projekt.
     */
    private Project rodic;
    /**
     * Skupina osob pracujících na projektu - slouží pro delegování aktivit v
     * rámci projektu.
     */
    private List<Person> skupina;
    /**
     * Úkoly projektu.
     */
    private List<Task> ukoly;
    /**
     * Podprojekty projektu.
     */
    private List<Project> projekty;

    /**
     * Konstruktor projektu
     */
    public Project() {
        this.projekty = new ArrayList<>();
        this.ukoly = new ArrayList<>();
        this.skupina = new ArrayList<>();
        this.rodic = null;
    }

    /**
     * Konstruktor projektu
     *
     * @param id
     * @param nazev
     * @param popis
     * @param stav
     * @param stavPopis
     * @param vlastnik_id
     */
    public Project(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id) {
        super(id, nazev, popis, stav, stavPopis, vlastnik_id);
        this.projekty = new ArrayList<>();
        this.ukoly = new ArrayList<>();
        this.skupina = new ArrayList<>();
        this.rodic = null;
    }

    /**
     * Konstruktor projektu
     *
     * @param nazev
     * @param popis
     * @param stav
     * @param vlastnik_id
     * @param skupina
     * @param rodic
     */
    public Project(String nazev, String popis, int stav, int vlastnik_id, List<Person> skupina, Project rodic) {
        super(nazev, popis, stav, vlastnik_id);
        this.projekty = new ArrayList<>();
        this.ukoly = new ArrayList<>();
        this.skupina = skupina;
        this.rodic = rodic;
    }

    /*
     * Nastav rodice projektu
     *
     * @param id
     * @param nazev
     * @param popis
     * @param stav
     * @param stavPopis
     * @param vlastnik_id
     */
    public void setProjectrodic(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id) {
        if (rodic == null) {
            this.rodic = new Project();
        }
        this.rodic.setAktivita(id, nazev, popis, stav, stavPopis, vlastnik_id);
    }
    /*
     * Pridej osobu do projektu
     *
     * @param osoba
     */
    public void addOsoba(Person osoba) {
        this.skupina.add(osoba);
    }
    /*
     * Pridej ukol do projektu
     *
     * @param ukol
     */
    public void addUkol(Task ukol) {
        this.ukoly.add(ukol);
    }
    /*
     * Pridej podprojekt do projektu
     *
     * @param projekt
     */
    public void addProjekt(Project projekt) {
        this.projekty.add(projekt);
    }

      
    /*
     * Vrátí název a popis projektu
     *
     * @return nazev_popis
     */
    @Override
    public String toString() {
        //return super.toString() + "rodic=" + rodic + ", skupina=" + skupina + ", ukoly=" + ukoly + ", projekty=" + projekty + '}';
        return super.toString();
    }

    /**
     * Vrati id rodice projektu
     * 
     * @return id
     */
    public Project getRodic() {
        return rodic;
    }

    /**
     * Vrati skupinu v projektu
     *
     * @return List<Osoba>
     */
    public List<Person> getSkupina() {
        return skupina;
    }

    /**
     * Vratí úkoly v projekty
     * 
     * @returnList<Ukol>
     */
    public List<Task> getUkoly() {
        return ukoly;
    }

    /**
     * Vrátí podprojekty
     * 
     * @return List<Projekt>
     */
    public List<Project> getProjekty() {
        return projekty;
    }

}
