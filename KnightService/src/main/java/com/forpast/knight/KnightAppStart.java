package com.forpast.knight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Date 2016-12-19
 * Knight 启动类
 * @author PJL
 * @version V1.0
 */
@SpringBootApplication
public class KnightAppStart extends WebMvcConfigurerAdapter{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    public static void main(String[] args){
        SpringApplication.run(KnightAppStart.class, args);
    }

}
