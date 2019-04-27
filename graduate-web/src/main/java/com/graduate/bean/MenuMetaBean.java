package com.graduate.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-10  11:10
 * @Description：<描述>
 */
@Component
public class MenuMetaBean implements Serializable {
    @Getter
    @Setter
    private boolean keepAlive;
    @Getter
    @Setter
    private boolean requireAuth;
}
