package kasei.web.config;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import kasei.web.config.interceptor.MyInterceptor;
import kasei.web.config.interceptor.MyInterceptor2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.text.SimpleDateFormat;
import java.util.List;

/** TODO 该类用于配置所有 MVC 相关的配置 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kasei.web.controller"})
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            /**
             * 1. 假设项目部署路径为                             /SpringWeb
             * 2. 且 DispatcherServlet 映射的路径配置为          /mvc/*
             * 3. 且静态资源访问路径配置为                        /resources/**
             * 那么实际静态资源访问 URL 应该为                    https://localhost:8080/SpringWeb/mvc/resources/
             * */
            .addResourceHandler("/resources/**")
            .addResourceLocations("/public", "classpath:/static/")
            .setCachePeriod(31556926); // 配置浏览器对静态资源最大的缓存时间
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


    /** TODO 配置 content-type = multipart/form-data 格式的 POST 请求的解析器 */
    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();  // 该类需要在 Servlet 3.0+ 的容器中先注册一个 MultipartConfigElement
    }




    /** TODO 配置 HttpMessageConverter 消息转换器：用于读取 request body 和 写入 response body */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 如果这里没有配置任何转换器，那么会添加默认的一系列转换器，如果添加了转换器，那么默认的就失效了
    }
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
            .indentOutput(true)
            .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH：mm:ss"))
            .modulesToInstall(new ParameterNamesModule());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));

    }
}
