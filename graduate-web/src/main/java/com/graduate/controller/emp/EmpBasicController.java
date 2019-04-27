package com.graduate.controller.emp;

import com.graduate.bean.EmployeeBean;
import com.graduate.bean.PositionBean;
import com.graduate.bean.RespBean;
import com.graduate.bean.SalaryBean;
import com.graduate.common.EmailRunnable;
import com.graduate.common.poi.PoiUtils;
import com.graduate.service.DepartmentService;
import com.graduate.service.EmpService;
import com.graduate.service.JobLevelService;
import com.graduate.service.PositionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  19:07
 * @Description：<描述>
 */

@Api(tags = {"员工个人信息"})
@RestController
@CrossOrigin
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmpService empService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionService positionService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    ExecutorService executorService;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    JavaMailSender javaMailSender;
    @ApiIgnore
    @RequestMapping(value = "/basicdata", method = RequestMethod.GET)
    public Map<String, Object> getAllNations() {
        Map<String, Object> map = new HashMap<>();
        map.put("nations", empService.getAllNations());
        map.put("politics", empService.getAllPolitics());
        map.put("deps", departmentService.getDepByPid(-1L));
        map.put("positions", positionService.getAllPos());
        map.put("joblevels", jobLevelService.getAllJobLevels());
        map.put("workID", String.format("%08d", empService.getMaxWorkID() + 1));
        return map;
    }

    @RequestMapping("/maxWorkID")
    public String maxWorkID() {
        return String.format("%08d", empService.getMaxWorkID() + 1);
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public RespBean addEmp(EmployeeBean employeeBean) {
        if (empService.addEmp(employeeBean) == 1) {
            List<PositionBean> allPos = positionService.getAllPos();
            for (PositionBean allPo : allPos) {
                if (allPo.getId() == employeeBean.getPosId()) {
                    employeeBean.setPosName(allPo.getName());
                }
            }
            executorService.execute(new EmailRunnable(employeeBean,
                    javaMailSender, templateEngine));
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @RequestMapping(value = "/empUpdate", method = RequestMethod.PUT)
    public RespBean updateEmp(EmployeeBean employeeBean, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<ObjectError> ls = bindingResult.getAllErrors();
        }
        if (empService.updateEmp(employeeBean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }



    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteEmpById(@PathVariable String ids) {
        if (empService.deleteEmpById(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public Map<String, Object> getEmployeeByPage(
            @RequestParam(defaultValue = "4") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String keywords,
            Long politicId, Long nationId, Long posId,
            Long jobLevelId, String engageForm,
            Long departmentId, String beginDateScope) {
        Map<String, Object> map = new HashMap<>();
        List<EmployeeBean> employeeByPage = empService.getEmployeeByPage(page, size,
                keywords, politicId, nationId, posId, jobLevelId, engageForm,
                departmentId, beginDateScope);
        Long count = empService.getCountByKeywords(keywords, politicId, nationId,
                posId, jobLevelId, engageForm, departmentId, beginDateScope);
        map.put("emps", employeeByPage);
        map.put("count", count);
        return map;
    }

    @RequestMapping(value = "/exportEmp", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportEmp() {
        return PoiUtils.exportEmp2Excel(empService.getAllEmployees());
    }

    /**
     * 不做
     * @param file
     * @return
     */
    @RequestMapping(value = "/importEmp", method = RequestMethod.POST)
    public RespBean importEmp(MultipartFile file) {
        List<EmployeeBean> emps = PoiUtils.importEmp2List(file,
                empService.getAllNations(), empService.getAllPolitics(),
                departmentService.getAllDeps(), positionService.getAllPos(),
                jobLevelService.getAllJobLevels());
        if (empService.addEmps(emps) == emps.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }

}
