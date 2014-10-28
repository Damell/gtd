/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.DL.DLDAO;

import GTD.DL.DLInterfaces.IDAOGeneric;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author simon
 * @param <T>
 */
public abstract class DAOGeneric<T> implements IDAOGeneric<T>
{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory factory) 
	{
		this.sessionFactory = factory;
	}
	
	protected Session openSession()
	{
		return sessionFactory.openSession();
	}

	@Override
	public void create(T entity)
	{
		Session session = this.openSession();
		Transaction tx = session.beginTransaction();
        session.persist(entity);
        tx.commit();
        session.close();
	}

	@Override
	public void update(T entity)
	{
		Session session = this.openSession();
		Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
	}

	@Override
	public void delete(T entity)
	{
		Session session = this.openSession();
		Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
	}

	@SuppressWarnings("unchecked")
	protected List getAll(Class<T> clazz)
	{
		Session session = this.openSession();
        List<T> all = null;
        Query query = session.createQuery("from " + clazz.getName());
        all = query.list();
		session.close();
        return all;
	}

	@SuppressWarnings("unchecked")
	protected T get(Class<T> clazz, int id)
	{
		Session session = this.openSession();
		T t = null;
        t = (T) session.get(clazz, id);
		session.close();
        return t;
	}

}
