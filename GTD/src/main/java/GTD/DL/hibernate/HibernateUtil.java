/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.DL.hibernate;

//import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.SessionFactory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author simon
 */
public class HibernateUtil
{
	private static final SessionFactory sessionFactory;
	
	static {
//		try {
//            // Create the SessionFactory from standard (hibernate.cfg.xml) 
//			// config file.
//			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//		} catch (Throwable ex) {
//			// Log the exception. 
//			System.err.println("Initial SessionFactory creation failed." + ex);
//			throw new ExceptionInInitializerError(ex);
//		}
		System.out.println("static start");
		try {
		// Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();
            System.out.println("Hibernate Annotation Configuration loaded");
             
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");
             
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
             
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}
	
//	@Bean
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
