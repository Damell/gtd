/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.DL.hibernate;

import org.hibernate.Session;
import GTD.DL.hibernate.HibernateUtil;

import GTD.DL.DLEntity.Action;

/**
 *
 * @author simon
 */
public class HibernateMain
{
	public static void main(String[] args)
	{
		Action action = new Action();
		action.setNazev("testNazev_2");

		//Get Session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//start transaction
		session.beginTransaction();
		//Save the Model object
		session.save(action);
		//Commit transaction
		session.getTransaction().commit();
		System.out.println("Action ID=" + action.getId());

		//terminate session factory, otherwise program won't end
		HibernateUtil.getSessionFactory().close();
	}
 
	
	
}
