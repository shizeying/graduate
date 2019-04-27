package com.graduate.controller;

import com.graduate.bean.HrBean;
import com.graduate.bean.MenuBean;
import com.graduate.common.HrUtils;
import com.graduate.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  20:08
 * @Description：这是一个只要登录就能访问的Controller主要用来获取一些配置信息
 */
@Api(tags = "获取登陆信息")
@RestController
@RequestMapping("/config")
@CrossOrigin
public class ConfigController {
    @Autowired
    MenuService menuService;

    @ApiOperation(notes = "获取列表信息", value = "获取列表信息")
    @RequestMapping("/sysmenu")
    public List<MenuBean> sysmenu() {
        return menuService.getMenusByHrId();
    }
    @ApiOperation(notes = "hr认证", value = "hr认证")
    @RequestMapping("/hr")
    public HrBean currentUser() {
        return HrUtils.getCurrentHr();
    }
}
