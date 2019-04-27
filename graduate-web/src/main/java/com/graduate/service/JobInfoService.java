package com.graduate.service;


import com.graduate.bean.JobInfoBean;
import com.graduate.mapper.JobInfoMapper;
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
public class JobInfoService {
    @Autowired
    JobInfoMapper jobInfoMapper;


    /**
     * 根据id获取信息
     *
     * @param id
     * @return
     */
    public List<JobInfoBean> getSelect(Long id) {
        return jobInfoMapper.getSelect(id);
    }

    public Long getCount() {
        return jobInfoMapper.getCount();
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    public boolean deleteJobInfoById(String ids) {
        String[] split = ids.split(",");

        int[] ints = new int[split.length];

        for (int i = 0; i < split.length; i++) {

            ints[i] = Integer.parseInt(split[i]);

        }
        return jobInfoMapper.deleteJobInfoById(ints) == split.length;
    }

    /**
     * 查看
     *
     * @param page
     * @param size
     * @return
     */
    public List<JobInfoBean> getJobInfoByPageShort(Integer page, Integer size) {
        int start = (page - 1) * size;
        return jobInfoMapper.getJobInfoByPageShort(start, size);
    }

    /**
     * 添加
     *
     * @param jobInfoBeans
     * @return
     */
    public int getInsert(JobInfoBean jobInfoBeans) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date start = df.parse(df.format(new Date()));
        Date end = df.parse(df.format(jobInfoBeans.getCreateDate()));
        long se = end.getTime() - start.getTime();
        if (se >= 0 && jobInfoBeans.getDid() != null) {
            return jobInfoMapper.getInsert(jobInfoBeans);
        }
        return -1;
    }

    /**
     * 更新
     *
     * @param jobInfoBean
     * @return
     */
    public int updateJobInfo(JobInfoBean jobInfoBean) {
        return jobInfoMapper.updateJobInfo(jobInfoBean);
    }
}
