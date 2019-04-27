package com.graduate.mapper;

import com.graduate.bean.JobInfoBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-21  21:52
 * @Description：招聘信息
 */
@Component
public interface JobInfoMapper {


    @Select({"SELECT count(*) from job_info,department WHERE job_info.\"did\"=department.\"id\""})
    Long getCount();

    List<JobInfoBean> getSelect(Long id);


    /**
     * 查看部门招聘信息
     *
     * @param start
     * @param size
     * @return:
     */
    List<JobInfoBean> getJobInfoByPageShort(@Param("start") int start, @Param("size") Integer size);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int deleteJobInfoById(@Param("ids") int[] ids);

    /**
     * 更新
     *
     * @param jobInfoBean
     * @return
     */
    int updateJobInfo(JobInfoBean jobInfoBean);//待修改
    /**
     * 添加
     *
     * @param jobInfoBeans
     * @return
     */
    @Insert({" UPSERT INTO job_info( \"id\",\"did\",\"info\",\"create_date\") VALUES (NEXT VALUE FOR job_info_sequence ,#{did} ,#{info},#{createDate,jdbcType=TIMESTAMP})"})
    int  getInsert(JobInfoBean jobInfoBeans);
}
