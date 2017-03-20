package com.example.zbj.baseframework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zbj on 2017/3/16.
 */

public class DateUtils {
    public String homeDateFormat(long date) {
        String sDate;
        Date d = new Date(date + 24 * 60 * 60 * 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        sDate = format.format(d);

        return sDate;
    }
}
