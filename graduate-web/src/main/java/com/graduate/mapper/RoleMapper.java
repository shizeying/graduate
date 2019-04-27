package com.graduate.mapper;

import com.graduate.bean.RoleBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-10  19:26
 * @Description：
 */
@Component
public interface RoleMapper {
    List<RoleBean> roles();

    int addNewRole(@Param("role") String role, @Param("roleZh") String roleZh);

    int deleteRoleById(Long rid);
}
