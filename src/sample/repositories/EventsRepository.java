package sample.repositories;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sample.domains.Event;
import sample.util.HibernateUtil;

import javax.annotation.Resource;

@Repository("EventsRepository")
public class EventsRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void AddEvents(Event event){
        Session session = sessionFactory.getCurrentSession();
//        Transaction transaction = session.beginTransaction();
        session.save(event);
//        transaction.commit();
    }


}
