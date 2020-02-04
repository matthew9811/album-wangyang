package com.shengxi.wangyang.common.util;

import cn.hutool.core.lang.UUID;

/**
 * @author y
 */
public class KeyUtil {

    /**
     * 使用hutool生成一个唯一的id
     *
     * @return 返回没有-的id
     */
    public static String getKey() {
        return UUID.randomUUID().toString(true);
    }
}
