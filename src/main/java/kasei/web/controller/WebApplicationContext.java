package kasei.web.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/WebApplicationContext")
public class WebApplicationContext {

    @GetMapping
    public void getWebApplicationContext(HttpServletRequest request){
        ApplicationContext webAppCtx = (ApplicationContext)request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);

    }
}
