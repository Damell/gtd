/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.DL.hibernate;

import GTD.DL.DLDAO.DAOActivity;
import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLDAO.DAOTask;
import org.hibernate.Session;
import GTD.DL.hibernate.HibernateUtil;

import GTD.DL.DLEntity.Action;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.ActivityState;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Task;
import java.util.List;

/**
 *
 * @author simon
 */
public class HibernateMain
{
	public static void main(String[] args)
	{
//		Task task = new Task();
//		task.setNazev("testNazev_2");
//
//		DAOTask dA = new DAOTask();
//		dA.setSessionFactory(HibernateUtil.getSessionFactory());
//		
////		//Get Session
////		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
////		//start transaction
////		session.beginTransaction();
//		//Save the Model object
////		session.save(task);
////		//Commit transaction
////		session.getTransaction().commit();
////		System.out.println("Task ID=" + task.getId());
//
//		dA.create(task);
//		
//		List<Task> all = dA.getAll();
//		System.out.println(all);
//		Task a1 = dA.get(1);
//		System.out.println(a1);
//		a1.setNazev("zmeneny nazev");
//		dA.update(a1);
//		Task a1AfterChange = dA.get(1);
//		System.out.println(a1AfterChange);
//		dA.delete(a1AfterChange);
//		all = dA.getAll();
//		System.out.println("After delete:\n" + all);
		
		
		// person
		
		Person person = new Person();
		person.setJmeno("testJmeno");
		person.setPrijmeni("testPrijmeni");
		person.setLogin("testLogin2");

		DAOPerson dA = new DAOPerson();
		dA.setSessionFactory(HibernateUtil.getSessionFactory());
		
//		//Get Session
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		//start transaction
//		session.beginTransaction();
		//Save the Model object
//		session.save(task);
//		//Commit transaction
//		session.getTransaction().commit();
//		System.out.println("Task ID=" + task.getId());

		dA.create(person);
		
		List<Person> all = dA.getAll();
		System.out.println(all);
//		Person a1 = dA.get(2);
//		System.out.println(a1);
//		a1.setJmeno("zmeneny nazev");
//		dA.update(a1);
//		Person a1AfterChange = dA.get(2);
//		System.out.println(a1AfterChange);
//		dA.delete(a1AfterChange);
//		all = dA.getAll();
//		System.out.println("After delete:\n" + all);
		
		DAOState ds = new DAOState();
		ds.setSessionFactory(HibernateUtil.getSessionFactory());
		ActivityState as = ds.getCinnostKeZpracovani();
		
		System.out.println(as);
		
		//terminate session factory, otherwise program won't end
		HibernateUtil.getSessionFactory().close();
	}
 
	
	
}
