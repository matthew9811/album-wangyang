package com.shengxi.wangyang.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.shengxi.wangyang.common.util.WeChatUtil;
import com.shengxi.wangyang.entity.vo.ApiResponse;
import com.shengxi.wangyang.mapper.CustomerDao;
import com.shengxi.wangyang.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 服务层实现类
 *
 * @author y
 * @version 1.1.0
 * @date 2020-02-19 14:40:18
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerDao customerDao;

    /**
     * 实现登录授权
     * 对微信授权发起请求
     * 获取唯一的openid
     * 保存openid到数据库
     *
     * @param jsCode 临时的jscode
     * @return ApiResponse 授权信息
     */
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

    @Override
    public void uploadFiles(String jsCode, MultipartFile[] uploadFiles) {
        System.out.println(uploadFiles);
        for (MultipartFile uploadFile : uploadFiles) {
            logger.info("文件的名称: {}",uploadFile.getName());
            logger.info("文件格式: {}",uploadFile.getContentType());
        }
    }
}
