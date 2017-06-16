package com.erdem.samples;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
//            // Create the SessionFactory from hibernate.cfg.xml
//        	Configuration configuration = new Configuration();
//        	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
//        	builder.applySettings(configuration.getProperties());
//        	ServiceRegistry serviceRegistry = builder.build();
//            return configuration.configure().buildSessionFactory(serviceRegistry);
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
    	// Close caches and connection pools
    	getSessionFactory().close();
    }
    
    public static boolean save(User user){
    	Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        
	    session.close();  
	    return true;
    }
    
    public static List<User> getAll(){
    	Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaQuery<User> listQuery = session.getCriteriaBuilder().createQuery(User.class);
        listQuery.select(listQuery.from(User.class));
        
        List<User> resultList = session.createQuery(listQuery).getResultList();
        
	    session.close();
		return resultList;  
        
    }

}
