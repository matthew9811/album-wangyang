package com.shengxi.wangyang.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.shengxi.wangyang.common.config.CosConfig;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;

public class CosUtil {

    @Autowired
    static CosConfig cosConfig;
    static COSClient cosClient;

    /**
     * 直接文件上传
     *
     * @param uploadFile file 需要上传的文件
     * @return 返回对应的信息
     */
    public static String upload(File uploadFile) {
        init();
        PutObjectRequest putObjectRequest;
        try {
            putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), KeyUtil.getKey(), uploadFile);
            cosClient.putObject(putObjectRequest);
        } finally {
            cosClient.shutdown();
        }
        return putObjectRequest.getKey();
    }

    public static String upload(String bucketName, File uploadFile) {
        init();
        PutObjectRequest putObjectRequest;
        try {
            putObjectRequest = new PutObjectRequest(bucketName, KeyUtil.getKey(), uploadFile);
            cosClient.putObject(putObjectRequest);
        } finally {
            cosClient.shutdown();
        }
        return putObjectRequest.getKey();
    }


//    public static void download(String key) {
//        init();
//        GetObjectRequest getObjectRequest = new GetObjectRequest(cosConfig.getBucketName(), key);
//        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
//    }

    /**
     * 初始化配置
     */
    private static void init() {
        if (ObjectUtil.isNull(cosClient)) {
            synchronized (COSClient.class) {
                if (ObjectUtil.isNull(cosClient)) {
                    cosClient = cosConfig.getCosClient();
                }
            }
        }
    }

}
