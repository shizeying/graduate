package com.grauate;

import com.graduate.GraduateWebApplication;
import com.graduate.bean.EmployeeBean;
import com.graduate.bean.HrBean;
import com.graduate.bean.NationBean;
import com.graduate.bean.PositionBean;
import com.graduate.controller.emp.EmpBasicController;
import com.graduate.controller.jobInfo.InfoTableController;
import com.graduate.controller.salary.SalaryEmpController;
import com.graduate.controller.salary.SalarySearchController;
import com.graduate.controller.system.SystemBasicController;
import com.graduate.controller.system.SystemHrController;
import com.graduate.mapper.*;
import com.graduate.service.*;

import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GraduateWebApplication.class, MockServletContext.class})
public class GraduateWebApplicationTests {
    private long l;
    @Autowired
    EmpMapper empMapper;
    @Autowired
    HrMapper hrMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    JobBasicMapper jobBasicMapper;
    @Autowired
    JobInfoMapper jobInfoMapper;
    @Autowired
    JobInfoService jobInfoService;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    SalaryMapper salaryMapper;
    @Autowired
    JobLevelMapper jobLevelMapper;
    @Autowired
    MenuRoleMapper menuRoleMapper;
    @Autowired
    MenuRoleService menuRoleService;
    @Autowired
    MenuService menuService;
    @Autowired
    EmpService empService;
    @Autowired
    HrService hrService;
    @Autowired
    JobBasicService jobBasicService;
    @Autowired
    JobLevelService jobLevelService;
    @Autowired
    SalaryService salaryService;
    @Autowired
    PositionService positionService;
    private MockMvc mockMvc;
    @Resource
    private EmpBasicController empBasicController;
    @Resource
    private InfoTableController infoTableController;
    @Resource
    private SalaryEmpController salaryEmpController;
    @Resource
    private SystemBasicController systemBasicController;
    @Resource
    SalarySearchController salarySearchController;
    @Resource
    SystemHrController systemHrController;

    @Test
    public void setSystemHrController() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(systemHrController).build();
        //发送请求
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.put("/system/hr/");

