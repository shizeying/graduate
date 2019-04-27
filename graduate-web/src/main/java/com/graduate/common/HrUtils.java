package com.graduate.common;

import com.graduate.bean.HrBean;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-10  12:53
 * @Description：<描述>
 */
public class HrUtils {
    public static HrBean getCurrentHr() {
        return (HrBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
