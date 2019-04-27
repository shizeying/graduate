package com.graduate.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-09  22:03
 * @Description：<描述>
 */
@Component
public class MenuBean implements Serializable {

    @Getter
    @Setter
    private Long id;
    @Setter
    private String url;
    @Setter
    @Getter
    private String path;
    @Setter
    private Object component;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String iconCls;
    @Setter
    private Long parentId;
    @Setter
    private List<RoleBean> roles;
    @Setter
    @Getter
    private List<MenuBean> children;
    @Setter
    @Getter
    private MenuMetaBean meta;

    @JsonIgnore
    public String getUrl() {
        return url;
    }

    @JsonIgnore
    public List<RoleBean> getRoles() {
        return roles;
    }

    @JsonIgnore
    public Long getParentId() {
        return parentId;
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public Object getComponent() {
        return component;
    }
}
