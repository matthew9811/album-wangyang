package com.shengxi.wangyang.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * album
 *
 * @author
 */
@Data
public class Album implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 相册名称
     */
    private String albumName;
}