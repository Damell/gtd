package GTD.DL.DLEntity;

import java.util.Date;

/**
 * Trída predstavuje casový interval pro daný úkol.
 *
 * @author GTD team
 * @version 1.0
 */
public class Interval {

    /**
     * Casový pocátek úkolu. Minimální presnost jsou dny.
     */
    private Date from;
    /**
     * Casový konec úkolu. Minimální presnost jsou dny.
     */
    private Date to;

    /*
     * Nastav interval
     *
     * @param from
     * @param to
     */
    public void setInterval(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Vrátí datum do z intervalu
     * @return from
     */
    public Date getFrom() {
        return from;
    }

    /**
     * Vrátí datum od z intervalu
     * 
     * @return to
     */
    public Date getTo() {
        return to;
    }

    /**
     * Vrátí tru, pikud je interval nastaven
     * 
     */
    public boolean isSet() {
        return from != null && to != null;
    }

    /**
     * Konstruktor intevalu
     */
    public Interval() {
    }

    /**
     * Nastavení hodnot intervalu
     * 
     * @param from
     * @param to
     */
    public Interval(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

}
