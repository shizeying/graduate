package com.graduate.common;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-12  12:59
 * @Description：自定义时间格式转化
 */
public class DateConverter implements Converter<String, Date> {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String s) {
        if ("".equals(s) || s == null) {
            return null;
        }
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
