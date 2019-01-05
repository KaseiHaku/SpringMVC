package kasei.springboot.xml;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

//springboot 第五步：Handler 编写
public class A01ItemsController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		
		List<String> itemsList = new ArrayList<String>();
		itemsList.add("采用  SimpleControllerHandlerAdapter  适配器");
		itemsList.add("小米");
		itemsList.add("iphone");
		itemsList.add("魅族");
		itemsList.add("OPPO");
		itemsList.add("Vivo");
		
		//返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//向域中添加数据，相当于request.setAttribute()
		modelAndView.addObject("itemList", itemsList);
		//指定视图
		modelAndView.setViewName("/WEB-INF/jsps/itemsList.jsp");
		return modelAndView;
	}
}
