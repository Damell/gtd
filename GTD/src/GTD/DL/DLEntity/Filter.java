package GTD.DL.DLEntity;

/**
 * Tato trída predstavuje spolecný nadtyp pro trídy Kontext, Složka (není
 * rešena) a Kategorie (není rešena).
 *
 * @version 1.0
 */
public class Filter {

    private int id;
    /**
     * Název filtru (filtry jedné osoby musí mít různá jména).
     */
    private String nazev;

    /*
     * Nastavi id a nazev filtru
     *
     * @param id
     * @param nazev
     */
    public void setFiltr(int id, String nazev) {
        this.id = id;
        this.nazev = nazev;
    }

    /**
     * Konstruktor filtru
     */
    public Filter() {

    }

    /*
     * Vrati id filtru
     *
     * @return id
     */
    public int getFiltrId() {
        return this.id;
    }

    /*
     * Vrati nazev filtru
     *
     * @return nazev
     */
    public String getFiltrNazev() {
        return this.nazev;
    }

}
