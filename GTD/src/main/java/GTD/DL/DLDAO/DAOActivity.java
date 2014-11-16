package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOActivity;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání cinností z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class DAOActivity extends DAOGeneric<Activity> implements IDAOActivity {



	public void finalize() throws Throwable {

	}

	/**
	 * Konstruktor činnosti
	 */
	public DAOActivity(){

	}

	/**
	 * Vrátí všechny cinnosti patrící zadané osobe.
	 * @return List<Cinnost>
	 * 
	 * @param osoba
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Activity> getCinnostiOsoby(Person osoba){
		Session session = null;
		Transaction tx = null;
		List<Activity> activities = null;
		try {
			session = this.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(
					"from " + Activity.class.getName() + " a "
					+ "where a.vlastnik = :vlastnik"
			);
			query.setParameter("vlastnik", osoba);
			activities = (List<Activity>) query.list();
			tx.commit();
		} catch (HibernateException e) {
			handleException(e, tx);
		} finally {
			if (session != null) session.close();
		}
		
		return activities;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Activity> getAll()
	{
		return (List<Activity>) this.getAll(Activity.class);
	}

	@Override
	public Activity get(int id)
	{
		return (Activity) this.get(Activity.class, id);
	}

	
	
	
}