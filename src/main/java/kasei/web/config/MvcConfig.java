package kasei.web.config;

import kasei.web.config.interceptor.MyInterceptor;
import kasei.web.config.interceptor.MyInterceptor2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kasei.web.controller"})
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
            .addResourceLocations("/public", "classpath:/static/")
            .setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // html 视图解析器 :不需要配，直接在controller 里面转发或者重定向即可
        // jsp 视图解析器，注意  InternalResourceViewResolver 视图解析器优先级必须最低，否则其他解析器会被拦截
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.jsp();
    }
}
