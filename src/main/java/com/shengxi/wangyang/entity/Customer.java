package com.shengxi.wangyang.entity;

import java.io.Serializable;

/**
 * customer
 * @author 
 */
public class Customer implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * å¾®ä¿¡æŽˆæƒæ ‡è¯†
     */
    private String openid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}