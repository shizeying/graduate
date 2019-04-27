package com.graduate.controller.jobInfo;


import com.graduate.bean.DepartmentBean;
import com.graduate.bean.JobInfoBean;
import com.graduate.bean.RespBean;
import com.graduate.service.DepartmentService;
import com.graduate.service.JobInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-20  18:49
 * @Description：招聘信息
 */
@Api(tags = "招聘信息")
@RestController
@CrossOrigin
@RequestMapping("/jobInfo/info")
public class JobInfoController {
    @Autowired
    JobInfoService jobInfoService;
    @Autowired
    DepartmentService departmentService;



    /**
     * 更新表操作
     *
     * @param jobInfoBean
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateJobInfo(JobInfoBean jobInfoBean) {
        if (jobInfoService.updateJobInfo(jobInfoBean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /**
     * 参加面试信息查询
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/jobInfoPage", method = RequestMethod.GET)
    public Map<Object, Object> getEmployeeByPageShort(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Map<Object, Object> map = new HashMap<>();
        List<JobInfoBean> jobInfoByPageShort = jobInfoService.getJobInfoByPageShort(page, size);
        Long count = jobInfoService.getCount();
        map.put("jobInfoByPageShort",jobInfoByPageShort);
        map.put("count",count);
        return map;
    }

    /**
     * 获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public List<JobInfoBean> getJobBasic(@PathVariable Long id) {
        return jobInfoService.getSelect(id);
    }

    @RequestMapping(value = "/AllDeps", method = RequestMethod.GET)
    public List<DepartmentBean> getAllDeps() {
        return departmentService.getAllDeps();
    }


    /**
     * 删除信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteJobInfoById(@PathVariable String ids) {
        if (jobInfoService.deleteJobInfoById(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    /**
     * 添加招聘信息
     *
     * @param jobInfoBeans
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespBean addJobInfo( JobInfoBean jobInfoBeans) throws ParseException {
        int result = jobInfoService.getInsert(jobInfoBeans);
        if (result == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败,确认填写信息是否正确!");
    }
}
