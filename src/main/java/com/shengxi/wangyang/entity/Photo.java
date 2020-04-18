package com.shengxi.wangyang.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * photo
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private String trait;

    private static final long serialVersionUID = 1L;
}