package GTD.DL.DLEntity;

/**
 * Trída predstavuje surovou cinnost tak, jak ji uživatel vymyslí, bez dalšího
 * clenení - je pripravena na prevedení do úkolu nebo projektu.
 * @version 1.0
 */
public class Cinnost extends Aktivita {

	/**
	 *
	 * @param id
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param stavPopis
	 * @param vlastnik_id
	 */
	public Cinnost(int id, String nazev, String popis, int stav, String stavPopis,int vlastnik_id) {
		super(id,nazev,popis,stav,stavPopis,vlastnik_id);
	}

	/**
	 *
	 * @param nazev
	 * @param popis
	 * @param stav
	 * @param vlastnik_id
	 */
	public Cinnost(String nazev, String popis, int stav, int vlastnik_id) {
		super(nazev, popis, stav, vlastnik_id);

	}

}