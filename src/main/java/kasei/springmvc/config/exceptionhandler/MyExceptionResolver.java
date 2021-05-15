package kasei.springmvc.config.exceptionhandler;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 以下 ExceptionResolver 执行顺序为 从上到下
 *  {@link ExceptionHandlerExceptionResolver} 的作用是：
 *      用于处理  {@code ExceptionHandler} 注解注释的异常处理类
 *  {@link ResponseStatusExceptionResolver} 的作用是:
 *      根据  {@link ResponseStatus} 的配置信息来设置 response status code
 *  {@link DefaultHandlerExceptionResolver} 的作用是:
 *      根据异常类型来设置 response status code
 * 
 * 自定义 HandlerExceptionResolver 有两种方式:
 *  1. implements HandlerExceptionResolver 接口
 *      绝对控制权，写起来麻烦
 *  2。extends AbstractHandlerExceptionResolver 抽象类
 *      该方式具有模板方法，可以配置哪些 HandlerMapping 可以被当前类处理
 */
@Component
public class MyExceptionResolver implements HandlerExceptionResolver {

    /**
     * 如果 异常在当前 resolver 中处理掉了
     *     1. 如果需要跳转到指定的 view ， 则返回一个 ModelAndView 对象
     *     2. 如果不需要跳转到指定的 view ，返回一个 空的 ModelAndView 对象，
     *        那么具体使用哪个 <error-page/> 将由 web.xml 的配置来决定，
     *        最终结果和 异常冒泡到 ServletContainer 的效果一致，
     *        唯一的不同是， ServletContainer 无法接收到具体的异常信息，
     *        即: <error-page>.<exception-type> 无法匹配到异常信息，因为异常已经被处理了
     * 如果 异常在当前 resolver 中没有处理掉
     *     返回 null ，表示调用 ResolverChain 中的后续 resolver 来处理异常
     *     如果当前 resolver 是最后一个，那么将异常冒泡到 servlet 容器中
     * 如果异常冒泡到 servlet 容器中，或者 response status = 4xx, 5xx;
     *     servlet 容器会使用 默认的错误页面（HTML格式） 进行展示，可以通过 web.xml 中的 <error-page> 标签自定义错误页面 
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return 
     */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) {

	    // 仅仅处理 MyExceptionResolverException 类型的异常
        if (ex instanceof MyExceptionResolverException) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("errorMyExceptionResolver.jsp"); // 转到错误页面
            return mv;
        }
        
        // 处理掉异常，但是通过 web.xml 中 <error-page/> 来决定使用哪个错误视图
        // return new ModelAndView();
        
        // 使用其他 HandlerExceptionResolver 继续处理
        return null;
	}

}
