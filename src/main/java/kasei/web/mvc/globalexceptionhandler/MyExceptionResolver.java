package kasei.web.mvc.globalexceptionhandler;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object obj,
			Exception ex) {
		
		ModelAndView mv = new ModelAndView();
		// arg3 :就是系统抛出的异常
		String message = null;
		if(ex instanceof CustomizeException) {
			message = ((CustomizeException)ex).getMessage();
			mv.setViewName("CustomError.jsp"); // 转到错误页面
		}else{
			message = "未知异常";
			mv.setViewName("error.jsp"); // 转到错误页面				
		}
		mv.addObject("message",message);		
		return mv;
	}

}
