package sample.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

@Configuration
public class AppContext {

    @Autowired
    private HibernateUtil hibernateUtil;

    @Bean
    public SessionFactory sessionFactory() {
        return hibernateUtil.BuildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        return new HibernateTransactionManager(sessionFactory());
    }
}
