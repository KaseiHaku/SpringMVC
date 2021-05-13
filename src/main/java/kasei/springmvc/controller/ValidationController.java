package kasei.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Controller
@RequestMapping("/Validation")
@Validated
public class ValidationController {
    
    
    @RequestMapping(path={"/primitive"}, method = {RequestMethod.GET})
    public String primitive(@Validated @NotBlank String aa){
        System.out.println(aa);
        return null;
    }

    @RequestMapping("/dto")
    public String dto(){
        return null;
    }

    @RequestMapping("/jsonDto")
    public String jsonDto(){
        return null;
    }



}
