package com.graduate.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-10  10:59
 * @Description：<描述>
 */
@Component
public class JobLevelBean {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String titleLevel;
    @Getter
    @Setter
    private Timestamp createDate;
    @Getter
    @Setter
    private boolean enabled;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        JobLevelBean jobLevel = (JobLevelBean) object;

        return name != null ? name.equals(jobLevel.name) : jobLevel.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public JobLevelBean(String name) {
        this.name = name;
    }

    public JobLevelBean() {
    }
}
