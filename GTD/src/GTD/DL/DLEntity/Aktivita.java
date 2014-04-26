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
	private Osoba vlastnik;
	public Osoba vlastník;

	public Aktivita(){

	}

	public void finalize() throws Throwable {

	}

}