package com.graduate.controller;

import com.graduate.bean.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  20:10
 * @Description：<描述>
 */
@Api(tags = "登陆信息")
@RestController
@CrossOrigin
public class RegLoginController {
    @ApiOperation(notes = "登陆错误", value = "登陆错误")
    @RequestMapping("/login_p")
    public RespBean login() {
        return RespBean.error("尚未登录，请登录!");
    }

    @ApiOperation(notes = "登陆成功信息", value = "登陆成功信息")
    @GetMapping("/employee/advanced/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/employee/basic/hello")
    public String basicHello() {
        return "basicHello";
    }
}
