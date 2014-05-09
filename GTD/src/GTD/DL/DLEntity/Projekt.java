package GTD.DL.DLEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
		projekty = new ArrayList<>();
		ukoly = new ArrayList<>();
		skupina = new ArrayList<>();
		rodic = null;
    }

    public Projekt(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id) {
        super(id, nazev, popis, stav, stavPopis, vlastnik_id);
		projekty = new ArrayList<>();
		ukoly = new ArrayList<>();
		skupina = new ArrayList<>();
		rodic = null;
    }
    public Projekt(String nazev, String popis, int stav, int vlastnik_id, Projekt rodic) {
        super(nazev, popis, stav, vlastnik_id);
		projekty = new ArrayList<>();
		ukoly = new ArrayList<>();
		skupina = new ArrayList<>();
		this.rodic = rodic;
    }

    /*
    * Nastav rodice projektu
    */
    public void setProjectrodic(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id){
		if(rodic == null) {
			this.rodic = new Projekt();
		}
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
        //return super.toString() + "rodic=" + rodic + ", skupina=" + skupina + ", ukoly=" + ukoly + ", projekty=" + projekty + '}';
		return super.toString();
    }

	public Projekt getRodic() {
		return rodic;
	}

	public List<Osoba> getSkupina() {
		return skupina;
	}

	public List<Ukol> getUkoly() {
		return ukoly;
	}

	public List<Projekt> getProjekty() {
		return projekty;
	}

}
