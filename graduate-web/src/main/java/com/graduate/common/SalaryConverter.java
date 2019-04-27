package com.graduate.common;

import com.graduate.bean.SalaryBean;
import org.springframework.core.convert.converter.Converter;


/**
 * @Author：史泽颖
 * @Date： Create in  2019-03-24  18:12
 * @Description：<描述>
 */
public class SalaryConverter implements Converter<String, SalaryBean> {
    @Override
    public SalaryBean convert(String s) {
        if ("".equals(s) || s==null){
            Object o=s;
            return (SalaryBean)o;
        }
        return null;
    }
}
