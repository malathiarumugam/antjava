package sample.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import sample.domains.Event;
import sample.repositories.EventsRepository;

import java.util.Date;

@Service
public class HelloService {

    @Autowired
    private EventsRepository eventsRepository;

    public String GetHelloText(){
        Event event = new Event();
        event.setDate(new Date());
        event.setTitle("title");
        eventsRepository.AddEvents(event);
        return "Hello Spring MVC Framework! this is message from service";
    }
}
