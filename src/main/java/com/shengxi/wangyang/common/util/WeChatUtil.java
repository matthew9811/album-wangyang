package com.shengxi.wangyang.common.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

/**
 * 实现微信api调用
 *
 * @author y
 * @version 1.0.0
 * @date 2020-02-16 14:04:11
 */
public class WeChatUtil {

    private static Logger logger = LoggerFactory.getLogger(WeChatUtil.class);
    /**
     * appid
     */
    private static String appid;
    /**
     * 密钥
     */
    private static String secret;
    /**
     * 授权类型
     */
    private static String grantType;
    /**
     * api路径
     */
    private static String wechatApiUrl;

    static {
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(new ClassPathResource("application-tengxun.yml"));
        Properties properties = factoryBean.getObject();
        appid = properties.getProperty("tengxun.wechat.appid");
        secret = properties.getProperty("tengxun.wechat.secret");
        grantType = properties.getProperty("tengxun.wechat.grant_type");
        wechatApiUrl = properties.getProperty("tengxun.wechat.url");
    }

    /**
     * https://api.weixin.qq.com/sns/jscode2session?
     * appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
     *
     * @return
     */
    public static JSONObject getLoginSession(String jsCode) {
        String url = wechatApiUrl + "?appid=" + appid + "&" + "secret=" + secret + "&"
                + "js_code=" + jsCode + "&" + "gramt_type=" + grantType;
        logger.info("发起请求的url为：{}", url);
        HttpRequest get = HttpUtil.createGet(url);
        HttpResponse execute = get.execute();

        logger.info("响应的结果为: {}", execute);
        execute.close();
        return JSONUtil.parseObj(execute.body());
    }


}
