package GTD.DL.DLEntity;


/**
 * Tato trída predstavuje kontext, ve kterém je úkol plnen. Urcuje v jaké
 * souvislosti lze daný úkol vykonat (pr. práce, doma). Každý uživatel má své
 * kontexty.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class Context extends Filter {



	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * Vytvor kontext
	 * 
	 * @param id
	 * @param nazev    nazev
	 */
	public Context(int id, String nazev){

	}

	/**
	 * Kontruktor kontextu
	 */
	public Context(){

	}

	/**
	 * Vrati id kontextu
	 * @return id
	 */
	public int getKontextId(){
		return 0;
	}

	/**
	 * Vrati nazev kontextu
	 * @return nazev
	 */
	public String getKontextNazev(){
		return "";
	}

	/**
	 * Nastav kontext
	 * 
	 * @param id
	 * @param nazev    nazev
	 */
	public void setKontext(int id, String nazev){

	}

}