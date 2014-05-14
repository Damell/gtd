package GTD.DL.DLEntity;

/**
 * Tato trída predstavuje kontext, ve kterém je úkol plnen. Urcuje v jaké
 * souvislosti lze daný úkol vykonat (pr. práce, doma). Každý uživatel má své
 * kontexty.
 *
 * @version 1.0
 */
public class Kontext extends Filtr {

	/**
	 *
	 */
	public Kontext() {

    }
    /*
     * Vytvor kontext
     */

	/**
	 *
	 * @param id
	 * @param nazev
	 */
	public Kontext(int id, String nazev) {
        this.setFiltr(id, nazev);
    }
    /*
     * Nastav kontext
     */

	/**
	 *
	 * @param id
	 * @param nazev
	 */
	public void setKontext(int id, String nazev) {
        this.setFiltr(id, nazev);
    }
    /*
     * Vrati id kontextu
     */

	/**
	 *
	 * @return
	 */
	public int getKontextId() {
        return this.getFiltrId();
    }

    /*
     * Vrati nazev kontextu
     */

	/**
	 *
	 * @return
	 */
	
    public String getKontextNazev() {
        return this.getFiltrNazev();
    }

}
