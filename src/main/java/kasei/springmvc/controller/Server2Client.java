package kasei.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** TODO 演示服务器返回响应数据 或 转发到 View */
@Controller
//@ResponseBody // 如果该标签放在这里，那么表示该类中所有的方法都是直接给客户端返回数据的
@RequestMapping("/Server2Client")//定义该 controller的根路径
@SessionAttributes({"master", "wife"}) //将 ModelMap 中属性名字为 master, wife 的值再放入 session 中
public class Server2Client {
	
	
	@RequestMapping("/mv.mvc")
	public ModelAndView toJsp1() throws Exception{
		
		String data2jsp1 = "返回类型：  ModelAndView";
		
		//创建 ModelAndView 实例
		ModelAndView mv = new ModelAndView();
		
		//向域中添加数据，相当于request.setAttribute()
		mv.addObject("data2jsp1", data2jsp1);
		
		//指定视图
		
		//mv.setViewName("parameterShow.jsp"); // 从视图解析器中寻找匹配的视图	
		//mv.setViewName("forward:/html/aa.html"); // 转发要写客户端的全路径，localhost:8180/Springmvc/path	
		mv.setViewName("redirect:/html/aa.html");// 重定向
		return mv;
	}
	
	

	@RequestMapping("/string.mvc")
	public String toJsp2(Model model) throws Exception{
		
		String data2jsp2 = "返回类型：  String";
		model.addAttribute("data2jsp2", data2jsp2);
		
		return "parameterShow.jsp"; // 从视图解析器里面找视图
		//return "forward:/WEB-INF/view/jsp/parameterShow.jsp"; // 页面转发
		//return "redirect:/WEB-INF/view/jsp/parameterShow.jsp"; // 页面重定向
	}
	
	
	@RequestMapping(value="/responsebody.mvc",
			//consumes={"application/json","text/html"},// 指定请求内容格式符合这两个才让该方法处理
			produces="text/html;charset=UTF-8") // 指定返回数据的类型及编码格式
	@ResponseBody // 表示返回数据就在 return 中，当返回值为 对象时，加上此标签，返回对象自动转换成 json 数据格式，需要 jackson 的几个 jar 包
	public String toJsp3() throws Exception{		
		String data2jsp3 = "返回类型：  String ：直接在函数内返回数据给 Client";		
		return data2jsp3;
	}
	
	
	@RequestMapping("/void.mvc")
	public void toJsp4(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String data2jsp4 = "返回类型：  void";		
		//跟servlet里面完全一样
		request.setAttribute("data2jsp4", data2jsp4);
		
		// void 这种方式不能从视图解析器中找视图
		
		request.getRequestDispatcher("/WEB-INF/view/jsp/parameterShow.jsp").forward(request, response); // 转发		
		//response.sendRedirect("/WEB-INF/view/jsp/parameterShow.jsp"); // 重定向		
	}
	
}
