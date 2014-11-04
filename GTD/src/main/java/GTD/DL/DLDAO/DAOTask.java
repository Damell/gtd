package GTD.DL.DLDAO;

import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLInterfaces.IDAOTask;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Trída zapouzdruje metody pro ukládání a nacítání úkolu z databáze.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:52
 */
public class DAOTask extends DAOGeneric<Task> implements IDAOTask {



	public void finalize() throws Throwable {

	}

	/**
	 * Konstruktor úkolu
	 */
	public DAOTask(){
		
	}

//	/**
//	 * Vytvorí nový úkol zadaných vlastností a uloží ho do databáze.
//	 * @return
//	 * 
//	 * @param ukol
//	 */
//	public boolean createUkol(Task ukol){
//		return false;
//	}
//
//	/**
//	 * Smaže úkol z databáze (resp. označí jako smazaný).
//	 * @return
//	 * 
//	 * @param ukol
//	 */
//	public boolean deleteUkol(Task ukol){
//		return false;
//	}
//
//	/**
//	 * Vrátí všechny úkoly v systému.
//	 * @return List<Ukol>
//	 */
//	public List getAllUkoly(){
//		return null;
//	}
//
//	/**
//	 * Vrátí úkol podle jeho ID.
//	 * @return ukol
//	 * 
//	 * @param id
//	 */
//	public Task getUkol(int id){
//		return null;
//	}

	/**
	 * Vrátí všechny úkoly daného kontextu.
	 * @return ukoly
	 * 
	 * @param kontext
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Task> getUkolyKontextu(Context kontext){
		Session session = this.openSession();
		Query query = session.createQuery(
				"from " + Task.class.getName() + " t " + 
				"where t.kontext = :kontext"
		);
		query.setParameter("kontext", kontext);
		List<Task> projects = (List<Task>) query.list();
		session.close();
		
		return projects;
	}

	/**
	 * Vrátí úkoly osoby.
	 * @return List<Ukol>
	 * 
	 * @param osoba
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Task> getUkolyOsoby(Person osoba){
		Session session = this.openSession();
		Query query = session.createQuery(
				"from " + Task.class.getName() + " t " + 
				"where t.vlastnik = :vlastnik"
		);
		query.setParameter("vlastnik", osoba);
		List<Task> projects = (List<Task>) query.list();
		session.close();
		
		return projects;
	}

//	/**
//	 * Uloží zmenený úkol.
//	 * @return
//	 * 
//	 * @param ukol
//	 */
//	public boolean updateUkol(Task ukol){
//		return false;
//	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Task> getAll()
	{
		return (List<Task>) this.getAll(Task.class);
	}

	@Override
	public Task get(int id)
	{
		return (Task) this.get(Task.class, id);
	}
	
}