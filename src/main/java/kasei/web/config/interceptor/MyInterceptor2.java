package kasei.web.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor2 implements HandlerInterceptor{

	//在进入 Handler 方法之前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("第二个拦截器-----------Handler 执行前");
		//return false; //表示拦截，不继续往下执行		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	//在进入 Handler 方法之后，返回 ModelAndView 之前
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("第二个拦截器-----------Handler 执行中，返回 ModelAndView 之前");
		// 存放公用的数据，或统一制定视图
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
	//在 Handler 方法执行完成之后，统一的异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("第二个拦截器-----------Handler 执行后");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}
