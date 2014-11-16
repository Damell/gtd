package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLInterfaces.IDAOProject;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání projektu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class DAOProject extends DAOGeneric<Project> implements IDAOProject {



	public void finalize() throws Throwable {

	}

	/**
	 * Konstruktor projektu
	 */
	public DAOProject(){

	}

	/**
	 * Vrátí všechny projekty patřící zadané osobe.
	 * @return List<Projekt>
	 * 
	 * @param osoba
	 */
	// TODO steklsim refactor vsechny metody getXOsoby() - obecna metoda getOwnedEntities() - ale kam s ni??
	@Override
	@SuppressWarnings("unchecked")
	public List<Project> getProjektyOsoby(Person osoba){ 
		Session session = null;
		Transaction tx = null;
		List<Project> projects = null;
		try {
			session = this.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(
					"from " + Project.class.getName() + " p "
					+ "where p.vlastnik = :vlastnik"
			);
			query.setParameter("vlastnik", osoba);
			projects = (List<Project>) query.list();
			tx.commit();
		} catch (HibernateException e) {
			handleException(e, tx);
		} finally {
			if (session != null) session.close();
		}
		
		return projects;
	}

//	/**
//	 * Uloží zmenený projekt.
//	 * @return
//	 * 
//	 * @param projekt
//	 */
//	public boolean updateProjekt(Project projekt){
//		return false;
//	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Project> getAll()
	{
		return (List<Project>) this.getAll(Project.class);
	}

	@Override
	public Project get(int id)
	{
		return (Project) this.get(Project.class, id);
	}

}