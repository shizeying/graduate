package com.graduate.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-11  7:31
 * @Description：服务器回应信息
 */
@NoArgsConstructor
public class RespBean {
    //状态码
    @Getter
    @Setter
    private Integer status;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private Object obj;


    public static RespBean build(){
        return  new RespBean();
    }

    public static RespBean ok(String msg, Object obj){
        return new RespBean(200,msg,obj);
    }

    public static RespBean ok(String msg){
        return new RespBean(200,msg,null);
    }

    public static RespBean error(String msg, Object obj){
        return new RespBean(500,msg,obj);
    }
    public static RespBean error(String msg){
        return new RespBean(500,msg,null);
    }

    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }
}
