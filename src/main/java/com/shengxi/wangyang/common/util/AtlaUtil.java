package com.shengxi.wangyang.common.util;


import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 经纬度逆转为地址
 * 高德例子：https://restapi.amap.com/v3/geocode/regeo?
 * output=xml&location=116.310003,39.991957&key=<用户的key>&radius=1000&extensions=all
 * 百度例子：http://api.map.baidu.com/reverse_geocoding/v3/?ak=您的ak
 * &output=json&coordtype=wgs84ll&location=31.225696563611,121.49884033194
 */
@Data
public class AtlaUtil {
    private static final Logger logger = LoggerFactory.getLogger(AtlaUtil.class);

    //    经纬度坐标
    /**
     * 传入内容规则：经度在前，纬度在后，经纬度间以“,”分割，
     * 经纬度小数点后不要超过 6 位。如果需要解析多个经纬度的话，
     * 请用"|"进行间隔，并且将 batch 参数设置为 true，最多支持传入 20 对坐标点。
     * 每对点坐标之间用"|"分割。
     */
    private static String location;

    private static String mapUrl;

    private static String key;

    private static boolean batch;


    static {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        Properties properties = yaml.getObject();
        key = (String) properties.get("atla.key");
        batch = (Boolean) properties.get("atla.batch");
        mapUrl = (String) properties.get("atla.url");
    }

    /**
     * 传递一个坐标，利用逗号进行分割，实现经纬度逆转为参考地址
     *
     * @param location string x,y
     * @return string 参考地址
     */
    public static Object getLocalAddress(String location) {
        String url = mapUrl.concat("key=" + key + "&" + "location=" + location);

        return (LinkedHashMap)HttpUtil.getRequestForUrl(url);
    }


}

