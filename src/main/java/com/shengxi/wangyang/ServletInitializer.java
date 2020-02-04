package com.shengxi.wangyang;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动配置类
 *
 * @author 汪洋
 * @date 2020-01-27 20:14:15
 * @see org.springframework.boot.web.servlet.support.SpringBootServletInitializer
 * @see org.springframework.web.WebApplicationInitializer
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AlbumApplication.class);
    }

}
