package com.shengxi.wangyang.common.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * @author y
 * 实现文件exif属性操作工具类
 * @version 1.0.0
 * @date 2020-02-17 21:46:30
 */
public class ExifUitl {

    public static String[] readExif(File file) throws ImageProcessingException, IOException {

        String[] array = new String[3];
        ImageMetadataReader.readMetadata(file)
                .getDirectories().forEach(v ->
                v.getTags().forEach(t -> {
                    System.out.println(t.getTagName() + " ： " + t.getDescription());
                    switch (t.getTagName()) {
                        //                    经度
                        case "GPS Longitude":
                            array[0] = t.getDescription();
                            break;
                        //                        纬度
                        case "GPS Latitude":
                            array[1] = t.getDescription();
                            break;
                        //                        拍摄时间
                        case "Date/Time Original":
                            array[2] = t.getDescription();
                        default:
                            break;
                    }
                })
        );
        return array;
    }

    /**
     * 经纬度格式  转换为  度分秒格式 ,如果需要的话可以调用该方法进行转换
     *
     * @param point 坐标点
     * @return
     */
    public static String pointToDegree(String point) {
        Double degree = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
        Double minute = Double.parseDouble(point.substring(point.indexOf("°") + 1, point.indexOf("'")).trim());
        Double second = Double.parseDouble(point.substring(point.indexOf("'") + 1, point.indexOf("\"")).trim());
        Double degreeStr = degree + minute / 60 + second / 60 / 60;
        return degree.toString();
    }
}
