package kasei.springmvc.config;


import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.nio.charset.StandardCharsets;


/** 使用 SPI 实现自动装配
 * 该类主要用于 Servlet 容器配置，例如 DispatcherServlet
 *
 * DispatcherServlet initialization parameters
 *      contextClass: 用于 WebApplicationContext 实例的类，例如 XmlWebApplicationContext
 *      contextConfigLocation: 创建 WebApplicationContext 实例用到的配置文件的位置，例如： spring-mvc.xml
 *      namespace: 跟 contextConfigLocation 的作用一样，也是用于找配置文件，但是该属性指定的 path 会以 WEB-INF/ 为根目录，默认为 [servlet-name]-servlet 即找 WEB-INF/[servlet-name]-servlet.xml
 *      throwExceptionIfNoHandlerFound: 当 request 没有找到对应的 handler 时，是否抛出 NoHandlerFoundException
 *                                      默认 false，这种情况下 DispatcherServlet 会设置返回头为 404 而不是抛出 NoHandlerFoundException 异常
 *                                      注意：如果同时配置了 默认的处理 servlet，那么没有 handler 的 request 都会发到 DefaultServlet 上，就不会返回 404 了
 * */
public class ServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {


    /** RootConfigClasses 用于配置 Spring 使用到的 App IOC 容器的配置类
     * 即: ApplicationContext
     * 主要包含 @Service @Repository
     * */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SpringConfig.class };
    }

    /** ServletConfigClasses 用于配置 MVC 使用到的 Web IOC 容器的配置类
     * 即: WebApplicationContext     该容器是 Spring IOC 容器的子容器
     * 只包含：@Controller
     * */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { MvcConfig.class };
    }

    /**
     * 用于配置 DispatcherServlet 映射的 url 路径
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        /* "/*":
         *      "/*" 会拦截所有 url 包括 jsp。会导致 view 路径解析失败，因为会重新跳转到 MVC 的 DispatcherServlet
         *      所以这里 url 不能配置成 /*
         * "/"：
         *      "/" 会拦截除了 jsp 以外所有的 url
         * */
        return new String[] { "/mvc/*" };  
    }

    /**  配置过滤器 */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {
            /* 该过滤器用于设置 request response 的字符编码格式 */
            new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true, true) 
        };
    }


    // @Override
    // protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    //     /**  文件上传限制配置 */
    //     // Optionally also set maxFileSize, maxRequestSize, fileSizeThreshold
    //     registration.setMultipartConfig(
    //         new MultipartConfigElement(
    //             "/tmp",             // location 上传文件保存路径
    //             1024*1024*10,       // maxFileSize 最大允许上传 n 个字节的文件
    //             2,              // maxRequestSize 最多一次允许上传 n 个文件
    //             1024*100        // fileSizeThreshold 超过 n 个字节的文件，需要生成临时文件，注意临时文件回自动删除
    //         )
    //     );
    //     /**  日志中显示登陆参数 */
    //     registration.setInitParameter("enableLoggingRequestDetails", "true");
    // }

    
    


}
