package GTD.DL.DLEntity;

/**
 * Trída predstavuje surovou cinnost tak, jak ji uživatel vymyslí, bez dalšího
 * clenení - je pripravena na prevedení do úkolu nebo projektu.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:19
 */
public class Cinnost extends Aktivita {

	public Cinnost(){
		
	}

	public Cinnost(int id, String name, String desc, int idType, String typeDesc) {
		super(id, name, desc, idType, typeDesc);
	}

}