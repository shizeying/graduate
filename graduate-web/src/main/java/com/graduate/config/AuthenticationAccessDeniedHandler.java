package com.graduate.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduate.bean.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-11  7:22
 * @Description：自定义权限不足处理程序
 */
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //403
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        RespBean error = RespBean.error("无权限,联系管理员");
        writer.write(new ObjectMapper().writeValueAsString(error));
        writer.flush();
        writer.close();
    }
}
