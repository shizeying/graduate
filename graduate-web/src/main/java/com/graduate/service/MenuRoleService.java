package com.graduate.service;

import com.graduate.mapper.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  18:55
 * @Description：<描述>
 */
@Service
@Transactional
public class MenuRoleService {
    @Autowired
    MenuRoleMapper menuRoleMapper;

    public int updateMenuRole(Long rid, Long[] mids) {
        menuRoleMapper.deleteMenuByRid(rid);
        if (mids.length == 0) {
            return 0;
        }
        int result=0;
        for (Long mid:mids){
            menuRoleMapper.addMenu(rid,mid);
            result++;
        }

        return result;
    }
}
