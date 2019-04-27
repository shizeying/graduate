package com.graduate.mapper;

import com.graduate.bean.EmployeeBean;
import com.graduate.bean.NationBean;
import com.graduate.bean.PoliticsStatusBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-10  19:26
 * @Description：
 */
@Component
public interface EmpMapper {

    EmployeeBean getEmpId(Long eid);

    List<NationBean> getAllNations();

    List<PoliticsStatusBean> getAllPolitics();


    List<EmployeeBean> getEmployeeByPageShort(@Param("start") int start, @Param("size") Integer size);

    List<EmployeeBean> getPage(@Param("start") int start, @Param("size") Integer size);

    EmployeeBean getMaxWorkID();

    List<EmployeeBean> getWorkID(String workID);

    EmployeeBean selectNameWork(String name, String workID);


    List<EmployeeBean> getWorkIDSal(String workID);


    int deleteEmpById(@Param("ids") int[] ids);

    List<EmployeeBean> getALLEmployee();

    List<EmployeeBean> getMember();

    List<EmployeeBean> getEmployeeByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("keywords") String keywords, @Param("politicId") Long politicId, @Param("nationId") Long nationId, @Param("posId") Long posId, @Param("jobLevelId") Long jobLevelId, @Param("engageForm") String engageForm, @Param("departmentId") Long departmentId, @Param("startBeginDate") Date startBeginDate, @Param("endBeginDate") Date endBeginDate);//无法调试

    Long getCountByKeywords(@Param("keywords") String keywords, @Param("politicId") Long politicId, @Param("nationId") Long nationId, @Param("posId") Long posId, @Param("jobLevelId") Long jobLevelId, @Param("engageForm") String engageForm, @Param("departmentId") Long departmentId, @Param("startBeginDate") Date startBeginDate, @Param("endBeginDate") Date endBeginDate);//无法解决

    int updateEmp(@Param("emp") EmployeeBean employee);



    int addEmp(EmployeeBean employee);//通过


}
