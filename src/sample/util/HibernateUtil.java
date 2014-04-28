package sample.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Service;
import sample.domains.Event;


@Service
public class HibernateUtil {

    public SessionFactory BuildSessionFactory(){
        try{
            return new AnnotationConfiguration()
                    .configure("/../hibernate.cfg.xml")
                    .addPackage("sample.domains") //add package if used.
                    .addAnnotatedClass(Event.class).
                    buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
