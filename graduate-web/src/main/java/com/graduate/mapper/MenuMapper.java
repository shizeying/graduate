package com.graduate.mapper;


import com.graduate.bean.MenuBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-10  19:26
 * @Description：
 */
@Component
public interface MenuMapper {


    List<MenuBean> getAllMenu();



    List<MenuBean> getMenusByHrId(@Param("id")Long[] hrId);

    @Select({"select mr.\"mid\" from hr_role h_r,menu_role mr where h_r.\"rid\"=mr.\"rid\" and h_r.\"hrid\"=#{hrId}"})
    List<Long> getHrId(Long hrid);

    List<MenuBean> menuTree();

    List<Long> getMenusByRid(Long rid);
}
