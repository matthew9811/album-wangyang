package com.shengxi.wangyang.common.config;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置cos
 *
 * @author y
 */
//@ConfigurationProperties(prefix = "cos.tengxun")
@Component
public class CosConfig {

    @Value("${cos.tengxun.accessKey}")
    private String accessKey;

    @Value("${cos.tengxun.secretKey}")
    private String secretKey;

    @Value("${cos.tengxun.bucket}")
    private String bucket;

    @Value("${cos.tengxun.bucketName}")
    private String bucketName;

    @Value("${cos.tengxun.path}")
    private String path;

    @Value("${cos.tengxun.prefix}")
    private String prefix;

    public COSClient getCosClient() {
        // 1 初始化用户身份信息(s ecretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = this.bucketName;
        return cosclient;
    }

    public String getBucketName() {
        return bucketName;
    }
}
