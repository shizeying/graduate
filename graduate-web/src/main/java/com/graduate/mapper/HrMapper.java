package com.graduate.mapper;

import com.graduate.bean.HrBean;
import com.graduate.bean.RoleBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-10  19:26
 * @Description：
 */
@Component
public interface HrMapper {
    HrBean loadUserByUsername(String username);


    List<HrBean> getAllHr(@Param("currentId") Long currentId);

    List<RoleBean> getRolesByHrId(Long id);

    int hrReg(@Param("username") String username, @Param("password") String password);//未测试

    List<HrBean> getHrsByKeywords(@Param("keywords") String keywords);//无模糊

    int updateHr(HrBean hrBean);//未测试


    int insertHr(String name, String address, String phone, String username, String password);


    int deleteRoleByHrId(Long hrId);

    int addRolesForHr(@Param("hrId") Long hrId, @Param("rid") Long rid);

    HrBean getHrById(Long hrId);

    int deleteHr(Long hrId);

}
