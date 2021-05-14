package kasei.springmvc.config;

import kasei.springmvc.config.interceptor.MyInterceptor;
import kasei.springmvc.config.interceptor.MyInterceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

/** 该类用于配置所有 MVC 相关的配置,
 * 也可以继承 WebMvcConfigurerAdapter 类，该类有默认实现，可以只定义自己感兴趣的配置 
 * */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kasei.springmvc.controller"})
public class MvcConfig implements WebMvcConfigurer {

    

    /** 校验配置 */
    @Override
    public Validator getValidator() {
        return null;
    }
    /** 
     * 该 BeanPostProcessor 通过 AOP 的方式，
     * 将 @NotNull 等 JSR-303 校验规范定义的校验器(只对 JaveBean 生效)，
     * 扩展到对 方法参数 的校验，即：使 @NotNUll 在方法参数中生效，
     * */
    @Bean
    public MethodValidationPostProcessor validationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    /** 拦截器配置 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
    }
    

    /** 消息转换：配置 HttpMessageConverter 消息转换器：用于读取 request body 和 写入 response body */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 如果这里没有配置任何转换器，那么会添加默认的一系列转换器，如果添加了转换器，那么默认的就失效了
    }

    /** 页面控制器: ParameterizableViewController 的快捷方式
     * 该配置的路径直接跳过 JavaController 的逻辑，直接返回 view
     * 坑：
     *  这里配置的路径，虽然跳过 JavaController 的逻辑，但是还是会通过 ViewResolver 进行解析的
     *  所以这里的实际路径需要根据 ViewResolver 的配置来决定
     *  
     *  */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /* 该配置前端访问路径为: http://localhost:8089/mvc/ 
         * 该配置访问的后端文件地址: src/main/webapp/WEB-INF/jsp/mvcHome.jsp
         * */
        registry.addViewController("/").setViewName("mvcHome.jsp");
        registry.addViewController("/freemarkerTemplate.ftl").setViewName("freemarkerTemplate.ftl");
    }

    /** 页面解析器配置 */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // html 视图解析器: 不需要配，直接在controller 里面转发或者重定向即可
        // JSON 视图解析器: 
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        /* freemarker 视图解析器：不能和 jsp 同时使用 */
        registry.freeMarker();
        /* jsp 视图解析器:
         * 由于检查一个 jsp 文件是否存在的唯一方法就是 RequestDispatcher (请求分发)，即: 调用看看；
         * 但是 RequestDispatcher 只能调用一遍，且 InternalResourceViewResolver 总是返回一个视图，
         * 所以 InternalResourceViewResolver 视图解析器优先级必须最低，否则其他解析器会被拦截 
         * */
        // registry.jsp("/WEB-INF/jsp/",".jsp");
        registry.jsp("/WEB-INF/jsp/",""); // 后缀不要默认自带后缀，方便理解
    }
    /** 配合页面解析器中的 registry.freeMarker(); 使用 */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/freemarker");
        return configurer;
    }
    
    
    
    /** 静态资源配置 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            /* 1. 假设项目部署路径为                             /SpringMVC
             * 2. 且 DispatcherServlet 映射的路径配置为          /mvc/*
             * 3. 且静态资源访问路径配置为                        /resources/**
             * 那么实际静态资源访问 URL 应该为                    https://localhost:8080/SpringMVC/mvc/resources/
             * */
            .addResourceHandler("/resources/**")
            /* classpath == SpringMVC.war/WEB-INF/classes 目录
             * classpath:/static/ 不仅仅会包含当前项目的 static，还会包含三方 jar 包中的 static 目录 
             * 由于文件在 SpringMVC.war/WEB-INF 目录下，所以不能通过 URL 直接访问，必须经过 DispatcherServlet 才能访问
             * */
            .addResourceLocations("classpath:/static/")
            .setCachePeriod(31556926); // 配置浏览器对静态资源最大的缓存时间，Last-Modified 返回 304 状态码

        registry
            /* 
             * 那么实际静态资源访问 URL 应该为                    https://localhost:8080/SpringMVC/mvc/public/
             * */
            .addResourceHandler("/public/**")
            /* /public/  中的 / 代表的是 web app root 目录，即：当前项目的 webapp 目录 或者 打包后的 SpringMVC.war/ 根目录
             * 由于文件在 SpringMVC.war/ 目录下，所以还可以通过 https://localhost:8080/SpringMVC/public/ 直接访问
             * */
            .addResourceLocations("/public/")
            .resourceChain(true)
            /* addContentVersionStrategy("/**") 中的路径是相对于 addResourceHandler("/public/**") 的相对路径 */
            .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }

    /** 默认 Servlet 配置：转发未处理的请求到 servlet 容器中名为 default 的 servlet，
     * 用于处理 DispatcherServlet 映射路径配置成 "/" 时，Servlet 容器默认的静态资源路径被覆盖的问题
     * */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    

    /** 异常处理配置：HandlerExceptionResolver 
     * 该方法用于扩展默认配置，完全替换默认配置 override configureHandlerExceptionResolvers()
     * */
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        
    }
    
    // @Override
    // public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    //     Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
    //         .indentOutput(true)
    //         .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH：mm:ss"))
    //         .modulesToInstall(new ParameterNamesModule());
    //     converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    //
    // }


    /**  全局 CORS 配置 */
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //
    //     registry.addMapping("/api/**")
    //         .allowedOrigins("https://haku.kasei")
    //         .allowedMethods("PUT", "DELETE")
    //         .allowedHeaders("header1", "header2", "header3")
    //         .exposedHeaders("header1", "header2")
    //         .allowCredentials(true).maxAge(3600);
    //
    //     registry.addMapping("/api/**")
    //         .allowedOrigins("https://miku.kasei")
    //         .allowedMethods("PUT", "DELETE")
    //         .allowedHeaders("header1", "header2", "header3")
    //         .exposedHeaders("header1", "header2")
    //         .allowCredentials(true).maxAge(3600);
    //     // Add more mappings...
    // }





    /** 配置 content-type = multipart/form-data 格式的 POST 请求的解析器 */
    // @Bean
    // public MultipartResolver multipartResolver(){
    //     return new StandardServletMultipartResolver();  // 该类需要在 Servlet 3.0+ 的容器中先注册一个 MultipartConfigElement
    // }
}
