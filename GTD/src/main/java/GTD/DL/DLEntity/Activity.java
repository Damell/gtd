package GTD.DL.DLEntity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Trída predstavuje surovou cinnost tak, jak ji uživatel vymyslí, bez dalšího
 * clenení - je pripravena na prevedení do úkolu nebo projektu.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:50
 */
@Entity
public class Activity extends Action {

	/**
	 * stav činnosti
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private ActivityState stav;
	

	public void finalize() throws Throwable {
		super.finalize();
	}

	
	public Activity(){

	}
	
	/**
	 * 
	 * @param nazev
	 * @param popis
	 * @param vlastnik
	 * @param stav 
	 */
	public Activity(String nazev, String popis, Person vlastnik, ActivityState stav)
	{
		super(nazev, popis, vlastnik);
		this.stav = stav;
	}
	
	

//	/**
//	 * Konstruktor cinnosti
//	 * 
//	 * @param id
//	 * @param nazev
//	 * @param popis
//	 * @param stav
//	 * @param stavPopis
//	 * @param vlastnik_id    vlastnik_id
//	 */
//	public Activity(int id, String nazev, String popis, int stav, String stavPopis, int vlastnik_id){
//
//	}

	public ActivityState getStav()
	{
		return stav;
	}

	public void setStav(ActivityState stav)
	{
		this.stav = stav;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.stav);
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!super.equals(obj)) return false;
		
		final Activity other = (Activity) obj;
		if (!Objects.equals(this.stav, other.stav)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "Cinnost: id=" + getId() + ", nazev=" + getNazev();
	}
	
	

}