package com.shengxi.wangyang.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author y
 */
public class DateUtil {


    public static Date offsetDay(Date date, int day){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }


    public static Boolean compare_date(Date DATE1, Date DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-M-d");
        try {
            Date dt1 = df.parse(df.format(DATE1));
            Date dt2 = df.parse(df.format(DATE2));

            if (dt1.getTime() <= dt2.getTime()) {
                return false;
            } else if (dt1.getTime() > dt2.getTime()) {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }
}
