package com.graduate.controller.system;

import com.graduate.bean.*;
import com.graduate.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  20:04
 * @Description：<描述>
 */
@Api(tags = "基本信息设置")
@RestController
@RequestMapping("/system/basic")
public class SystemBasicController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Autowired
    MenuRoleService menuRoleService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionService positionService;
    @Autowired
    JobLevelService jobLevelService;

    @ApiOperation(notes = "根据id删除角色", value = "根据id删除角色")
    @RequestMapping(value = "/role/{rid}", method = RequestMethod.DELETE)
    public RespBean deleteRole(@PathVariable Long rid) {
        if (roleService.deleteRoleById(rid) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(notes = "添加角色", value = "添加角色")
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public RespBean addNewRole(String role, String roleZh) {
        if (roleService.addNewRole(role, roleZh) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation(notes = "获取menuTree", value = "获取menuTree")
    @RequestMapping(value = "/menuTree/{rid}", method = RequestMethod.GET)
    public Map<String, Object> menuTree(@PathVariable Long rid) {
        Map<String, Object> map = new HashMap<>();
        List<MenuBean> menus = menuService.menuTree();
        map.put("menus", menus);
        List<Long> selMids = menuService.getMenusByRid(rid);
        map.put("mids", selMids);
        return map;
    }

    @ApiOperation(notes = "更新MenuRole", value = "更新MenuRole")
    @RequestMapping(value = "/updateMenuRole", method = RequestMethod.PUT)
    public RespBean updateMenuRole(Long rid, Long[] mids) {
        if (menuRoleService.updateMenuRole(rid, mids) == mids.length) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @ApiOperation(notes = "列出roles", value = "列出roles")
    @RequestMapping("/roles")
    public List<RoleBean> allRoles() {
        return roleService.roles();
    }



    @ApiOperation(notes = "根据id查询department", value = "根据id查询department")
    @RequestMapping(value = "/dep/{pid}", method = RequestMethod.GET)
    public List<DepartmentBean> getDepByPid(@PathVariable Long pid) {
        return departmentService.getDepByPid(pid);
    }

    @ApiOperation(notes = "获得departments", value = "获得departments")
    @RequestMapping(value = "/deps", method = RequestMethod.GET)
    public List<DepartmentBean> getAllDeps() {
        return departmentService.getAllDeps();
    }

    @ApiOperation(notes = "添加position", value = "添加position")
    @RequestMapping(value = "/position", method = RequestMethod.POST)
    public RespBean addPos(PositionBean pos) {
        int result = positionService.addPos(pos);
        if (result == 1) {
            return RespBean.ok("添加成功!");
        } else if (result == -1) {
            return RespBean.error("职位名重复，添加失败!");
        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation(notes = "列出positions", value = "列出positions")
    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public List<PositionBean> getAllPos() {
        return positionService.getAllPos();
    }

    @ApiOperation(notes = "删除修改positions", value = "删除修改positions")
    @RequestMapping("/position/{pids}")
    public RespBean deletePosById(@PathVariable String pids) {
        if (positionService.deletePosById(pids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(notes = "修改position", value = "修改position")
    @RequestMapping(value = "/position", method = RequestMethod.PUT)
    public RespBean updatePosById(PositionBean positionBean) {
        if (positionService.updatePosById(positionBean) == 1) {
            return RespBean.ok("修改成功!");
        }
        return RespBean.error("修改失败!");
    }

    @ApiOperation(notes = "添加joblevel", value = "添加joblevel")
    @RequestMapping(value = "/joblevel", method = RequestMethod.POST)
    public RespBean addJobLevel(JobLevelBean jobLevelBean) {
        int result = jobLevelService.addJobLevel(jobLevelBean);
        if (result == 1) {
            return RespBean.ok("添加成功!");
        } else if (result == -1) {
            return RespBean.error("职称名重复，添加失败!");
        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation(notes = "获取所有joblevels", value = "获取所有joblevels")
    @RequestMapping(value = "/joblevels", method = RequestMethod.GET)
    public List<JobLevelBean> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @ApiOperation(notes = "删除joblevel", value = "删除joblevel")
    @RequestMapping(value = "/joblevel/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteJobLevelById(@PathVariable String ids) {
        if (jobLevelService.deleteJobLevelById(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(notes = "更新jobLevel", value = "更新jobLevel")
    @RequestMapping(value = "/joblevel", method = RequestMethod.PUT)
    public RespBean updateJobLevel(JobLevelBean jobLevelBean) {
        if (jobLevelService.updateJobLevel(jobLevelBean) == 1) {
            return RespBean.ok("修改成功!");
        }
        return RespBean.error("修改失败!");
    }
}
