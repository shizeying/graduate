package com.graduate.config;


import com.graduate.bean.MenuBean;
import com.graduate.bean.RoleBean;
import com.graduate.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-12  12:43
 * @Description：自定义决策管理器
 */
@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<MenuBean> allMenu = menuService.getAllMenu();
        List<String> list=new ArrayList<>();
        for (MenuBean menu : allMenu) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)
                    && menu.getRoles().size() > 0) {
                List<RoleBean> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                    list.add(roles.get(i).getName());
                }
                String[] strings = list.toArray(new String[list.size()]);
                return SecurityConfig.createList(strings);
            }
        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
