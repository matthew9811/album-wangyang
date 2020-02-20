package com.shengxi.wangyang.service;


import com.shengxi.wangyang.entity.vo.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户服务层对外接口
 *
 * @author y
 * @version 1.0.0
 * @date 2020-02-16 22:46:47
 */
public interface CustomerService {


    /**
     * 利用jscode调用对应的api获取唯一的openid
     * 实现登录
     *
     * @param jsCode 临时的jscode
     */
    ApiResponse login(String jsCode);

    /**
     * 上传照片
     *
     * @param jsCode
     * @param uploadFiles 照片文件数组
     */
    void uploadFiles(String jsCode, MultipartFile[] uploadFiles);
}
