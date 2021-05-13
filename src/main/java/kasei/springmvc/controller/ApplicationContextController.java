package kasei.springmvc.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/ApplicationContext")
public class ApplicationContextController {

    @GetMapping
    public List<Map<String, Object>> getServletContextInfo(HttpServletRequest request){

        List<Map<String, Object>> filters = new ArrayList<>();


        Map<String, ? extends FilterRegistration> filterRegistrations = request.getServletContext().getFilterRegistrations();
        filterRegistrations.forEach((key, val) -> {
            Map<String, Object> filter = new TreeMap<>();
            filter.put("filterName", key);
            filter.put("className", val.getClassName());
            filter.put("initParameters", val.getInitParameters());
            filter.put("servletNameMappings", val.getServletNameMappings());
            filter.put("urlPatternMappings", val.getUrlPatternMappings());
            filters.add(filter);
        });
        //filters.sort(((o1, o2) -> ((String)o1.get("filterName")).compareTo((String)o2.get("filterName"))));
        filters.sort(Comparator.comparing(item -> (String)item.get("filterName"), Comparator.comparing(String::toString)));
        return filters;
    }


    @GetMapping("/mvcApplicationContext")
    public void mvcApplicationContext(HttpServletRequest request){
        ApplicationContext webAppCtx = (ApplicationContext)request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    }

    @GetMapping("/springApplicationContext")
    public void springApplicationContext(){

    }

}
