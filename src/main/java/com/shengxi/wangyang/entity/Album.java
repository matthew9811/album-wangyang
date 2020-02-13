package com.shengxi.wangyang.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * album
 * @author 
 */
public class Album implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * æ‹¥æœ‰è€…id
     */
    private Integer customerId;

    /**
     * åˆ›å»ºæ—¶é—´
     */
    private Date createTime;

    /**
     * æ›´æ–°æ—¶é—´(é»˜è®¤ä¸ºåˆ›å»ºæ—¶é—´)
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}