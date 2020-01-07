package kasei.web.controller;

import kasei.web.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;

/** TODO 演示客户端请求数据 和 方法参数 的绑定 */
@Controller
@RequestMapping("/Client2Server")
public class Client2Server {

	/** springboot 默认绑定的参数：
	 * HttpServletRequest
	 * HttpServletResponse
	 * HttpSession
	 * Model/ModelMap：ModelMap是Model的实现 
	 * */
	@RequestMapping(value="/bind1.mvc")
	public ModelAndView bind1(
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session,
			Model model,
            RedirectAttributes attr) throws Exception{
		
		String bind1 = "默认绑定的类型有：HttpServletRequest、HttpServletResponse、HttpSession、Model/ModelMap";
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bind1", bind1);
		mv.setViewName("parameterShow.jsp");
		return mv;
	}
	
	/** 简单类型的参数绑定，全部采用包装类，严禁使用 int double bool 等*/
	@RequestMapping(value="/bind2.mvc")
	public ModelAndView bind2(
			@RequestParam(value="name") String myName, // 绑定前端参数名为 name 的参数到 myName 上
			Integer age,
			Double money) throws Exception{
		
		String bind2 = "简单类型的参数绑定：name="+myName+"//age="+age+"//money="+money;
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bind2", bind2);
		mv.setViewName("parameterShow.jsp");
		return mv;
	}

	/** bean 类型的参数绑定 */
	@RequestMapping(value="/bind3.mvc")
	public ModelAndView bind3(Person person) throws Exception{
		
		String bind3 = "bean 类型的参数绑定：name="+person.getName()+"//age="+person.getAge();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bind3", bind3);
		mv.setViewName("parameterShow.jsp");
		return mv;
	}
	
	/** 嵌套类型的 bean 参数绑定 */
	@RequestMapping(value="/bind4.mvc")
	public ModelAndView bind4(ClientData clientData) throws Exception{
		
		String bind4 = "嵌套类型的 bean 参数绑定：name="
					+clientData.getPerson().getName()
					+"//age="+clientData.getPerson().getAge();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bind4", bind4);
		mv.setViewName("parameterShow.jsp");
		return mv;
	}
	
	/** Array 绑定 */
	@RequestMapping(value="/bind5.mvc" )
	public ModelAndView bind5(String[] strArray) throws Exception{		
		String bind5 = "Array 绑定："+Arrays.toString(strArray);		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bind5", bind5);
		mv.setViewName("parameterShow.jsp");
		return mv;
	}
	
	/** List 绑定 */
	@RequestMapping(value="/bind6.mvc")
	public ModelAndView bind6(ClientData clientData) throws Exception{	
		
		String bind6 =  "List 参数绑定 :   list[0].name=" 
				+ clientData.getList().get(0).getName()
				+ "    list[1].name=" + clientData.getList().get(1).getName();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bind6", bind6);
		mv.setViewName("parameterShow.jsp");
		return mv;
	}
	
	/** Map 绑定 */
	@RequestMapping(value="/bind7.mvc")
	public ModelAndView bind7(ClientData clientData) throws Exception{	
		
		String bind7 =  "Map 参数绑定:   map['person1'].name=" 
				+ clientData.getMap().get("person1").getName()
				+ "    map['person2'].name=" + clientData.getMap().get("person2").getName();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("bind7", bind7);
		mv.setViewName("parameterShow.jsp");
		return mv;
	}
	
	/** json 数据转换: 只能用 8、9 不能直接传 js 对象，解析不了 */
	@RequestMapping(value="/bind8.mvc")
	@ResponseBody
	public Return2Client bind8(
			@RequestBody School school  //@RequestBody 把请求发送过来的 json 字符串转成 java 对象
			) throws Exception{	
		String bind8 =  "JSON 参数绑定1 ： " + school.toString();		
		System.out.println(bind8);
		Return2Client result = new Return2Client();
		result.setMsg("bind8:success");
		return result;
	}
		
	@RequestMapping(value="/bind9.mvc")
	@ResponseBody
	public Return2Client bind9(
			@RequestBody Map<String,Object> map  //@RequestBody 把请求发送过来的 json 字符串转成 java 对象
			) throws Exception{			
		String bind9 =  "JSON 参数绑定2 ：" + map.toString();
		System.out.println(bind9);
		Return2Client result = new Return2Client();
		result.setMsg("bind9:success");
		return result;
	}
	
	
	@RequestMapping(value="/bind10.mvc")
	@ResponseBody
	public Return2Client bind10(Student student) throws Exception{			
		String bind10 =  "JSON 参数绑定3 ：" + student.toString();
		System.out.println(bind10);
		Return2Client result = new Return2Client();
		result.setMsg("bind10:success");
		return result;
	}
	
	/** 自定义类型的参数绑定：即转换器 */
	@RequestMapping(value="/bind11.mvc")
	public ModelAndView bind11(Timestamp timestamp) throws Exception{			
		String bind11 =  "自定义参数绑定（自定义转换器）：" + timestamp.toString();
		ModelAndView mv = new ModelAndView();
		mv.addObject("bind11", bind11);
		mv.setViewName("parameterShow.jsp");
		return mv;
	}
}
