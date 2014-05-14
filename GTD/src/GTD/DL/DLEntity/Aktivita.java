package GTD.DL.DLEntity;

/**
 * Tato trída predstavuje spolecný nadtyp pro trídy Cinnost, Úkol a Projekt.
 * @version 1.0
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
	
	/**
	 *
	 */
	public Aktivita(){
		
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
	public Aktivita(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id) {
		this.id = id;
		this.nazev = nazev;
		this.popis = popis;
		this.stav = stav;
		this.stavPopis = stavPopis;
		this.vlastnik_id = vlastnik_id;
	}

	/**
	 *
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param vlastnik_id
	 */
	public Aktivita(String nazev, String popis, int stav, int vlastnik_id) {
		this.nazev = nazev;
		this.popis = popis;
		this.stav = stav;
		this.vlastnik_id = vlastnik_id;
	}
	
	/*
	* Nastav aktivitu
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
	
	public void setAktivita(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id) {
		this.id = id;
		this.nazev = nazev;
		this.popis = popis;
		this.stav = stav;
		this.stavPopis = stavPopis;
		this.vlastnik_id = vlastnik_id;
	}
	
	/**
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 *
	 * @param stav
	 */
	public void setStav(int stav) {
		this.stav = stav;
	}

	/**
	 *
	 * @param id
	 */
	public void setVlastnikID(int id) {
		this.vlastnik_id = id;
	}
        
	/**
	 *
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 *
	 * @return
	 */
	public String getNazev() {
		return nazev;
	}
	
	/**
	 *
	 * @return
	 */
	public String getPopis() {
		return popis;
	}
	
	/**
	 *
	 * @return
	 */
	public int getStav() {
		return stav;
	}
	
	/**
	 *
	 * @return
	 */
	public int getVlastnik_id() {
		return vlastnik_id;
	}
	
	/**
	 *
	 * @return
	 */
	public String getStavPopis() {
		return stavPopis;
	}

	@Override
	public String toString() {
		return nazev + " :: " + popis;
		//return "Aktivita{" + "id=" + id + ", nazev=" + nazev + ", popis=" + popis + ", stav=" + stav + ", vlastnik_id=" + vlastnik_id + ", stavPopis=" + stavPopis + '}';
	}
	
}