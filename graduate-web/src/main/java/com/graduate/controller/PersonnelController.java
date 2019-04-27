package com.graduate.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-15  18:31
 * @Description：<描述>
 */
@Api(tags = "人员控制器")
@RestController
@CrossOrigin
@RequestMapping("/personnel")
public class PersonnelController {
    @RequestMapping("/")
    public String hello() {
        return "hello";
    }
    @RequestMapping("/emp/hello")
    public String helloEmp() {
        return "hello emp";
    }
}