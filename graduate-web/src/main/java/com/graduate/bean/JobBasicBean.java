package com.graduate.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-21  21:42
 * @Description：招聘人员登陆信息
 */
@Component
public class JobBasicBean  {
    /**
     * (必填项)(主键ID)
     */
    @Getter
    @Setter
    private Integer id;
    /**
     * 面试招聘部门id
     */
    @Getter
    @Setter
    private Integer did;
    /**
     * 姓名
     */
    @Getter
    @Setter
    private String name;
    /**
     * 年龄
     */
    @Getter
    @Setter
    private Integer age;
    /**
     * 性别
     */
    @Getter
    @Setter
    private String gender;
    /**
     * 联系方式
     */
    @Getter
    @Setter
    private String phone;
    /**
     * 紧急联系方式
     */
    @Getter
    @Setter
    private String sparePhone;
    /**
     * 面试时间
     */
    @Getter
    @Setter
    private Date interviewDate;
    @Getter
    @Setter
    private DepartmentBean departmentBean;


}
