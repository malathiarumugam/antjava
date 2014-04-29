package sample.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sample.domains.Event;

@Configuration
public class AppContext {

    @Autowired
    private HibernateUtil hibernateUtil;

    @Bean
    public SessionFactory sessionFactory() {
        return hibernateUtil.BuildSessionFactory();
    }
}
