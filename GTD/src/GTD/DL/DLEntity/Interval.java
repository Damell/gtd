package GTD.DL.DLEntity;

import java.util.Date;

/**
 * Trída predstavuje casový interval pro daný úkol.
 *
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
     */

	/**
	 *
	 * @param from
	 * @param to
	 */
	
    public void setInterval(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

	/**
	 *
	 * @return
	 */
	public Date getFrom() {
        return from;
    }

	/**
	 *
	 * @return
	 */
	public Date getTo() {
        return to;
    }

	/**
	 *
	 * @return
	 */
	public boolean isSet() {
        return from != null && to != null;
    }

	/**
	 *
	 */
	public Interval() {
    }

	/**
	 *
	 * @param from
	 * @param to
	 */
	public Interval(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

}
