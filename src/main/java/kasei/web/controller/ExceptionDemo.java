package kasei.web.controller;

import kasei.web.config.globalexceptionhandler.CustomizeException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ExceptionDemo")
public class ExceptionDemo {
	
	@RequestMapping("/tty1")
	public String tty1() throws CustomizeException {
		
		Boolean bool = true;
		if(bool) {
			throw new CustomizeException("sql 异常！");
		} else {
			throw new NullPointerException();
		}
	}
}
