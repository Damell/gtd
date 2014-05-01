package GTD.DL.DLEntity;

/**
 * Tato trída predstavuje kontext, ve kterém je úkol plnen. Urcuje v jaké
 * souvislosti lze daný úkol vykonat (pr. práce, doma). Každý uživatel má své
 * kontexty.
 *
 * @author andel
 * @version 1.0
 * @created 26-4-2014 14:51:23
 */
public class Kontext extends Filtr {

    public Kontext() {

    }
    /*
     * Nastav kontext
     */

    public void setKontext(int id, String nazev) {
        this.setFiltr(id, nazev);
    }
    /*
     * Vrati id kontextu
     */

    public int getKontextId() {
        return this.getFiltrId();
    }

    /*
     * Vrati nazev kontextu
     */
    public String getKontextNazev() {
        return this.getFiltrNazev();
    }

    public void finalize() throws Throwable {
        super.finalize();
    }

}
