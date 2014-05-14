package GTD.DL.DLEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Třída predstavuje projekt - množinu souvisejících úkolu. Projekt muže krome
 * úkolu obsahovat i další projekty (pocet úrovní není omezen). Vlastník
 * projektu může delegovat jeho úkoly a podprojekty (v 1.úrovni).
 *
 * @version 1.0
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

	/**
	 *
	 */
	public Projekt() {
		this.projekty = new ArrayList<>();
		this.ukoly = new ArrayList<>();
		this.skupina = new ArrayList<>();
		this.rodic = null;
    }

	/**
	 *
	 * @param id
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param stavPopis
	 * @param vlastnik_id
	 */
	public Projekt(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id) {
        super(id, nazev, popis, stav, stavPopis, vlastnik_id);
		this.projekty = new ArrayList<>();
		this.ukoly = new ArrayList<>();
		this.skupina = new ArrayList<>();
		this.rodic = null;
    }

	/**
	 *
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param vlastnik_id
	 * @param skupina
	 * @param rodic
	 */
	public Projekt(String nazev, String popis, int stav, int vlastnik_id, List<Osoba> skupina, Projekt rodic) {
        super(nazev, popis, stav, vlastnik_id);
		this.projekty = new ArrayList<>();
		this.ukoly = new ArrayList<>();
		this.skupina = skupina;
		this.rodic = rodic;
    }

    /*
    * Nastav rodice projektu
    */

	/**
	 *
	 * @param id
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param stavPopis
	 * @param vlastnik_id
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

	/**
	 *
	 * @param osoba
	 */
	
    public void addOsoba(Osoba osoba){
        this.skupina.add(osoba);
    }
    /*
    * Pridej ukol do projektu
    */

	/**
	 *
	 * @param ukol
	 */
	
    public void addUkol(Ukol ukol){
        this.ukoly.add(ukol);
    }
    /*
    * Pridej podprojekt do projektu
    */

	/**
	 *
	 * @param projekt
	 */
	
    public void addProjekt(Projekt projekt){
        this.projekty.add(projekt);
    }

    @Override
    public String toString() {
        //return super.toString() + "rodic=" + rodic + ", skupina=" + skupina + ", ukoly=" + ukoly + ", projekty=" + projekty + '}';
		return super.toString();
    }

	/**
	 *
	 * @return
	 */
	public Projekt getRodic() {
		return rodic;
	}

	/**
	 *
	 * @return
	 */
	public List<Osoba> getSkupina() {
		return skupina;
	}

	/**
	 *
	 * @return
	 */
	public List<Ukol> getUkoly() {
		return ukoly;
	}

	/**
	 *
	 * @return
	 */
	public List<Projekt> getProjekty() {
		return projekty;
	}

}
