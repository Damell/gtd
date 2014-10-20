package GTD.DL.DLEntity;


/**
 * Tato trída predstavuje spolecný nadtyp pro trídy Kontext, Složka (není rešena)
 * a Kategorie (není rešena).
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:52
 */
public class Filter {

	private int id;
	/**
	 * Název filtru (filtry jedné osoby musí mít různá jména).
	 */
	private String nazev;



	public void finalize() throws Throwable {

	}

	/**
	 * Konstruktor filtru
	 */
	public Filter(){

	}

	/**
	 * Vrati id filtru
	 * @return id
	 */
	public int getFiltrId(){
		return 0;
	}

	/**
	 * Vrati nazev filtru
	 * @return nazev
	 */
	public String getFiltrNazev(){
		return "";
	}

	/**
	 * Nastavi id a nazev filtru
	 * 
	 * @param id
	 * @param nazev    nazev
	 */
	public void setFiltr(int id, String nazev){

	}

}