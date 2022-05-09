package com.zz.time_zone;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class time_zone1 {

    @Test
    public void t1() throws ParseException {
        String updateTime = "2022-04-09 09:40:49";
        SimpleDateFormat sdf_UTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf_UTC.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat sdf_beijing = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf_beijing.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        Date utc_date = sdf_UTC.parse(updateTime);
        Date beijing_date = sdf_beijing.parse(updateTime);

        System.out.println(sdf_UTC.format(beijing_date));  //把 updateTime作为北京时间 求现在的UTC时间
        System.out.println(sdf_beijing.format(utc_date));  //把 updateTime作为UTC时间 求现在的北京时间


    }
}
