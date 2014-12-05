package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOPerson;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání osob z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class DAOPerson extends DAOGeneric<Person> implements IDAOPerson 
{



	public void finalize() throws Throwable {

	}

	/**
	 * Konstruktor osoby
	 */
	public DAOPerson(){

	}


	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * @return
	 * 
	 * @param osoba
	 */
//	@Override
//	public boolean deactivateOsoba(Person osoba){
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}


	/**
	 * Vráti osoby dle zadaného loginu
	 * @param login
	 * @return osoba
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Person getOsoba(String login)
	{
		Session session = null;
		Transaction tx = null;
		List<Person> persons = null;
		try {
			session = this.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(
					"from " + Person.class.getName() + " p "
					+ "where p.login = :login"
			);
			query.setParameter("login", login);
			persons = (List<Person>) query.list();
			tx.commit();
		} catch (HibernateException e) {
			handleException(e, tx);
		} finally {
			if (session != null) session.close();
		}
		
		if (persons == null || persons.isEmpty()) throw new ItemNotFoundException("User '" + login + "' not found"); // prvni cast podminky je tam jen aby si netbeans nestezoval (dereferencing possible null)
		return persons.get(0);
	}

//	/**
//	 * Vrátí osobu podle jejího ID.
//	 * @return osoba
//	 * 
//	 * @param id
//	 */
//	public Person getOsoba(int id){
//		return null;
//	}

	/**
	 * Vrátí ID prihlasene ososby.
	 * @return id
	 */
//	@Override
//	public int getOsobaID(){
//		throw new UnsupportedOperationException("method is not yet implemented");
//	}

//	/**
//	 * Zkontroluje, jestli už neexistuje uživatel s daným uživ. jménem.
//	 * @return true kdyz je login volny, jinak false
//	 * 
//	 * @param login
//	 */
//	@Override
//	public boolean checkNewLogin(String login){
//		return this.getOsoba(login) == null;
//	}
//
//	/**
//	 * Zkontroluje přihlašovací údaje. Zda je ucet povolen.
//	 * @return true=povolen, false=nepovolen
//	 * 
//	 * @param login
//	 */
//	@Override
//	public boolean checkPrihlaseni(String login){
//		throw new UnsupportedOperationException("method is not yet implemented");
//	}

//	/**
//	 * Uloží změny osoby.
//	 * @return
//	 * 
//	 * @param osoba
//	 */
//	public boolean updateOsoba(Person osoba){
//		return false;
//	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Person> getAll()
	{
		return (List<Person>) this.getAll(Person.class);
	}

	@Override
	public Person get(int id)
	{
		return (Person) this.get(Person.class, id);
	}

}