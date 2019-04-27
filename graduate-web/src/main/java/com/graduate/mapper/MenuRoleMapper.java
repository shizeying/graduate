package com.graduate.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-10  19:26
 * @Description：
 */
@Component
public interface MenuRoleMapper {
    int deleteMenuByRid(@Param("rid") Long rid);

    int addMenu(@Param("rid") Long rid, @Param("mid") Long mid);
}
