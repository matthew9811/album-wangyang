package com.shengxi.wangyang;


import cn.hutool.core.date.DateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

public class UtilTest {

    @Test
    public void testUtil1() throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        Date parse = simpleDateFormat.parse(format);
        System.out.println(format);
        int hour = DateUtil.hour(parse, true);
        System.out.println(hour);
    }
}
