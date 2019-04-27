package com.graduate.service;

import com.graduate.bean.JobLevelBean;
import com.graduate.mapper.JobLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  18:54
 * @Description：<描述>
 */
@Service
@Transactional
public class JobLevelService {
    @Autowired
    JobLevelMapper jobLevelMapper;

    public int addJobLevel(JobLevelBean jobLevelBean) {
        if (jobLevelMapper.getJobLevelByName(jobLevelBean.getName()) != null) {
            return -1;
        }
        Timestamp time2 = new Timestamp(new Date().getTime());
        jobLevelBean.setCreateDate(time2);
        return jobLevelMapper.addJobLevel(jobLevelBean);
    }

    public List<JobLevelBean> getAllJobLevels() {
        return jobLevelMapper.getAllJobLevels();
    }

    public boolean deleteJobLevelById(String ids) {
        String[] split = ids.split(",");
        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }
        return jobLevelMapper.deleteJobLevelById(ints) == split.length;
    }

    public int updateJobLevel(JobLevelBean jobLevelBean) {
        jobLevelBean.setEnabled(true);
        return jobLevelMapper.updateJobLevel(jobLevelBean);
    }
}

