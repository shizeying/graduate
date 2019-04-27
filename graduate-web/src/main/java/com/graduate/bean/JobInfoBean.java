package com.graduate.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-21  21:47
 * @Description：<描述>
 */
@Component
public class JobInfoBean  {
    /**
     * (必填项)(主键ID)
     */
    @Getter
    @Setter
    private Integer id;
    /**
     * 招聘部门id
     */
    @Getter
    @Setter
    private Integer did;
    /**
     * 招聘信息
     */
    @Getter
    @Setter
    private String info;
    /**
     * 创建时间
     */
    @Getter
    @Setter
    private Date createDate;
    @Getter
    @Setter
    private DepartmentBean departmentBean;


}
