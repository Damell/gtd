package GTD.DL.DLEntity;

/**
 * Tato trída predstavuje spolecný nadtyp pro trídy Cinnost, Úkol a Projekt.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:19
 */
public class Aktivita {

	private int id;
	/**
	 * Název aktivity
	 */
	private String nazev;
	/**
	 * Popis aktivity
	 */
	private String popis;
	/**
	 * Stav, ve kterém se aktivita nachází (každá podtřída má své stavy definované v
	 * databázi).
	 */
	private int stav;
	/**
	 * Vlastník aktivity - má právo ji upravovat (změnit název/popis, smazat, v
	 * případě úkolu a projektu dokončit a delegovat).
	 */
	private int vlastnik_id;

	private String stavPopis; /*pro zobrazeni v gridu*/

	public Aktivita(){

	}

	public Aktivita(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id) {
		this.id = id;
		this.nazev = nazev;
		this.popis = popis;
		this.stav = stav;
		this.stavPopis = stavPopis;
                this.vlastnik_id = vlastnik_id;
	}
        
        /*
        * Nastav aktivitu
        */
        public void setAktivita(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id) {
		this.id = id;
		this.nazev = nazev;
		this.popis = popis;
		this.stav = stav;
		this.stavPopis = stavPopis;
                this.vlastnik_id = vlastnik_id;
	}


	public int getId() {
		return id;
	}

	public String getNazev() {
		return nazev;
	}

	public String getPopis() {
		return popis;
	}

	public int getStav() {
		return stav;
	}

	public int getVlastnik_id() {
		return vlastnik_id;
	}

	public String getStavPopis() {
		return stavPopis;
	}

	@Override
	public String toString() {
		return "Aktivita{" + "id=" + id + ", nazev=" + nazev + ", popis=" + popis + ", stav=" + stav + ", vlastnik_id=" + vlastnik_id + ", stavPopis=" + stavPopis + '}';
	}

}