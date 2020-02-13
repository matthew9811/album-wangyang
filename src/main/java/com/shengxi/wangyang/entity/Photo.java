package com.shengxi.wangyang.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * photo
 * @author 
 */
public class Photo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * å›¾ç‰‡é“¾æŽ¥
     */
    private String link;

    /**
     * æ‹¥æœ‰è€…id
     */
    private Integer customerId;

    /**
     * ä¸Šä¼ æ—¶é—´
     */
    private Date uploadTime;

    /**
     * æ‹æ‘„åœ°å€
     */
    private String area;

    /**
     * æ‹æ‘„æ—¶é—´
     */
    private Date filmingTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getFilmingTime() {
        return filmingTime;
    }

    public void setFilmingTime(Date filmingTime) {
        this.filmingTime = filmingTime;
    }
}