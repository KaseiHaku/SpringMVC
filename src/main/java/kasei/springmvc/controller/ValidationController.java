package kasei.springmvc.controller;

import kasei.springmvc.pojo.ValidationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Slf4j
@Controller
@RequestMapping("/Validation")
@Validated
public class ValidationController {
    
    
    @RequestMapping(path={"/primitive"}, method = {RequestMethod.GET})
    public String primitive(@Validated @NotBlank String aa){
        log.atInfo().addArgument(()->aa).log("primitive: {}");
        return null;
    }

    @RequestMapping("/dto")
    public String dto(@Validated ValidationDTO dto){
        log.atInfo().addArgument(()->dto).log("dto: {}");
        return null;
    }

    @RequestMapping("/jsonDto")
    public String jsonDto(){
        return null;
    }



}
