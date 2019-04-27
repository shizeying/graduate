package com.graduate.service;

import com.graduate.bean.MenuBean;
import com.graduate.common.HrUtils;
import com.graduate.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-12  12:02
 * @Description：<描述>
 */

@Service
//声明事务
@Transactional
//统一指定缓存的名字
@CacheConfig(cacheNames = "menus_cache")
public class MenuService {
    @Autowired
    MenuMapper menuMapper;
    //@Cacheable(key = "#root.methodName")
    public List<MenuBean> getAllMenu() {
        return menuMapper.getAllMenu();
    }
    public List<MenuBean> getMenusByHrId() {
        List<Long> hr = menuMapper.getHrId(HrUtils.getCurrentHr().getId());
        Long[] hrId = new Long[hr.size()];
        for (int i = 0; i < hr.size(); i++) {
            hrId[i] = hr.get(i);
        }
        return menuMapper.getMenusByHrId(hrId);
    }

    public List<MenuBean> menuTree() {
        return menuMapper.menuTree();
    }
    public List<Long> getMenusByRid(Long rid) {
        return menuMapper.getMenusByRid(rid);
    }
}
