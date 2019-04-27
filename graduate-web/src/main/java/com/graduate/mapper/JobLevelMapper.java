package com.graduate.mapper;

import com.graduate.bean.JobLevelBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-10  19:26
 * @Description：
 */
@Component
public interface JobLevelMapper {

    JobLevelBean getJobLevelByName(String name);

    int addJobLevel(@Param("jobLevel") JobLevelBean jobLevel);

    List<JobLevelBean> getAllJobLevels();

    int deleteJobLevelById(@Param("ids") int[] ids);

    int updateJobLevel(JobLevelBean jobLevel);//完成
}
