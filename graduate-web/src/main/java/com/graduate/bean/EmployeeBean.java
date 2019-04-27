package com.graduate.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-10  10:03
 * @Description：<描述>
 */
@Component
public class EmployeeBean {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String gender;
    @Getter
    @Setter
    private Date birthday;
    @Getter
    @Setter
    private String idCard;
    @Getter
    @Setter
    private String wedlock;
    @Setter
    private Long nationId;
    @Setter
    private String nationName;
    @Setter
    @Getter
    private String nativePlace;
    @Setter
    private String politicName;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String phone;
    @Setter
    @Getter
    private String address;
    @Setter
    private Long departmentId;
    @Setter
    private Long politicId;
    @Setter
    private String departmentName;
    @Setter
    private Long jobLevelId;
    @Setter
    private String jobLevelName;
    @Setter
    private Long posId;
    @Setter
    @Getter
    private String posName;
    @Setter
    @Getter
    private String engageForm;
    @Setter
    @Getter
    private String tiptopDegree;
    @Setter
    @Getter
    private String specialty;
    @Setter
    @Getter
    private String school;
    @Setter
    @Getter
    private Date beginDate;
    @Setter
    @Getter
    private String workState;
    @Setter
    @Getter
    private String workID;
    @Setter
    @Getter
    private Double contractTerm;
    @Setter
    @Getter
    private Date conversionTime;
    @Setter
    @Getter
    private Date notWorkDate;
    @Setter
    @Getter
    private Date beginContract;
    @Setter
    @Getter
    private Date endContract;
    @Setter
    @Getter
    private Integer workAge;
    @Setter
    @Getter
    private DepartmentBean department;
    @Setter
    @Getter
    private NationBean nation;
    @Setter
    @Getter
    private JobLevelBean jobLevel;
    @Setter
    @Getter
    private PositionBean position;
    @Setter
    @Getter
    private PoliticsStatusBean politicsStatus;
    @Setter
    @Getter
    private SalaryBean salary;
    @JsonIgnore
    public Long getDepartmentId() {
        return departmentId;
    }
    public void setWorkID(String workID) {
        this.workID = workID;
    }
    @JsonIgnore
    public Long getPoliticId() {
        return politicId;
    }
    @JsonIgnore
    public String getDepartmentName() {
        return departmentName;
    }
    @JsonIgnore
    public Long getJobLevelId() {
        return jobLevelId;
    }
    @JsonIgnore
    public String getJobLevelName() {
        return jobLevelName;
    }
    @JsonIgnore
    public Long getPosId() {
        return posId;
    }
    @JsonIgnore
    public Long getNationId() {
        return nationId;
    }
    @JsonIgnore
    public String getNationName() {
        return nationName;
    }
    @JsonIgnore
    public String getPoliticName() {
        return politicName;
    }
}
