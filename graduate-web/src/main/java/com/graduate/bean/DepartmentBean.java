package com.graduate.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-10  9:50
 * @Description：<描述>
 */
@Component
@NoArgsConstructor
public class DepartmentBean {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Long parentId;

    @Setter
    private String depPath;

    @Getter
    @Setter
    private boolean enabled;
    @Getter
    @Setter
    private boolean isParent;
    //存储执行过程
    @Setter
    private Integer result;
    @Getter
    @Setter
    private List<DepartmentBean> children = new ArrayList<>();

    @JsonIgnore
    public String getDepPath() {
        return depPath;
    }

    @JsonIgnore
    public Integer getResult() {
        return result;
    }

    /**
     * 重写equals和hashCode方法
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {

            return false;
        }
        DepartmentBean that = (DepartmentBean) object;
        return name != null ? name.equals(that.name) : that.name == null;
    }
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public DepartmentBean(String name) {
        this.name = name;
    }
}
