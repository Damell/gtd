package GTD.DL.DLEntity;

/**
 * Tato trída predstavuje spolecný nadtyp pro trídy Kontext, Složka (není
 * rešena) a Kategorie (není rešena).
 *
 * @author andel
 * @version 1.0
 * @created 26-4-2014 14:51:21
 */
public class Filtr {

    private int id;
    /**
     * Název filtru (filtry jedné osoby musí mít různá jména).
     */
    private String nazev;

    /*
     * Nastavi id a nazev filtru
     */
    public void setFiltr(int id, String nazev) {
        this.id = id;
        this.nazev = nazev;
    }

    public Filtr() {

    }

    /*
     * Vrati nazev filtru
     */
    public int getFiltrId() {
        return this.id;
    }

    /*
     * Vrati nazev filtru
     */
    public String getFiltrNazev() {
        return this.nazev;
    }

}
