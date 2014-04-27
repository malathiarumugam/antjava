package sample.services;


import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String GetHelloText(){
        return "Hello Spring MVC Framework! this is message from service";
    }
}
