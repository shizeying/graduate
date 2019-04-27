package com.graduate.service;

import com.graduate.bean.JobBasicBean;
import com.graduate.mapper.JobBasicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-21  23:22
 * @Description：<描述>
 */
@Transactional
@Service
public class JobBasicService {
    @Autowired
    JobBasicMapper jobBasicMapper;

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public boolean deleteJobBasicById(String ids) {
        String[] split = ids.split(",");
        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }
        return jobBasicMapper.deleteJobBasicById(ints) == ints.length;
    }

    public Long getCount() {
        return jobBasicMapper.getCount();
    }

    /**
     * 查看
     *
     * @param page
     * @param size
     * @return
     */
    public List<JobBasicBean> getEmployeeByPageShort(Integer page, Integer size) {
        int start = (page - 1) * size;
        return jobBasicMapper.getJobBasicByPageShort(start, size);
    }

    /**
     * 添加
     *
     * @param jobBasicBeans
     * @return
     */
    public int getInsert(JobBasicBean jobBasicBeans) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date start = df.parse(df.format(new Date()));
        Date end = df.parse(df.format(jobBasicBeans.getInterviewDate()));
        long se = end.getTime() - start.getTime();
        if (jobBasicBeans.getAge() > 18 && se >= 0 && jobBasicBeans.getPhone().length() == 11 && jobBasicBeans.getSparePhone().length() == 11 && jobBasicBeans.getDid() != null) {
            return jobBasicMapper.getInsert(jobBasicBeans);
        }
        return -1;
    }

    /**
     * 更新
     *
     * @param jobBasicBean
     * @return
     */
    public int updateJobBasic(JobBasicBean jobBasicBean) {
        return jobBasicMapper.updateJobBasic(jobBasicBean);
    }


}
