package kasei.web.mvc.controller.param3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Server2Server")
public class Server2Server {

    /** 服务器 到 服务器 之间的参数传递以下几种方式：
     * 转发拼接 URL：
     * 转发不拼接 URL：
     * 重定向拼接 URL：
     * 重定向不拼接 URL：
     * */

    /* 转发拼接 URL */
    @RequestMapping("/forwardSend1")
    public ModelAndView forwardSend1(String originalArg){
        String newArg = "abcd新添加的参数（不在原有请求内的参数）"; // 拼接 url 的方式传中文会出错
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:/mvc/Server2Server/forwardReceive1?newArg="+newArg);
        return mv;
    }
    @RequestMapping("/forwardReceive1")
    public void forwardReceive(String originalArg, String newArg){
        /* 接收方式：跟普通客户端传递参数一样，spring mvc 不管参数在 url 里还是在请求体里（post）都能绑定同名参数
         * @RequestParam 注解只能绑定客户端发过来的参数，无法绑定转发过程中添加的参数
         * */
        System.out.println(originalArg);
        System.out.println(newArg);
    }




    /* 转发不拼接 URL */
    @RequestMapping("/forwardSend2")
    public ModelAndView forwardSend2(String originalArg, HttpServletRequest request){
        String newArg = "abcd新添加的参数（不在原有请求内的参数）";
        request.setAttribute("newArg", newArg);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:/mvc/Server2Server/forwardReceive2");
        return mv;
    }
    @RequestMapping("/forwardReceive2")
    public void forwardReceive2(String originalArg, HttpServletRequest request){
        /* 接收方式：采用 HttpServletRequest 对象接收，其他接收方式接收不到传值 */
        System.out.println(originalArg);
        System.out.println((String)request.getAttribute("newArg"));
    }





    /* 重定向拼接 URL */
    @RequestMapping("/redirectSend1")
    public ModelAndView redirectSend1(String originalArg){
        String newArg = "abcd新添加的参数（不在原有请求内的参数）"; // 拼接 url 的方式传中文会出错
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/mvc/Server2Server/redirectReceive1?newArg="+newArg);
        return mv;
    }
    @RequestMapping("/redirectReceive1")
    public void redirectReceive(String originalArg, String newArg, Model model){
        System.out.println(originalArg);
        System.out.println(newArg);
    }






    /* 重定向自动拼接 URL */
    @RequestMapping("/redirectSend2")
    public ModelAndView redirectSend2(String originalArg, RedirectAttributes attr){
        String newArg = "abcd新添加的参数（不在原有请求内的参数）";
        ModelAndView mv = new ModelAndView();
        attr.addAttribute("newArg", newArg);
        mv.setViewName("redirect:/mvc/Server2Server/redirectReceive2");
        return mv;
    }
    @RequestMapping("/redirectReceive2")
    public void redirectReceive2(String originalArg, String newArg, Model model){
        System.out.println(originalArg);
        System.out.println(newArg);
    }





    /* 重定向不拼接 URL */
    @RequestMapping("/redirectSend3")
    public ModelAndView redirectSend3(String originalArg, RedirectAttributes attr){
        String newArg = "abcd新添加的参数（不在原有请求内的参数）";
        ModelAndView mv = new ModelAndView();
        attr.addFlashAttribute("newArg", newArg);
        mv.setViewName("redirect:/mvc/Server2Server/redirectReceive3");
        return mv;
    }
    @RequestMapping("/redirectReceive3")
    public void redirectReceive2(String originalArg, @ModelAttribute("newArg") String newArg){
        System.out.println(originalArg);
        System.out.println(newArg);
    }
}
