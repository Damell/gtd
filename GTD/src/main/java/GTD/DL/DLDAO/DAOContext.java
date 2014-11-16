package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOContext;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání kontextu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class DAOContext extends DAOGeneric<Context> implements IDAOContext {



	public void finalize() throws Throwable {

	}

	/**
	 * Kontruktor kontextu
	 */
	public DAOContext(){

	}

	/**
	 * Vrátí všechny kontexty patrící zadané osobe.
	 * @return List<Kontext>
	 * 
	 * @param osoba
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Context> getKontextyOsoby(Person osoba){
		
		Session session = null;
		Transaction tx = null;
		List<Context> contexts = null;
		try {
			session = this.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(
				"from " + Context.class.getName() + " c " + 
				"where c.vlastnik = :vlastnik"
			);
			query.setParameter("vlastnik", osoba);
			contexts = (List<Context>) query.list();
			tx.commit();
		} catch (HibernateException e) {
			handleException(e, tx);
		} finally {
			if (session != null) session.close();
		}
		
		return contexts;
	}

//	/**
//	 * Uloží zmenený kontext.
//	 * @return
//	 * 
//	 * @param kontext
//	 */
//	public boolean updateKontext(Context kontext){
//		return false;
//	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Context> getAll()
	{
		return (List<Context>) this.getAll(Context.class);
	}

	@Override
	public Context get(int id)
	{
		return (Context) this.get(Context.class, id);
	}

}