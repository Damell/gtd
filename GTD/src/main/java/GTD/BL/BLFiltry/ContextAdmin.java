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

	public void setDAOContext(IDAOContext dao)
	{
		this.DAOKontext = dao;
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
	 * @param user logged-in user
	 */
	public void addKontext(Context kontext, Person user){ 
		if (kontext.getVlastnik().equals(user)) {
			DAOKontext.create(kontext); 
		} else {
			throw new SecurityException("Context owned by '" 
				+ kontext.getVlastnik().getLogin() + "' can't be added by '" 
				+ user.getLogin() + "'");
		}
		
	}

	/**
	 * Smaže kontext.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param kontext
	 */
	public void deleteKontext(Context kontext, Person user){
		if (kontext.getVlastnik().equals(user)) {
			DAOKontext.delete(kontext);
		} else {
			throw new SecurityException("Context owned by '" 
				+ kontext.getVlastnik().getLogin() + "' can't be deleted by '" 
				+ user.getLogin() + "'");
		}
	}

	/**
	 * Vrátí kontext podle jeho ID.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param id
	 */
	public Context getKontext(int id, Person user){
		Context kontext = DAOKontext.get(id);
		if (kontext != null && !kontext.getVlastnik().equals(user)) {	
			throw new SecurityException("Context owned by '" 
				+ kontext.getVlastnik().getLogin() + "' can't be accessed by '" 
				+ user.getLogin() + "'");
		}
		return kontext;
	}

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param osoba
	 */
	public List getKontextyOsoby(Person user){
		return DAOKontext.getKontextyOsoby(user);
	}

	/**
	 * Uloží zmenený kontext.
	 * @param user logged-in user
	 * @return
	 * 
	 * @param kontext
	 */
	public void updateKontext(Context kontext, Person user){
		if (kontext.getVlastnik().equals(user)) {
			DAOKontext.update(kontext);
		} else {
			throw new SecurityException("Context owned by '" 
				+ kontext.getVlastnik().getLogin() + "' can't be updated by '" 
				+ user.getLogin() + "'");
		}
	}

}