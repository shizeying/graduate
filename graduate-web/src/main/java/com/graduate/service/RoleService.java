package com.graduate.service;

import com.graduate.bean.RoleBean;
import com.graduate.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  18:56
 * @Description：<描述>
 */
@Service
@Transactional
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public List<RoleBean> roles() {
        return roleMapper.roles();
    }

    public int addNewRole(String role, String roleZh) {
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        return roleMapper.addNewRole(role, roleZh);
    }

    public int deleteRoleById(Long rid) {
        return roleMapper.deleteRoleById(rid);
    }
}

