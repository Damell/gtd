package GTD.DL.DLEntity;


/**
 * Třída predstavující úkol - realizovatelnou akci.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
public class Task extends Action {

	/**
	 * Projekt úkolu.
	 */
	private int id_projekt;
	/**
	 * Tvůrce úkolu (může se lišit od vlastníka - což je přiřazená osoba)
	 */
	private int id_tvurce;
	/**
	 * Záznam o úkolu v kalendáři.
	 */
	private Interval kalendar;
	/**
	 * Context úkolu.
	 */
	private Context kontext;
	/**
	 * Nazev projektu ukolu
	 */
	private String projekt_nazev;
	/**
	 * Popis projektu ukolu
	 */
	private String projekt_popis;



	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * Inicializace ukolu
	 * 
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param tvurce_id
	 * @param vlastnik_id
	 * @param id_projekt    id_projekt
	 */
	public Task(String nazev, String popis, int stav, int tvurce_id, int vlastnik_id, int id_projekt){

	}

	/**
	 * Inicializace ukolu
	 * 
	 * @param id
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param stavPopis
	 * @param vlastnik_id
	 * @param id_projekt    id_projekt
	 */
	public Task(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id, int id_projekt){

	}

	/**
	 * Konstruktor ukolu
	 */
	public Task(){

	}

	/**
	 * Vrati kalendat ukolu
	 * @return kalendar
	 */
	public Interval getKalendar(){
		return null;
	}

	/**
	 * Vrati kontext ukolu
	 * @return kontext
	 */
	public Context getKontext(){
		return null;
	}

	/**
	 * Vrati projekt úkolu
	 * @return id
	 */
	public int getProjekt(){
		return 0;
	}

	/**
	 * Vrátí název projektu
	 * @return nazev
	 */
	public String getProjektNazev(){
		return "";
	}

	/**
	 * Vrátí popis projektu
	 * @return nazev
	 */
	public String getProjektPopis(){
		return "";
	}

	/**
	 * Vrati id osoby tvurce úkolu
	 * @return id
	 */
	public int getTvurce(){
		return 0;
	}

	/**
	 * Nastav interval ukolu
	 * 
	 * @param from
	 * @param to    to
	 */
	public void setInterval(Date from, Date to){

	}

	/**
	 * Nastav uzivatelsky kontext ukolu
	 * 
	 * @param id
	 * @param nazev    nazev
	 */
	public void setKontext(int id, String nazev){

	}

	/**
	 * Nastav projekt ukolu
	 * 
	 * @param id_projekt
	 * @param nazev
	 * @param popis    popis
	 */
	public void setProjekt(int id_projekt, String nazev, String popis){

	}

	/**
	 * Nastav id projetku k ukolu
	 * 
	 * @param id_projekt    id_projekt
	 */
	public void setProjekt(int id_projekt){

	}

}