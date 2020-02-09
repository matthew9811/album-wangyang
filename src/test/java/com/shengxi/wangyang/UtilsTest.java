package com.shengxi.wangyang;

import cn.hutool.core.io.FileTypeUtil;
import java.io.File;
import org.junit.Test;


public class UtilsTest {
    @Test
    public void testFileNameUtils(){
        File file = new File("D:/浏览器下载/斯嘉丽.jpg");
        System.out.println(FileTypeUtil.getType(file));
    }
}
