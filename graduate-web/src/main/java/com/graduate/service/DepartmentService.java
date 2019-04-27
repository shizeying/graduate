package com.graduate.service;

import com.graduate.bean.DepartmentBean;
import com.graduate.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  18:45
 * @Description：<描述>
 */
@Service
@Transactional
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;


    public List<DepartmentBean> getDepByPid(Long pid) {
        return departmentMapper.getDepByPid(pid);
    }

    public List<DepartmentBean> getAllDeps() {
        return departmentMapper.getAllDeps();
    }


}
