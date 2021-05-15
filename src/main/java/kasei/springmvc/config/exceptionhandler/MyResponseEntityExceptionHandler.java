package kasei.springmvc.config.exceptionhandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/** 
 * @ControllerAdvice 
 *  1. 只能捕获 MVC 跑出来的异常，无法捕获在 Servlet Container 层面产生的异常,
 *  2. 该 Bean 执行的优先级高于 HandlerExceptionResolver，该 ControllerAdvice 执行完，就不会执行 HandlerExceptionResolver 了
 * */
@RestControllerAdvice
public class MyResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

}
