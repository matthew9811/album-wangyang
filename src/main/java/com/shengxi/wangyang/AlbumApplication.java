package com.shengxi.wangyang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 启动主方法
 *
 * @author 汪洋
 * @version 1.0
 * @date 2020-01-27 20:15:14
 * @since 1.0
 */
@EnableWebMvc
@MapperScan("com.shengxi.wangyang.mapper")
@SpringBootApplication
public class AlbumApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlbumApplication.class, args);
    }

    /**
     * 配置页面的路径配置
     * 简单配置页面的路径前缀和后缀
     *
     * @return resolver InternalResourceViewResolver
     */
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        return resolver;
    }

}
