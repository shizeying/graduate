package com.graduate.controller.jobInfo;


import com.graduate.bean.DepartmentBean;
import com.graduate.bean.JobBasicBean;
import com.graduate.bean.RespBean;
import com.graduate.service.DepartmentService;
import com.graduate.service.JobBasicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-20  18:53
 * @Description：应聘个人信息
 */
@Api(tags = "应聘个人信息")
@RestController
@CrossOrigin
@RequestMapping("/jobInfo/basic")
public class InfoTableController {
    @Autowired
    JobBasicService jobBasicService;
    @Autowired
    DepartmentService departmentService;
    /**
     * 更新表操作
     *
     * @param jobBasicBean
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBean updateJobBasic(JobBasicBean jobBasicBean) {
        int result = jobBasicService.updateJobBasic(jobBasicBean);
        if (result == 1) {
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
    @RequestMapping(value = "/jobBasicPage", method = RequestMethod.GET)
    public Map<Object, Object> getEmployeeByPageShort(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Map<Object, Object> map = new HashMap<>();
        List<JobBasicBean> employeeByPageShort = jobBasicService.getEmployeeByPageShort(page, size);
        Long count = jobBasicService.getCount();
        map.put("employeeByPageShort",employeeByPageShort);
        map.put("count",count);
        return map;
    }

    /**
     * 删除信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteJobBasicById(@PathVariable String ids) {
        if (jobBasicService.deleteJobBasicById(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    /**
     * 添加
     * @param jobBasicBean
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBean getInsert( JobBasicBean jobBasicBean) throws ParseException {

            if (jobBasicService.getInsert(jobBasicBean)==1){
                return RespBean.ok("添加成功");
            }

        return RespBean.error("请认真填写面试人相关信息和面试时间");
    }

    @RequestMapping(value = "/AllDeps", method = RequestMethod.GET)
    public List<DepartmentBean> getAllDeps() {
        return departmentService.getAllDeps();
    }
}
