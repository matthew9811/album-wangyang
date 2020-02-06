package com.shengxi.wangyang.common.util;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.shengxi.wangyang.common.util.KeyUtil;
import java.io.File;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置cos
 *
 * @author y
 */
public class CosUtil {

    //    @Value("${cos.tengxun.accessKey}")
    private static String accessKey;

    //    @Value("${cos.tengxun.secretKey}")
    private static String secretKey;

    //    @Value("${cos.tengxun.bucket}")
    private static String bucket;

    //    @Value("${cos.tengxun.bucketName}")
    private static String bucketName;

    //    @Value("${cos.tengxun.path}")
    private static String path;

    //    @Value("${cos.tengxun.prefix}")
    private static String prefix;

    private static COSClient cosClient;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        accessKey = resourceBundle.getString("cos.tengxun.accessKey");
        secretKey = resourceBundle.getString("cos.tengxun.secretKey");
        bucket = resourceBundle.getString("cos.tengxun.bucket");
        bucketName = resourceBundle.getString("cos.tengxun.bucketName");
        path = resourceBundle.getString("cos.tengxun.path");
        prefix = resourceBundle.getString("os.tengxun.prefix");
        // 1 初始化用户身份信息(s ecretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));
        // 3 生成cos客户端
        cosClient = new COSClient(cred, clientConfig);
    }


    /**
     * 直接文件上传
     *
     * @param uploadFile file 需要上传的文件
     * @return 返回对应的信息
     */
    public static String upload(File uploadFile) {
        PutObjectRequest putObjectRequest;
        try {
            putObjectRequest = new PutObjectRequest(bucketName, KeyUtil.getKey(), uploadFile);
            cosClient.putObject(putObjectRequest);
        } finally {
            cosClient.shutdown();
        }
        return putObjectRequest.getKey();
    }

    public static String upload(String bucketName, File uploadFile) {
        PutObjectRequest putObjectRequest;
        try {
            putObjectRequest = new PutObjectRequest(bucketName, KeyUtil.getKey(), uploadFile);
            cosClient.putObject(putObjectRequest);
        } finally {
            cosClient.shutdown();
        }
        return putObjectRequest.getKey();
    }
}
