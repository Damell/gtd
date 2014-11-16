/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.DL.hibernate;

import GTD.DL.DLDAO.DAOActivity;
import GTD.DL.DLDAO.DAOContext;
import GTD.DL.DLDAO.DAOException;
import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DAOProject;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLDAO.DAOTask;
import org.hibernate.Session;
import GTD.DL.hibernate.HibernateUtil;

import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.ActivityState;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.PersonState;
import GTD.DL.DLEntity.Project;
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
		
		
//		DAOPerson daoPerson = new DAOPerson();
//		daoPerson.setSessionFactory(HibernateUtil.getSessionFactory());
//		DAOState daoState = new DAOState();
//		daoState.setSessionFactory(HibernateUtil.getSessionFactory());
//		DAOActivity daoActivity = new DAOActivity();
//		daoActivity.setSessionFactory(HibernateUtil.getSessionFactory());
//		DAOContext daoContext = new DAOContext();
//		daoContext.setSessionFactory(HibernateUtil.getSessionFactory());
//		DAOProject daoProject = new DAOProject();
//		daoProject.setSessionFactory(HibernateUtil.getSessionFactory());
//		DAOTask daoTask = new DAOTask();
//		daoTask.setSessionFactory(HibernateUtil.getSessionFactory());
//		
//		// person
//		
//		Person person = new Person();
//		person.setJmeno("Pepa");
//		person.setPrijmeni("Vomáčka");
//		person.setLogin("testLogin");
//		PersonState state = daoState.getOsobaAktivni();
//		person.setStav(state);
//
//		Person person2 = new Person();
//		person2.setJmeno("Franta");
//		person2.setPrijmeni("Votýpka");
//		person2.setLogin("testLogin");
//		person2.setStav(state);
//
//		try {
//			daoPerson.create(person);
//			System.out.println("person1 created");
//			Person dbPerson = daoPerson.getOsoba("testLogin");
//			System.out.println("dbPerson: " + dbPerson);
//			daoPerson.create(person2);
//			
//		} catch (DAOException e) {
//			System.out.println("!!! Catch !!! : " + e.getMessage());
//		}
//		
//		// activity
//		
//		ActivityState as = daoState.getCinnostKeZpracovani();
//		Activity activity = new Activity("aktivity OK", null, person, as);
//		Activity activityWithoutState = new Activity("aktivity bez stavu", null, person, null);
//		
//		try {
//			daoActivity.create(activity);
//			System.out.println("activity created");
//			List<Activity> actList = daoActivity.getCinnostiOsoby(person);
//			System.out.println("Pepa's activities:\n" + actList);
//			daoActivity.create(activityWithoutState);
//		} catch (DAOException e) {
//			System.out.println("!!! Catch !!! : " + e.getMessage());
//		}
//		
//		// context
//		
//		Context ctx = new Context("test kontext", person);
//		Context ctxWithoutOwner = new Context("kontext bez vlastnika", null);
//		
//		try {
//			daoContext.create(ctx);
//			System.out.println("context created");
//			List<Context> ctxList = daoContext.getKontextyOsoby(person);
//			System.out.println("Pepa's contexts:\n" + ctxList);
//			daoContext.create(ctxWithoutOwner);
//		} catch (DAOException e) {
//			System.out.println("!!! Catch !!! : " + e.getMessage());
//		}
//		
//		// Task
		
		
		
		//terminate session factory, otherwise program won't end
		HibernateUtil.getSessionFactory().close();
		
		System.out.println("Jo!");
	}
 
	
	
}
