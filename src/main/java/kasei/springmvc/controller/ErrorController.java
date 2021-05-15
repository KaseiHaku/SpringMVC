package kasei.springmvc.controller;

import kasei.springmvc.config.exceptionhandler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/Error")
@ResponseBody
public class ErrorController {


    /** 以下是可以报指定错误的请求 */

    /** get     http://localhost:8089/mvc/Error/error404 */
    // @RequestMapping(path = {"/error404"})
    // public void error404(){
    //     log.atError().addArgument("").log("404 error demo");
    // }
    
    /** get     http://localhost:8089/mvc/Error/error405 
     * 该异常将被 {@link MyResponseEntityExceptionHandler} 捕获并处理
     * */
    @RequestMapping(path = {"/error405"}, method = RequestMethod.POST)
    public void error405(){
        log.atError().addArgument("").log("405 error demo");
    }

    /** get     http://localhost:8089/mvc/Error/error500 
     * 该异常没有任何 ControllerAdvice 和 HandlerExceptionResolver 能处理，
     * 直接冒泡到 Servlet 容器，通过 <error-page>.<exception-type> 处理
     * */
    @RequestMapping(path = {"/error500"})
    public void error500(){
        log.atError().addArgument("").log("500 error demo");
        throw new BizException("ErrorDemo500");
    }

    /** get     http://localhost:8089/mvc/Error/error501 
     * 因为该异常被 @ResponseStatus 注释了，所以该异常将被 {@link ResponseStatusExceptionResolver} 处理，
     * {@link ResponseStatusExceptionResolver} 仅仅设置了 response status，
     * 但是并没有设置 error view(即：返回的是一个 空的 ModelAndView)， 所以最终错误页面还是由 web.xml 的 <error-page> 来处理
     * */
    @RequestMapping(path = {"/error501"})
    public void error501(){
        log.atError().addArgument("").log("501 error demo");
        throw new Error501Exception("ErrorDemo501");
    }

    /** get     http://localhost:8089/mvc/Error/errorMyExceptionResolver 
     * 该异常将被 {@link MyExceptionResolver} 处理
     * */
    @RequestMapping(path = {"/errorMyExceptionResolver"})
    public void errorMyExceptionResolver(){
        log.atError().addArgument("").log("errorMyExceptionResolver error demo");
        throw new MyExceptionResolverException("ErrorDemoMyExceptionResolver");
    }
    
    /** 以下都是 错误视图，即：返回给前端的内容 */
    @RequestMapping("/errorView500")
    public Map<String, Object> errorView(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", request.getAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE));
        map.put("message", request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE));
        map.put("exception", request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE));
        map.put("exceptionType", request.getAttribute(WebUtils.ERROR_EXCEPTION_TYPE_ATTRIBUTE));
        map.put("servletName", request.getAttribute(WebUtils.ERROR_SERVLET_NAME_ATTRIBUTE));
        map.put("requestUri", request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE));
        return map;
    }
}
