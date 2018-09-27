
package sim.persistence;


import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;


public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory()	{
		try	{
			AnnotationConfiguration an = new AnnotationConfiguration();
			an.configure("config/mysql_hibernate.cfg.xml");
			
			return an.buildSessionFactory();
		} catch(Throwable e)	{
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory()	{
		return sessionFactory;
	}

}
