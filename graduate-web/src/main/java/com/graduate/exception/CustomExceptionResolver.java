package com.graduate.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-10  19:26
 * @Description：自定义异常处理器
 */

/**
 * 泛指组件
 */
@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",500);
        map.put("msg","失败操作");
        //添加属性
        modelAndView.addAllObjects(map);
        return modelAndView;
    }
}
