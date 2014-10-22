package GTD.DL.DLEntity;

import java.util.Date;


/**
 * Trída predstavuje casový interval pro daný úkol.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:53
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



	public void finalize() throws Throwable {

	}

	/**
	 * Nastavení hodnot intervalu
	 * 
	 * @param from
	 * @param to    to
	 */
	public Interval(Date from, Date to){

	}

	/**
	 * Konstruktor intevalu
	 */
	public Interval(){

	}

	/**
	 * Vrátí datum do z intervalu
	 * @return from
	 */
	public Date getFrom(){
		return null;
	}

	/**
	 * Vrátí datum od z intervalu
	 * @return to
	 */
	public Date getTo(){
		return null;
	}

	/**
	 * Vrátí tru, pikud je interval nastaven
	 * @return
	 */
	public boolean isSet(){
		return false;
	}

	/**
	 * Nastav interval
	 * 
	 * @param from
	 * @param to    to
	 */
	public void setInterval(Date from, Date to){

	}

}