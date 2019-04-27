package com.graduate.controller.salary;


import com.graduate.bean.DepartmentBean;
import com.graduate.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-20  20:07
 * @Description：<描述>
 */
@Api(tags = "部门")
@RestController
@CrossOrigin
@RequestMapping("/salary/table")
public class SalaryTableController {
    @Autowired
    DepartmentService departmentService;

    @ApiOperation(notes = "列出所有部门", value = "列出所有部门")
    @RequestMapping("/deps")
    public List<DepartmentBean> departments() {
        return departmentService.getAllDeps();
    }
}
