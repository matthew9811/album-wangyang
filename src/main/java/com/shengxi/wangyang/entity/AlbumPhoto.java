package com.shengxi.wangyang.entity;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * album_photo
 *
 * @author
 */
@Data
public class AlbumPhoto implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 照片id
     */
    private Integer photoId;

    /**
     * 相册id
     */
    private Integer albumId;

    /**
     * 相册
     */
    private List<Photo> photos;
    /**
     * 相册名称
     */
    private String albumName;

}