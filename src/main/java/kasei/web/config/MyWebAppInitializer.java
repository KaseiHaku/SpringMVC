package kasei.web.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/** TODO 使用 SPI 实现自动装配 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    /** TODO RootConfigClasses 用于配置 Spring 使用到的 App IOC 容器的配置类
     * 即: ApplicationContext
     * 主要包含 @Service @Repository
     * */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SpringConfig.class };
    }

    /** TODO ServletConfigClasses 用于配置 MVC 使用到的 Web IOC 容器的配置类
     * 即: WebApplicationContext     该容器是 Spring IOC 容器的子容器
     * 只包含：@Controller
     * */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { MvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/mvc/**" };
    }

    /** TODO 配置过滤器 */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {new CharacterEncodingFilter() };
    }


    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        /** TODO 文件上传限制配置 */
        // Optionally also set maxFileSize, maxRequestSize, fileSizeThreshold
        registration.setMultipartConfig(new MultipartConfigElement("/tmp"));
        /** TODO 日志中显示登陆参数 */
        registration.setInitParameter("enableLoggingRequestDetails", "true");
    }

}
