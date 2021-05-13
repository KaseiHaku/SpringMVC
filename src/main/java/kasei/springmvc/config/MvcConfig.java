package kasei.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/** 该类用于配置所有 MVC 相关的配置,
 * 也可以继承 WebMvcConfigurerAdapter 类，该类有默认实现，可以只定义自己感兴趣的配置 
 * */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kasei.springmvc.controller"})
public class MvcConfig implements WebMvcConfigurer {


    // /** 类型转换配置 */
    // @Override
    // public void addFormatters(FormatterRegistry registry) {
    //    
    // }
    //
    // /** 校验配置 */
    // @Override
    // public Validator getValidator() {
    //     return null;
    // }
    //
    // /** 拦截器配置 */
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    //     registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
    // }
    //
    //
    // /** 请求类型配置 */
    // @Override
    // public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    //    
    // }

    // /** 消息转换：配置 HttpMessageConverter 消息转换器：用于读取 request body 和 写入 response body */
    // @Override
    // public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //     // 如果这里没有配置任何转换器，那么会添加默认的一系列转换器，如果添加了转换器，那么默认的就失效了
    // }

    /** 页面控制器: ParameterizableViewController 的快捷方式
     * 该配置的路径直接跳过 JavaController 的逻辑，直接返回 view
     *  */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
    }

    /** 页面解析器配置 */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // html 视图解析器: 不需要配，直接在controller 里面转发或者重定向即可
        // JSON 视图解析器: 
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        // jsp 视图解析器，注意  InternalResourceViewResolver 视图解析器优先级必须最低，否则其他解析器会被拦截
        registry.jsp("/WEB-INF/jsp/",".jsp");
    }
    
    
    
    
    // /** 静态资源配置 */
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     registry
    //         /**
    //          * 1. 假设项目部署路径为                             /SpringWeb
    //          * 2. 且 DispatcherServlet 映射的路径配置为          /mvc/*
    //          * 3. 且静态资源访问路径配置为                        /resources/**
    //          * 那么实际静态资源访问 URL 应该为                    https://localhost:8080/SpringWeb/mvc/resources/
    //          * */
    //         .addResourceHandler("/resources/**")
    //         .addResourceLocations("classpath:/static/")
    //         .setCachePeriod(31556926); // 配置浏览器对静态资源最大的缓存时间，Last-Modified 返回 304 状态码
    //
    //     registry
    //         .addResourceHandler("/resources/**")
    //         .addResourceLocations("/public/")
    //         .resourceChain(true)
    //         .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    // }
    //
    // /** 默认 Servlet 配置 */
    // @Override
    // public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    //     configurer.enable();
    // }
    //
    // /** 路径匹配配置 */
    // @Override
    // public void configurePathMatch(PathMatchConfigurer configurer) {
    //    
    // }
    //
    //
    // @Override
    // public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    //    
    // }
    //
    // @Override
    // public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    //
    // }
    //
    // @Override
    // public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
    //
    // }
    //
    // @Override
    // public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    //
    // }
    //
    // @Override
    // public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    //
    // }
    //
    // @Override
    // public MessageCodesResolver getMessageCodesResolver() {
    //     return null;
    // }

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
