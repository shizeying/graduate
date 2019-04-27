package com.graduate.mapper;

import com.graduate.bean.DepartmentBean;
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
public interface DepartmentMapper {


    List<DepartmentBean> getDepByPid(Long pid);

    List<DepartmentBean> getAllDeps();
}
