package GTD.DL.DLEntity;
import Analýza.Doménový model.Interval;

/**
 * Trída predstavuje casový interval pro daný úkol.
 * @author andel
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public class Interval {

	/**
	 * Casový pocátek úkolu. Minimální presnost jsou dny. 
	 */
	private Date od;
	/**
	 * Casový konec úkolu. Minimální presnost jsou dny. 
	 */
	private Date do;

	public Interval(){

	}

	public void finalize() throws Throwable {

	}

}