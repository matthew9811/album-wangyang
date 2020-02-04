package com.shengxi.wangyang;

import com.shengxi.wangyang.common.util.CosUtil;
import java.io.File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void testUpload() {
        File file = new File("D:/浏览器下载/斯嘉丽.jpg");
        System.out.println(CosUtil.upload(file));
    }

}
