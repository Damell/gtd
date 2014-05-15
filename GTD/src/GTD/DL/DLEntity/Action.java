package GTD.DL.DLEntity;

/**
 * Tato trída predstavuje spolecný nadtyp pro trídy Cinnost, Úkol a Projekt.
 *
 * @author GTD team
 * @version 1.0
 */
public class Action {

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
     * Stav, ve kterém se aktivita nachází (každá podtřída má své stavy
     * definované v databázi).
     */
    private int stav;
    /**
     * Vlastník aktivity - má právo ji upravovat (změnit název/popis, smazat, v
     * případě úkolu a projektu dokončit a delegovat).
     */
    private int vlastnik_id;
    /**
     * Název stavu aktivity
     */
    private String stavPopis;

    /**
     * Konstruktor aktivity
     */
    public Action() {

    }

    /**
     * Konstruktor aktivity
     *
     * @param id
     * @param nazev
     * @param popis
     * @param stav
     * @param stavPopis
     * @param vlastnik_id
     */
    public Action(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id) {
        this.id = id;
        this.nazev = nazev;
        this.popis = popis;
        this.stav = stav;
        this.stavPopis = stavPopis;
        this.vlastnik_id = vlastnik_id;
    }

    /**
     * Konstruktor aktivity
     *
     * @param nazev
     * @param popis
     * @param stav
     * @param vlastnik_id
     */
    public Action(String nazev, String popis, int stav, int vlastnik_id) {
        this.nazev = nazev;
        this.popis = popis;
        this.stav = stav;
        this.vlastnik_id = vlastnik_id;
    }

    /*
     * Nastav aktivitu
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
     * Nastavi id aktivity
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Nastav stav aktivity
     *
     * @param stav
     */
    public void setStav(int stav) {
        this.stav = stav;
    }

    /**
     * Nastav vlastníka aktivity
     *
     * @param id
     */
    public void setVlastnikID(int id) {
        this.vlastnik_id = id;
    }

    /**
     * Vrátí id aktivity
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Vrátí název aktivity
     *
     * @return nazev
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrátí popis aktivity
     *
     * @return popis
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Vrátí id stavu aktivity
     *
     * @return id
     */
    public int getStav() {
        return stav;
    }

    /**
     * Vrátí id vlastníka aktivity
     *
     * @return vlastnik_id
     */
    public int getVlastnik_id() {
        return vlastnik_id;
    }

    /**
     * Vrátí popis stavu aktivity
     *
     * @return popis
     */
    public String getStavPopis() {
        return stavPopis;
    }

    /**
     * Vrátí název a popis aktivity v jendom řetězci
     *
     * @return popis
     */
    @Override
    public String toString() {
        return nazev + " :: " + popis;
        //return "Action{" + "id=" + id + ", nazev=" + nazev + ", popis=" + popis + ", stav=" + stav + ", vlastnik_id=" + vlastnik_id + ", stavPopis=" + stavPopis + '}';
    }

}
