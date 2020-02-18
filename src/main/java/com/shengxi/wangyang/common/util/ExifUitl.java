package com.shengxi.wangyang.common.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import java.io.File;
import java.io.IOException;

/**
 * @author y
 * 实现文件exif属性操作工具类
 * @version 1.0.0
 * @date 2020-02-17 21:46:30
 */
public class ExifUitl {

    public static void readExif(File file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        Iterable<Directory> directories = metadata.getDirectories();
        directories.forEach(v -> System.out.println(v));
    }

}
