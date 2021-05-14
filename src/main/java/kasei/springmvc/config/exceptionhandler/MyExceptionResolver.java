package kasei.springmvc.config.exceptionhandler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyExceptionResolver implements HandlerExceptionResolver {

    /**
     * 如果 异常在当前 resolver 中处理掉了
     *     如果需要跳转到指定的 view ， 则返回一个 ModelAndView 对象
     *     如果不需要跳转到指定的 view ，返回一个 空的 ModelAndView 对象
     * 如果 异常在当前 resolver 中没有处理掉
     *     返回 null ，表示调用 ResolverChain 中的后续 resolver 来处理异常
     *     如果当前 resolver 是最后一个，那么将异常冒泡到 servlet 容器中
     * 如果异常冒泡到 servlet 容器中，或者 resonse status = 4xx, 5xx;
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
		
		ModelAndView mv = new ModelAndView();
		// arg3 :就是系统抛出的异常
		String message = null;
		if(ex instanceof CustomizeException) {
			message = ((CustomizeException)ex).getMessage();
			mv.setViewName("customError.jsp"); // 转到错误页面
		}else{
			message = "未知异常";
			mv.setViewName("error.jsp"); // 转到错误页面				
		}
		mv.addObject("message",message);		
		return mv;
	}

}
