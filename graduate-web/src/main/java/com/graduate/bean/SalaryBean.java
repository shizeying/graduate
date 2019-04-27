package com.graduate.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-10  11:42
 * @Description：<描述>
 */
@Component
public class SalaryBean {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private Integer bonus;
    @Getter
    @Setter
    private Integer lunchSalary;
    @Getter
    @Setter
    private Integer trafficSalary;
    @Getter
    @Setter
    private Integer basicSalary;
    @Getter
    @Setter
    private Integer allSalary;
    @Getter
    @Setter
    private Integer pensionBase;
    @Getter
    @Setter
    private Float pensionPer;
    @Getter
    @Setter
    private Date createDate;
    @Getter
    @Setter
    private Integer medicalBase;
    @Getter
    @Setter
    private Float medicalPer;
    @Getter
    @Setter
    private Integer accumulationFundBase;
    @Getter
    @Setter
    private Float accumulationFundPer;
    @Getter
    @Setter
    private String name;


}
