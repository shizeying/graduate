package com.graduate.mapper;

import com.graduate.bean.EmpSalaryBean;
import com.graduate.bean.SalaryBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
public interface SalaryMapper {

    int addSalary(@Param("salary") SalaryBean salary);

    List<SalaryBean> getAllSalary();

    EmpSalaryBean getSidAndEid(@Param("eid") Long eid, @Param("sid") Integer sid);

    Long getCountEmpSal();

    int updateSalary(@Param("salary") SalaryBean salary);//未通过


    int deleteSalary(@Param("ids") int[] ids);

    int deleteEmpSalary(@Param("ids") int[] ids);

    int deleteSalaryByEid(@Param("eid") Long eid);

    int addSidAndEid(@Param("sid") Integer sid, @Param("eid") Long eid);



}
