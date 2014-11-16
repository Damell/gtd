package GTD.DL.DLEntity;

import javax.persistence.Entity;


/**
 * Tato trída predstavuje kontext, ve kterém je úkol plnen. Urcuje v jaké
 * souvislosti lze daný úkol vykonat (pr. práce, doma). Každý uživatel má své
 * kontexty.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
@Entity
public class Context extends Filter {



	public void finalize() throws Throwable {
		super.finalize();
	}

//	/**
//	 * Vytvor kontext
//	 * 
//	 * @param id
//	 * @param nazev    nazev
//	 */
//	public Context(int id, String nazev){
//
//	}

	/**
	 * Kontruktor kontextu
	 */
	public Context(){

	}
	
	public Context(String nazev, Person vlastnik)
	{
		super(nazev, vlastnik);
	}

	@Override
	public String toString()
	{
		return "Kontext: id=" + getId() + ", nazev=" + getNazev();
	}
	

}