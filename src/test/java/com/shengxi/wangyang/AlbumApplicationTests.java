package com.shengxi.wangyang;

import cn.hutool.json.JSONObject;
import com.drew.imaging.ImageProcessingException;
import com.shengxi.wangyang.common.util.AtlaUtil;
import com.shengxi.wangyang.common.util.ExifUitl;
import com.shengxi.wangyang.common.util.WeChatUtil;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumApplicationTests {

    @Test
    public void contextLoads() {
    }

//
//    @Test
//    public void testUpload() {
//        File file = new File("D:/浏览器下载/斯嘉丽.jpg");
//        System.out.println(CosUtil.upload(file, ".".concat(FileTypeUtil.getType(file))));
//    }


    @Test
    public void testYaml() {
        try {
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            yaml.setResources(new ClassPathResource("application-tengxun.yml"));
            Properties properties = yaml.getObject();
            properties.forEach((k, v) -> System.out.println(k + " : " + v));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetAddress() {
        //纬度， 经度
        String localAddress = AtlaUtil.getLocalAddress("39.984154,116.307490");
//        System.out.println(((JSONObject)localAddress.get("result")).get("ad_info"));
        System.out.println(localAddress);
    }

    @Test
    public void testGetWechatApi() {
        JSONObject loginSession = WeChatUtil.getLoginSession("033HnHQ127txcT0f8QQ12JpoQ12HnHQz");
        System.out.println(loginSession);
    }

    @Test
    public void testExifUtil() throws ImageProcessingException, IOException {
        File file = new File("D:/浏览器下载/风景1.jpg");
        String[] strings = ExifUitl.readExif(file);
        for (String string : strings) {
            System.out.println(string);
        }


    }

}
