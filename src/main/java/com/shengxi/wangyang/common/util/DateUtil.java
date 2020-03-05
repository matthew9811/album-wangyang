package com.shengxi.wangyang.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author y
 */
public class DateUtil {
    public static Boolean compare_date(Date DATE1, Date DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-M-d");
        try {
            Date dt1 = df.parse(df.format(DATE1));
            Date dt2 = df.parse(df.format(DATE2));
            if (dt1.getTime() > dt2.getTime()) {
                return true;
            } else if (dt1.getTime() < dt2.getTime()) {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }
}
