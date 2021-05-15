package kasei.springmvc.controller;

import kasei.springmvc.config.exceptionhandler.BizException;
import kasei.springmvc.config.exceptionhandler.Error501Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
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
    
    /** get     http://localhost:8089/mvc/Error/error405 */
    @RequestMapping(path = {"/error405"}, method = RequestMethod.POST)
    public void error405(){
        log.atError().addArgument("").log("405 error demo");
    }

    /** get     http://localhost:8089/mvc/Error/error500 */
    @RequestMapping(path = {"/error500"})
    public void error500(){
        log.atError().addArgument("").log("500 error demo");
        throw new BizException("ErrorDemo500");
    }

    /** get     http://localhost:8089/mvc/Error/error501 */
    @RequestMapping(path = {"/error501"})
    public void error501(){
        log.atError().addArgument("").log("501 error demo");
        throw new Error501Exception("ErrorDemo501");
    }
    
    /** 以下都是 错误视图，即：返回给前端的内容 */
    @RequestMapping("/errorView500")
    public Map<String, Object> errorView(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("reason", request.getAttribute("javax.servlet.error.message"));
        return map;
    }
}
