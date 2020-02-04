package com.shengxi.wangyang;

import com.shengxi.wangyang.common.config.CosConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 启动主方法
 *
 * @author 汪洋
 * @version 1.0
 * @date 2020-01-27 20:15:14
 * @since 1.0
 */
@EnableWebMvc
@SpringBootApplication
//@EnableConfigurationProperties({CosConfig.class})
public class AlbumApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlbumApplication.class, args);
    }

}
