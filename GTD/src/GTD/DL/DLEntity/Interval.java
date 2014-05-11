package GTD.DL.DLEntity;

import java.util.Date;

/**
 * Trída predstavuje casový interval pro daný úkol.
 *
 * @author andel
 * @version 1.0
 * @created 26-4-2014 14:51:22
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
     */
    public void setInterval(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public boolean isSet() {
        return from != null && to != null;
    }

    public Interval() {
    }

    public Interval(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

}
