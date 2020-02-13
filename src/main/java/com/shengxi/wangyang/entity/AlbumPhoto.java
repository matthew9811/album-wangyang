package com.shengxi.wangyang.entity;

import java.io.Serializable;

/**
 * album_photo
 * @author 
 */
public class AlbumPhoto implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * ç…§ç‰‡id
     */
    private Integer photoId;

    /**
     * ç›¸å†Œid
     */
    private Integer albumId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }
}