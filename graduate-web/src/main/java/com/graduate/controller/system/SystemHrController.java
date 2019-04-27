package com.graduate.controller.system;

import com.graduate.bean.EmployeeBean;
import com.graduate.bean.HrBean;
import com.graduate.bean.RespBean;
import com.graduate.service.EmpService;
import com.graduate.service.HrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  20:05
 * @Description：<描述>
 */
@Api(tags = "操作员管理")
@RestController
@CrossOrigin
@RequestMapping("/system/hr")
public class SystemHrController {
    @Autowired
    HrService hrService;
    @Autowired
    EmpService empService;

    @RequestMapping("/id/{hrId}")
    public HrBean getHrById(@PathVariable Long hrId) {
        return hrService.getHrById(hrId);
    }

    @RequestMapping(value = "/{hrId}", method = RequestMethod.DELETE)
    public RespBean deleteHr(@PathVariable Long hrId) {
        if (hrService.deleteHr(hrId) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public RespBean updateHr(HrBean hrBean) {
        if (hrService.updateHr(hrBean) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }




    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public List<EmployeeBean> selectName(String workID) {
        return empService.getWorkID(workID);
    }


    @RequestMapping(value = "/roles", method = RequestMethod.PUT)
    public RespBean updateHrRoles(Long hrId, Long[] rids) {
        if (hrService.updateHrRoles(hrId, rids) ==true) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @RequestMapping(value = "/getMember", method = RequestMethod.POST)
    public List<EmployeeBean> getMember(){
        return empService.getMember();
    }
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public RespBean getHr(String name,String address,String phone )throws BadHanyuPinyinOutputFormatCombination {
        int result = hrService.insertHr(name,address,phone);
        if (result == 1) {
            return RespBean.ok("添加成功!");
        }else if (result==-1){
            return RespBean.error("请不要为已有账号重新分配信息");
        }
        return RespBean.error("操作有误,添加失败!");
    }

    @RequestMapping("/{keywords}")
    public List<HrBean> getHrsByKeywords(@PathVariable(required = false) String keywords) {
        List<HrBean> hrs = hrService.getHrsByKeywords(keywords);
        return hrs;
    }

    /**
     * 新增方法
     * @param name
     * @param workID
     * @return
     */
    @RequestMapping(value = "/Verb",method = RequestMethod.GET)
    public RespBean selectNameWork(String name,String workID){
        if (empService.selectNameWork(name,workID)==-1){
            return RespBean.error("若为公司员工,请确认姓名和工号是否正确");
        }
        return RespBean.ok("");
    }

    @RequestMapping(value = "/hr/reg", method = RequestMethod.POST)
    public RespBean hrReg(String username, String password) {
        int i = hrService.hrReg(username, password);
        if (i == 1) {
            return RespBean.ok("注册成功!");
        } else if (i == -1) {
            return RespBean.error("用户名重复，注册失败!");
        }
        return RespBean.error("注册失败!");
    }

}

