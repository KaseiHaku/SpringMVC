package kasei.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ViewResolver")
public class ViewResolverController {
    
    @RequestMapping("/html")
    public void html(){
        
    }

    @RequestMapping("/jsp")
    public void jsp(){

    }

    @RequestMapping("/freemarker")
    public void freemarker(){

    }

    @RequestMapping("/json")
    public void json(){

    }
    
    
    
}
