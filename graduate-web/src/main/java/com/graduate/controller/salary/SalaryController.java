package com.graduate.controller.salary;

import com.graduate.bean.SalaryBean;
import com.graduate.bean.RespBean;
import com.graduate.service.EmpService;
import com.graduate.service.SalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工资账套配置
 */
@Api(tags = "工资账套配置")
@RestController
@CrossOrigin
@RequestMapping("/salary/sob")
public class SalaryController {
    @Autowired
    SalaryService salaryService;
    @Autowired
    EmpService empService;

    @ApiOperation(notes = "添加账套信息", value = "添加账套信息")
    @RequestMapping(value = "/salary", method = RequestMethod.POST)
    public RespBean addSalaryCfg(SalaryBean salaryBean) {
        if (salaryService.addSalary(salaryBean) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation(notes = "获取全部信息", value = "获取全部信息")
    @RequestMapping(value = "/salary", method = RequestMethod.GET)
    public List<SalaryBean> salaries() {
        return salaryService.getAllSalary();
    }

    @ApiOperation(notes = "更新工资账套", value = "更新工资账套")
    @RequestMapping(value = "/salary", method = RequestMethod.PUT)
    public RespBean updateSalary(SalaryBean salaryBean) {
        if (salaryService.updateSalary(salaryBean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(notes = "删除工资账套", value = "删除工资账套")
    @RequestMapping(value = "/salary/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteSalary(@PathVariable String ids) {
        if (salaryService.deleteSalary(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

}
