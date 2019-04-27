package com.graduate.service;

import com.graduate.bean.EmployeeBean;
import com.graduate.bean.NationBean;
import com.graduate.bean.PoliticsStatusBean;
import com.graduate.bean.SalaryBean;
import com.graduate.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  18:49
 * @Description：<描述>
 */
@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor = Exception.class)
public class EmpService {
    @Autowired
    EmpMapper empMapper;
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    SimpleDateFormat birthdayFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public List<NationBean> getAllNations() {
        return empMapper.getAllNations();
    }

    public List<PoliticsStatusBean> getAllPolitics() {
        return empMapper.getAllPolitics();
    }

    public int getEmpId(Long eid){
        if (empMapper.getEmpId(eid)!=null){
            return 1;
        }
        return -1;
    }
    public int addEmp(EmployeeBean employeeBean) {
        Date beginContract = employeeBean.getBeginContract();
        Date endContract = employeeBean.getEndContract();
        Double contractTerm = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract));
        employeeBean.setContractTerm(Double.parseDouble(decimalFormat.format(contractTerm / 12)));
        return empMapper.addEmp(employeeBean);
    }

    public Long getMaxWorkID() {
        EmployeeBean employeeBean = empMapper.getMaxWorkID();
        String workID = employeeBean.getWorkID();
        Long maxWorkID = Long.valueOf(workID);
        return maxWorkID == null ? 0 : maxWorkID;
    }

    /**
     * 新增方法
     *
     * @return
     */
    public List<EmployeeBean> getWorkID(String workID) {
        return empMapper.getWorkID(workID);
    }

    public int selectNameWork(String name, String workID) {
        if (empMapper.selectNameWork(name, workID) == null) {
            return -1;
        }
        return 1;
    }


    /**
     * 新增方法
     *
     * @param workID
     * @return
     */
    public List<EmployeeBean> getWorkIDSal(String workID) {
        return empMapper.getWorkIDSal(workID);
    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public List<EmployeeBean> getEmployeeByPage(Integer page, Integer size, String keywords, Long politicId, Long nationId, Long posId, Long jobLevelId, String engageForm, Long departmentId, String beginDateScope) {
        int start = (page - 1) * size;
        Date startBeginDate = null;
        Date endBeginDate = null;
        //noinspection AlibabaUndefineMagicConstant
        if (beginDateScope != null && beginDateScope.contains(",")) {
            try {
                String[] split = beginDateScope.split(",");
                startBeginDate = birthdayFormat.parse(split[0]);
                endBeginDate = birthdayFormat.parse(split[1]);
            } catch (ParseException e) {
            }
        }
        if (keywords.length() > 0) {
            System.out.println("keywords:" + keywords.length());
            String nameLike = "%" + keywords + "%";
            return empMapper.getEmployeeByPage(start, size, nameLike, politicId, nationId, posId, jobLevelId, engageForm, departmentId, startBeginDate, endBeginDate);
        }
        return empMapper.getEmployeeByPage(start, size, keywords, politicId, nationId, posId, jobLevelId, engageForm, departmentId, startBeginDate, endBeginDate);

    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public Long getCountByKeywords(String keywords, Long politicId, Long nationId, Long posId, Long jobLevelId, String engageForm, Long departmentId, String beginDateScope) {
        Date startBeginDate = null;
        Date endBeginDate = null;
        //noinspection AlibabaUndefineMagicConstant
        if (beginDateScope != null && beginDateScope.contains(",")) {
            try {
                String[] split = beginDateScope.split(",");
                startBeginDate = birthdayFormat.parse(split[0]);
                endBeginDate = birthdayFormat.parse(split[1]);
            } catch (ParseException e) {
            }
        }
        if (keywords.length() != 0) {
            String nameLike = "%" + keywords + "%";
            return empMapper.getCountByKeywords(nameLike, politicId, nationId, posId, jobLevelId, engageForm, departmentId, startBeginDate, endBeginDate);
        }
        return empMapper.getCountByKeywords(keywords, politicId, nationId, posId, jobLevelId, engageForm, departmentId, startBeginDate, endBeginDate);
    }


    public int updateEmp(EmployeeBean employeeBean) {
        String s = String.format("%08d", Integer.valueOf(employeeBean.getWorkID()));
        employeeBean.setWorkID(s);
        if (employeeBean.getBeginContract() != null || employeeBean.getBeginContract() != null) {
            Date beginContract = employeeBean.getBeginContract();
            Date endContract = employeeBean.getEndContract();
            Double contractTerm = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract));
            employeeBean.setContractTerm(Double.parseDouble(decimalFormat.format(contractTerm / 12)));
            return empMapper.updateEmp(employeeBean);
        }
        return -1;
    }

    public boolean deleteEmpById(String ids) {
        String[] split = ids.split(",");
        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }
        return empMapper.deleteEmpById(ints) == ints.length;
    }

    public List<EmployeeBean> getAllEmployees() {
        return empMapper.getALLEmployee();
    }

    public List<EmployeeBean> getMember(){
        return empMapper.getMember();
    }

    public int addEmps(List<EmployeeBean> emps) {
        int result = 0;
        for (EmployeeBean em : emps) {
            result = empMapper.addEmp(em);
        }
        return result;
    }

    public List<EmployeeBean> getEmployeeByPageShort(Integer page, Integer size) {
        int start = (page - 1) * size;
        return empMapper.getEmployeeByPageShort(start, size);
    }

    public List<EmployeeBean> getPage(Integer page, Integer size) {
        int start = (page - 1) * size;
        return empMapper.getPage(start, size);
    }
}
