package com.graduate.config;


import com.graduate.common.DateConverter;
import com.graduate.common.SalaryConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.*;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-12  12:51
 * @Description： 拦截器配置
 */
@Configuration
@CrossOrigin
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 添加类型转换器和格式化器
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SalaryConverter());
        registry.addConverter(new DateConverter());
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/static/swagger/");
    }


    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //配置跨域请求，允许所有站点访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*","/login")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .maxAge(3600);
        registry.addMapping("/login").allowedOrigins("http://localhost:8085", "http://localhost:8082");
    }





}
