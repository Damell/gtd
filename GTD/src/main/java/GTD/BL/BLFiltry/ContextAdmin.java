package GTD.BL.BLFiltry;

import GTD.DL.DLInterfaces.IDAOContext;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Context;
import java.util.List;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání kontextu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class ContextAdmin {

	private IDAOContext DAOKontext;

	public ContextAdmin(){
		
	}

	public void setDAOContext(IDAOContext $dao)
	{
		this.DAOKontext = $dao;
	}
	
	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable{

	}

	/**
	 * Přidá osobě nový kontext.
	 * @return
	 * 
	 * @param kontext
	 * @param osoba
	 */
	public void addKontext(Context kontext, Person osoba){ // TODO steklsim nestaci jen parametr 'kontext', resp. metoda updateContext()? (kontext ma atribut 'vlastnik')
		kontext.setVlastnik(osoba);
		DAOKontext.create(kontext); // TODO steklsim chytat DAOException v BL?
	}

	/**
	 * Smaže kontext.
	 * @return
	 * 
	 * @param kontext
	 */
	public void deleteKontext(Context kontext){
		DAOKontext.delete(kontext);
	}

	/**
	 * Vrátí kontext podle jeho ID.
	 * @return
	 * 
	 * @param id
	 */
	public Context getKontext(int id){
		return DAOKontext.get(id);
	}

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * @return
	 * 
	 * @param osoba
	 */
	public List getKontextyOsoby(Person osoba){
		return DAOKontext.getKontextyOsoby(osoba);
	}

	/**
	 * Uloží zmenený kontext.
	 * @return
	 * 
	 * @param kontext
	 */
	public void updateKontext(Context kontext){
		DAOKontext.update(kontext);
	}

}