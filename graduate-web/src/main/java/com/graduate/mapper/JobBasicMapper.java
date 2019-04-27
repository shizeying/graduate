package com.graduate.mapper;

import com.graduate.bean.JobBasicBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-21  21:52
 * @Description：<描述>
 */
@Component
public interface JobBasicMapper {
    /**
     * 查看个人应聘信息
     *
     * @param start
     * @param size
     * @return
     */
    List<JobBasicBean> getJobBasicByPageShort(@Param("start") int start, @Param("size") Integer size);

    @Select({"SELECT count(*) from job_basic ,department where job_basic.\"did\"=department.\"id\""})
    Long getCount();

    /**
     * 添加
     *
     * @param jobBasicBeans
     * @return
     */
    int getInsert(JobBasicBean jobBasicBeans);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int deleteJobBasicById(@Param("ids") int[] ids);

    /**
     * 更新
     *
     * @param jobBasicBean
     * @return
     */
    int updateJobBasic(JobBasicBean jobBasicBean);


}
