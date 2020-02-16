package com.shengxi.wangyang.app;


import com.shengxi.wangyang.entity.vo.ApiResponse;
import com.shengxi.wangyang.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author y
 */
@RequestMapping("/wechat")
@RestController
public class WeChatController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    @ResponseBody
    public ApiResponse login(@RequestParam String jsCode){
        return customerService.login(jsCode);
    }
}
