package com.graduate.controller.salary;


import com.graduate.bean.EmployeeBean;

import com.graduate.bean.RespBean;
import com.graduate.bean.SalaryBean;
import com.graduate.service.EmpService;
import com.graduate.service.SalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-20  20:07
 * @Description：<描述>
 */
@Api(tags = "个人工资表")
@RestController
@CrossOrigin
@RequestMapping("/salary/search")
public class SalarySearchController {
    @Autowired
    EmpService empService;
    @Autowired
    SalaryService salaryService;

    /**
     * 个人工资表查看
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/empSalPage", method = RequestMethod.GET)
    public Map<Object, Object> getEmployeeByPageShort(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Map<Object, Object> map = new HashMap<>();
        List<EmployeeBean> employeeByPageShort = empService.getEmployeeByPageShort(page, size);
        Long countEmpSal = salaryService.getCountEmpSal();
        map.put("emps", employeeByPageShort);
        map.put("count", countEmpSal);
        return map;
    }


    /**
     * 获取工资表
     *
     * @return
     */
    @RequestMapping(value = "/salary", method = RequestMethod.GET)
    public List<SalaryBean> salaries() {
        return salaryService.getAllSalary();
    }

    /**
     * 通过workID获取员工信息
     *
     * @param workID
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public List<EmployeeBean> selectWorkID(String workID) {
        return empService.getWorkID(workID);
    }

    @RequestMapping(value = "/getSal", method = RequestMethod.GET)
    public List<EmployeeBean> getWorkIDSal(String workID) {
        return empService.getWorkIDSal(workID);
    }

    /**
     * 添加个人员工工资
     *
     * @param sid
     * @param eid
     * @return
     */
    @RequestMapping(value = "/insertEmpSalary", method = RequestMethod.PUT)
    public RespBean insertEmpSalary(Integer sid, Long eid) {
        if (empService.getEmpId(eid)==1){
            if (salaryService.getSidAndEid(eid, sid)==1){
                if (salaryService.insertEmpSalary(sid, eid) == 1) {
                    return RespBean.ok("添加个人工资成功");
                }
                return RespBean.error("添加个人工资失败");
            }
            return RespBean.error("该员工已有工资表,请勿重复添加");
        }
        return RespBean.error("该员工不存在,请确认重试");
    }

    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteSalary(@PathVariable String ids) {
        if (salaryService.deleteEmpSalary(ids) ) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
