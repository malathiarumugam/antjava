package sample.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sample.services.HelloService;

@Controller
@RequestMapping("/hello")
public class HelloWebController{

    private HelloService helloService;

    @Autowired
    public HelloWebController(HelloService helloService){
        this.helloService = helloService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", helloService.GetHelloText());
        return "hello";
    }

}
