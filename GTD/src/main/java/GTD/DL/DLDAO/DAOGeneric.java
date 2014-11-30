/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.DL.DLDAO;

import GTD.DL.DLInterfaces.IDAOGeneric;
import GTD.DL.hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
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
	
	public DAOGeneric() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

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
		Session session = null;
		Transaction tx = null;
		try {
			session = this.openSession();
			tx = session.beginTransaction();
			session.persist(entity);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e, tx);
		} finally {
			if (session != null) session.close();
		}
	}

	@Override
	public void update(T entity)
	{
		Session session = null;
		Transaction tx = null;
		try {
			session = this.openSession();
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e, tx);
		} finally {
			if (session != null) session.close();
		}
	}

	@Override
	public void delete(T entity)
	{
		Session session = null;
		Transaction tx = null;
		try {
			session = this.openSession();
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e, tx);
		} finally {
			if (session != null) session.close();
		}
	}

	@SuppressWarnings("unchecked")
	protected List getAll(Class<T> clazz)
	{
		Session session = null;
		Transaction tx = null;
		List all = null;
		try {
			session = this.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from " + clazz.getName());
			all = query.list();
			tx.commit();
		} catch (HibernateException e) {
			handleException(e, tx);
		} finally {
			if (session != null) session.close();
		}
		return all;
	}

	@SuppressWarnings("unchecked")
	protected T get(Class<T> clazz, int id)
	{
		Session session = null;
		Transaction tx = null;
		T t = null;
		try {
			session = this.openSession();
			tx = session.beginTransaction();
			t = (T) session.get(clazz, id);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e, tx);
		} finally {
			if (session != null) session.close();
		}
        return t;
	}
	
	/**
	 * Resi vyjimky v DAO operacich - zrusi transakci a vyhodi vlastni vyjimku
	 * @param e
	 * @param tx 
	 */
	protected void handleException(HibernateException e, Transaction tx)
	{
		if (tx != null) tx.rollback();
		throw new DAOException(e.getMessage(), e);
	}

}
