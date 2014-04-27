package GTD.DL.DLEntity;

/**
 * Třída predstavující úkol - realizovatelnou akci.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class Ukol extends Aktivita {

	/**
	 * Záznam o úkolu v kalendáři.
	 */
	private Interval kalendar;
	/**
	 * Kontext úkolu.
	 */
	private Kontext kontext;

	public Ukol(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}