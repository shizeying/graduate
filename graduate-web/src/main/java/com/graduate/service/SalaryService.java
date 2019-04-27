package com.graduate.service;

import com.graduate.bean.SalaryBean;
import com.graduate.mapper.SalaryMapper;
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
public class SalaryService {
    @Autowired
    SalaryMapper salaryMapper;

    public int addSalary(SalaryBean salaryBean) {
        return salaryMapper.addSalary(salaryBean);
    }


    public List<SalaryBean> getAllSalary() {
        return salaryMapper.getAllSalary();
    }

    public int getSidAndEid(Long eid, Integer sid) {
        if (salaryMapper.getSidAndEid(eid,sid)==null){
            return 1;
        }
        return -1;
    }

    public int updateSalary(SalaryBean salaryBean) {
        return salaryMapper.updateSalary(salaryBean);
    }

    public boolean deleteSalary(String ids) {
        String[] split = ids.split(",");
        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }
        return salaryMapper.deleteSalary(ints) == ints.length;
    }

    public boolean deleteEmpSalary(String ids) {
        String[] split = ids.split(",");
        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }
        return salaryMapper.deleteEmpSalary(ints) == ints.length;
    }

    public Long getCountEmpSal() {
        return salaryMapper.getCountEmpSal();
    }

    public int updateEmpSalary(Integer sid, Long eid) {
        salaryMapper.deleteSalaryByEid(eid);
        return salaryMapper.addSidAndEid(sid, eid);
    }

    public int insertEmpSalary(Integer sid, Long eid) {
        return salaryMapper.addSidAndEid(sid, eid);
    }
}

