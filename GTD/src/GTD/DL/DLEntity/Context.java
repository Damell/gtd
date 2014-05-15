package GTD.DL.DLEntity;

/**
 * Tato trída predstavuje kontext, ve kterém je úkol plnen. Urcuje v jaké
 * souvislosti lze daný úkol vykonat (pr. práce, doma). Každý uživatel má své
 * kontexty.
 *
 * @author GTD team
 * @version 1.0
 */
public class Context extends Filter {

    /**
     * Kontruktor kontextu
     */
    public Context() {

    }
    /*
     * Vytvor kontext

     *
     * @param id
     * @param nazev
     */

    public Context(int id, String nazev) {
        this.setFiltr(id, nazev);
    }
    /*
     * Nastav kontext
     *
     * @param id
     * @param nazev
     */

    public void setKontext(int id, String nazev) {
        this.setFiltr(id, nazev);
    }
    /*
     * Vrati id kontextu
     *
     * @return id
     */

    public int getKontextId() {
        return this.getFiltrId();
    }

    /*
     * Vrati nazev kontextu
     *
     * @return nazev
     */
    public String getKontextNazev() {
        return this.getFiltrNazev();
    }

}
