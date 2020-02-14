package com.shengxi.wangyang.app;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主要的控制器
 *
 * @author y
 * @version 1.0.0
 * @date 2020-02-14 18:20:283
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String indexWeb() {
        return "index";
    }

}
