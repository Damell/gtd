package GTD.DL.DLEntity;

/**
 * Trída predstavuje surovou cinnost tak, jak ji uživatel vymyslí, bez dalšího
 * clenení - je pripravena na prevedení do úkolu nebo projektu.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:19
 */
public class Cinnost extends Aktivita {

	public Cinnost(int id, String nazev, String popis, int stav, String stavPopis,int vlastnik_id) {
		super(id,nazev,popis,stav,stavPopis,vlastnik_id);
	}

}