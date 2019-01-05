package kasei.springboot.xml;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class A02ItemsController implements HttpRequestHandler{

	@Override
	public void handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		List<String> itemsList = new ArrayList<String>();
		itemsList.add("采用  HttpRequestHandlerAdapter 适配器");
		itemsList.add("小米");
		itemsList.add("iphone");
		itemsList.add("魅族");
		itemsList.add("OPPO");
		itemsList.add("Vivo");
		
		//设置模型数据
		arg0.setAttribute("itemList", itemsList);
		//设置转发的视图
		arg0.getRequestDispatcher("/WEB-INF/jsps/itemsList.jsp").forward(arg0, arg1);
	}	
}
