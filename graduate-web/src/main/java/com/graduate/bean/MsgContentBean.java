package com.graduate.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-19  10:52
 * @Description：<描述>
 */
@Component
public class MsgContentBean {
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private Date createDate;
}
