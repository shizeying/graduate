package com.graduate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.graduate.mapper")
@EnableCaching
@EnableSwagger2
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class GraduateWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GraduateWebApplication.class, args);
    }

    /**
     * 为了打包springboot项目
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }


}

