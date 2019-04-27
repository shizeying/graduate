package com.graduate.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-10  7:45
 * @Description：<描述>
 */
@JsonIgnoreProperties(value = {"handler"})
@Component
public class RoleBean {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    /**
     * 角色名称
     */
    @Getter
    @Setter
    private String nameZh;
}
