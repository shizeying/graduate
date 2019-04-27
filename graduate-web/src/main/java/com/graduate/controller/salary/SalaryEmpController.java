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
 * @Date： Create in  2019-02-20  18:53
 * @Description：员工账套
 */
@Api(tags = "员工账套")
@RestController
@CrossOrigin
@RequestMapping("/salary/sobcfg")
public class SalaryEmpController {
    @Autowired
    SalaryService salaryService;
    @Autowired
    EmpService empService;

    @ApiOperation(notes = "修改", value = "修改")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateEmpSalary(Integer sid, Long eid) {
        if (salaryService.updateEmpSalary(sid, eid) == 1) {
            return RespBean.ok("修改成功!");
        }
        return RespBean.error("修改失败!");
    }

    @ApiOperation(notes = "查看", value = "查看")
    @RequestMapping(value = "/salaries", method = RequestMethod.GET)
    public List<SalaryBean> salaries() {
        return salaryService.getAllSalary();
    }

    @ApiOperation(notes = "分页", value = "分页")
    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public Map<String, Object> getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>();
        List<EmployeeBean> employeeByPage = empService.getEmployeeByPageShort(page, size);
        Long count = empService.getCountByKeywords("", null, null, null, null, null, null, null);
        map.put("emps", employeeByPage);
        map.put("count", count);
        return map;
    }
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Map<Object, Object> getPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Map<Object, Object> map = new HashMap<>();
        List<EmployeeBean> employeeByPageShort = empService.getPage(page, size);
        Long countEmpSal = salaryService.getCountEmpSal();
        map.put("emps", employeeByPageShort);
        map.put("count", countEmpSal);
        return map;
    }



}
