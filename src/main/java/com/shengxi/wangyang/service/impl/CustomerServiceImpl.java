package com.shengxi.wangyang.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.shengxi.wangyang.common.util.WeChatUtil;
import com.shengxi.wangyang.entity.vo.ApiResponse;
import com.shengxi.wangyang.mapper.CustomerDao;
import com.shengxi.wangyang.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public ApiResponse login(String jsCode) {
        JSONObject loginSession = WeChatUtil.getLoginSession(jsCode);
        String openid = loginSession.getStr("openid");
        if (ObjectUtil.isNotNull(openid)) {
            int num = customerDao.insert(openid);
            if (num > 0) {
                return ApiResponse.ofMessage(200, openid);
            }
        }
        return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
    }
}
