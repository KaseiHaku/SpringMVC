package kasei.springboot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Error")
public class ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String error(){
        return "Error!!!";
    }
}
