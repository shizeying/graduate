package com.graduate.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  20:09
 * @Description：<描述>
 */
@Api(tags = "登陆")
@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @RequestMapping("/basic")
    public String basic() {
        return "basic";
    }

    @RequestMapping("/")
    public String hello() {
        return "Hello";
    }
}