        //requestBuilder.param("msg","1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse()
                .getContentAsString();
        System.out.println(content);
    }


    //@Test
    //public void setSalarySearchController() throws Exception{
    //    mockMvc = MockMvcBuilders.standaloneSetup(salarySearchController).build();
    //    //发送请求
    //    MockHttpServletRequestBuilder requestBuilder =
    //            MockMvcRequestBuilders.put("/salary/search/insertEmpSalary");
    //    //requestBuilder.param("sid","450001500").param("eid","4");
    //
    //    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    //    String content = result.getResponse()
    //            .getContentAsString();
    //    System.out.println(content);
    //}
    @Test
    public void setSysMsgMapper() {

    }
    //@Test
    //public void setMenuService() {
    //    //menuService.getMenusByHrId();
    //    //menuService.getAllMenu();
    //    //empService.getMaxWorkID();
    //    HrBean hrBean = new HrBean();
    //    hrBean.setId((long) 3);
    //    hrBean.setUsername("admin");
    //    hrBean.setPassword("123");
    //    //hrService.updateHr(hrBean);
    //    PositionBean positionBean = new PositionBean();
    //    positionBean.setName("设计院");
    //    positionService.addPos(positionBean);
    //    Long rid=(long)450001500;
    //    Long[] mids= new Long[4];
    //    mids[0]=(long)2;
    //    mids[1]=(long)3;
    //    mids[2]=(long)4;
    //    mids[3]=(long)5;
    //
    //    menuRoleService.updateMenuRole(rid,mids);
    //}

    @Before//在每个测试方法之前运行
    public void Testbegin() {
        l = System.currentTimeMillis();
    }

    @After//在每个方法之后运行
    public void end() {
        l = System.currentTimeMillis() - l;
        System.out.println("程序运行了:" + l + "s");
    }


    //@Test
    //public  void setEmpBasicController()  throws Exception{
    //    mockMvc = MockMvcBuilders.standaloneSetup(empBasicController).build();
    //    //发送请求
    //    MockHttpServletRequestBuilder requestBuilder =
    //            MockMvcRequestBuilders.get("/employee/basic/emp");
    //    //MockHttpServletRequestBuilder requestBuilder =
    //    //        MockMvcRequestBuilders.get("/employee/basic/basicdata");
    //
    //    //requestBuilder.param("employeeBean",emplyoee);
    //    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    //    String content = result.getResponse()
    //            .getContentAsString();
    //    System.out.println(content);
    //
    //    File file = new File("K:\\员工信息表 .xls");
    //    FileInputStream fileInputStream = new FileInputStream(file);
    //    MockMultipartFile mockMultipartFile = new MockMultipartFile(file.getName(),fileInputStream);
    //    ////文件之外的参数
    //    //MockHttpServletRequestBuilder requestBuilder =
    //    //        MockMvcRequestBuilders.fileUpload("/employee/basic/importEmp").file(mockMultipartFile);
    //    //MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    //    //String content = result.getResponse()
    //    //        .getContentAsString();
    //    //System.out.println(content);
    //    /**
    //     * 到这里就是获取到响应的结果了
    //     * {"status":0,
    //     * "msg":"用户名和密码正确!",
    //     * "data":"48595f52-b22c-4485-9244-f4004255b972"}
    //     * 怎么是一个JSON数据?
    //     * 这里就要将JSON数据转换为NoteResult对象
    //     */
    //}
    //@Test
    public void setInfoTableController() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(infoTableController).build();
        //发送请求
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/jobInfo/basic/jobBasicPage");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse()
                .getContentAsString();
        System.out.println(content);

    }

    @Test
    public void setSalaryEmpController() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(salaryEmpController).build();
        //发送请求
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/salary/sobcfg/");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse()
                .getContentAsString();
        System.out.println(content);

    }
    //@Test
    //public void  setSystemBasicController() throws Exception{
    //    mockMvc = MockMvcBuilders.standaloneSetup(salarySearchController).build();
    //    //发送请求
    //    MockHttpServletRequestBuilder requestBuilder =
    //            MockMvcRequestBuilders.delete("/salary/search/delete/");
    //    requestBuilder.param("ids","6,10");
    //    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    //    String content = result.getResponse()
    //            .getContentAsString();
    //    System.out.println(content);
    //
    //}

    //@Test
    //public void setEmpService(){
    //    //empService.getEmployeeByPage(1,10,"",null,null,null,null,"",null,"");
    //    //empService.getCountByKeywords("",null,null,null,null,null,null,null);
    //    EmployeeBean employeeBean = new EmployeeBean();
    //    // 1(Long), 江南一点雨(String), 男(String), 1990-01-01 00:00:00.0(Timestamp), 610122199001011256(String), 已婚(String), 1(Long), 陕西(String), 13(Long), laowang@qq.com(String), 18565558897(String), 深圳市南山区(String), 5(Long), 9(Long), 29(Long), 劳务合同(String), 本科(String), 信息管理与信息系统(String), 深圳大学(String), 2018-01-01 00:00:00.0(Timestamp), 1(String), 4.33(Double), 2018-04-01 00:00:00.0(Timestamp), 2015-09-07 00:00:00.0(Timestamp), 2020-01-01 00:00:00.0(Timestamp)
    //    employeeBean.setId((long)1);
    //    employeeBean.setName("江南一点雨");
    //    employeeBean.setGender("女");
    //    employeeBean.setBirthday(Timestamp.valueOf("1990-01-01 00:00:00.0"));
    //    employeeBean.setIdCard("610122199001011256");
    //    employeeBean.setWedlock("已婚");
    //    employeeBean.setNationId((long)1);
    //    employeeBean.setNativePlace("陕西");
    //    employeeBean.setPoliticId((long)13);
    //    employeeBean.setEmail("laowang@qq.com");
    //    employeeBean.setPhone("18565558897");
    //    employeeBean.setAddress("深圳市南山区");
    //    employeeBean.setDepartmentId((long)5);
    //    employeeBean.setJobLevelId((long)9);
    //    employeeBean.setPosId((long)29);
    //    employeeBean.setEngageForm("劳务合同");
    //    employeeBean.setTiptopDegree("本科");
    //    employeeBean.setSpecialty("信息管理与信息系统");
    //    employeeBean.setSchool("深圳大学");
    //    String s="2018-01-01 00:00:00.0";
    //    employeeBean.setBeginDate(Timestamp.valueOf(s));
    //    employeeBean.setWorkID("1");
    //    employeeBean.setContractTerm(0.0);
    //    employeeBean.setConversionTime(new Date(2018-04-01));
    //    employeeBean.setBeginContract(new Date(2018-01-01));
    //    employeeBean.setEndContract(new Date(2020-01-01));
    //    empService.updateEmp(employeeBean);
    //}

    //@Test
    //public void setEmpMapper(){
    //    EmployeeBean employeeBean = new EmployeeBean();
    //    employeeBean.setName("shizeying");
    //    employeeBean.setGender("男");
    //    employeeBean.setBirthday(new Date(2019-03-04));
    //    employeeBean.setIdCard("640381199510013018");
    //    employeeBean.setWedlock("离异");
    //    employeeBean.setNationId((long)1);
    //    employeeBean.setNativePlace("宁");
    //    employeeBean.setEmail("1@13.com");
    //    employeeBean.setPhone("18780134977");
    //    employeeBean.setAddress("四川大学锦城学院");
    //    employeeBean.setDepartmentId((long)8);
    //    employeeBean.setJobLevelId((long)9);
    //    employeeBean.setPosId((long)30);
    //    employeeBean.setWorkID("00000001");
    //    employeeBean.setContractTerm( 0.08);
    //    employeeBean.setConversionTime(new Date(2018-04-01));
    //    employeeBean.setBeginContract(new Date(2018-01-01));
    //    employeeBean.setEndContract(new Date(2020-01-01));
    //    empService.addEmp(employeeBean);
    //}

}
