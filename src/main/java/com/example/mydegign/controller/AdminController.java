package com.example.mydegign.controller;

import com.example.mydegign.common.state.AuditState;
import com.example.mydegign.common.ErrorCode;
import com.example.mydegign.common.MyResponseBody;
import com.example.mydegign.common.state.EmployerPositionState;
import com.example.mydegign.entity.*;
import com.example.mydegign.entity.responsebean.AdminCompanyInfoResponse;
import com.example.mydegign.entity.responsebean.UsersInfoResponseBean;
import com.example.mydegign.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private EmployerCompanyInfoService employerCompanyInfoService;

    @Autowired
    private EmployerAccountService employerAccountService;

    @Autowired
    private EmployerPersonalInfoService employerPersonalInfoService;

    @Autowired
    private EmployerPositionService employerPositionService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private EducationExperiencesService educationExperiencesService;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(String account, String password) {
        if ("admin".equals(account) && "admin".equals(password)) {
            return new MyResponseBody(200, "OK");
        }
        return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, "账号或密码错误！");
    }

    /**
     * 企业信息审批-待审批-列表
     *
     * @return
     */
    @RequestMapping("/get/companyInfo/state")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getCompanyList() {
        List<EmployerCompanyInfo> companyInfos = employerCompanyInfoService.selectAllByState(AuditState.APPROVING);
        ArrayList<AdminCompanyInfoResponse> result = new ArrayList<>();
        for (EmployerCompanyInfo companyInfo : companyInfos) {
            EmployerAccount account = employerAccountService.selectByCompanyId(companyInfo.getEmployerCompanyInfoId());
            if (account.getEmployerPersonalInfoId() != null) {
                EmployerPersonalInfo personalInfo = employerPersonalInfoService.selectById(account.getEmployerPersonalInfoId());
                AdminCompanyInfoResponse response = new AdminCompanyInfoResponse(companyInfo, personalInfo);
                result.add(response);
            } else {
                AdminCompanyInfoResponse response = new AdminCompanyInfoResponse(companyInfo, null);
                result.add(response);
            }
        }
        return new MyResponseBody(200, "OK", result);
    }

    /**
     * 企业信息审批-同意
     *
     * @param companyInfoId
     * @return
     */
    @RequestMapping("/update/companyInfo/adopt")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object updateCompanyAdopt(int companyInfoId) {
        EmployerCompanyInfo companyInfo = employerCompanyInfoService.selectById(companyInfoId);
        if (companyInfo == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, "企业信息编号错误！");
        }
        if (!companyInfo.getEmployerCompanyInfoAuditState().equals(AuditState.APPROVING)) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, "审核状态错误！");
        }
        companyInfo.setEmployerCompanyInfoAuditState(AuditState.APPROVED);
        employerCompanyInfoService.updateByPrimaryKeySelective(companyInfo);
        return new MyResponseBody(200, "OK");
    }

    /**
     * 企业信息审批-拒绝
     *
     * @param companyInfoId
     * @return
     */
    @RequestMapping("/update/companyInfo/refuse")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object updateCompanyRefuse(int companyInfoId) {
        EmployerCompanyInfo companyInfo = employerCompanyInfoService.selectById(companyInfoId);
        if (companyInfo == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, "企业信息编号错误！");
        }
        if (!companyInfo.getEmployerCompanyInfoAuditState().equals(AuditState.APPROVING)) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, "审核状态错误！");
        }
        companyInfo.setEmployerCompanyInfoAuditState(AuditState.REFUSE);
        employerCompanyInfoService.updateByPrimaryKeySelective(companyInfo);
        return new MyResponseBody(200, "OK");
    }

    /**
     * 职位信息审批-列表
     *
     * @return
     */
    @RequestMapping("/get/positionInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getPositionInfo() {
        List<EmployerPosition> positionList = employerPositionService.selectAllByType(EmployerPositionState.SIGN_UP, "");
        return new MyResponseBody(200, "OK", positionList);
    }

    /**
     * 职位信息审批-通过
     *
     * @param positionInfoId
     * @return
     */
    @RequestMapping("/update/positionInfo/adopt")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object updatePositionInfoAdopt(int positionInfoId) {
        EmployerPosition position = employerPositionService.selectById(positionInfoId);
        if (position == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, "职位信息编号错误！");
        }
        if (!position.getEmployerPositionState().equals(EmployerPositionState.SIGN_UP)) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, "状态错误！");
        }
        position.setEmployerPositionState(EmployerPositionState.RECRUIT);
        employerPositionService.updateByPrimaryKeySelective(position);
        return new MyResponseBody(200, "OK");
    }

    /**
     * 职位信息审批-拒绝
     *
     * @param positionInfoId
     * @return
     */
    @RequestMapping("/update/positionInfo/refuse")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object updatePositionInfoRefuse(int positionInfoId) {
        EmployerPosition position = employerPositionService.selectById(positionInfoId);
        if (position == null) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, "职位信息编号错误！");
        }
        if (!position.getEmployerPositionState().equals(EmployerPositionState.SIGN_UP)) {
            return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, "状态错误！");
        }
        position.setEmployerPositionState(EmployerPositionState.OFFLINE);
        employerPositionService.updateByPrimaryKeySelective(position);
        return new MyResponseBody(200, "OK");
    }

    @RequestMapping("/get/employerInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getEmployerInfo() {
        List<EmployerCompanyInfo> companyInfos = employerCompanyInfoService.selectAll();
        ArrayList<AdminCompanyInfoResponse> result = new ArrayList<>();
        for (EmployerCompanyInfo companyInfo : companyInfos) {
            EmployerAccount account = employerAccountService.selectByCompanyId(companyInfo.getEmployerCompanyInfoId());
            if (account.getEmployerPersonalInfoId() != null) {
                EmployerPersonalInfo personalInfo = employerPersonalInfoService.selectById(account.getEmployerPersonalInfoId());
                AdminCompanyInfoResponse response = new AdminCompanyInfoResponse(companyInfo, personalInfo);
                result.add(response);
            }else {
                AdminCompanyInfoResponse response = new AdminCompanyInfoResponse(companyInfo, null);
                result.add(response);
            }
        }
        return new MyResponseBody(200, "OK", result);
    }

    @RequestMapping("/get/employeeInfo")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Object getEmployeeInfo() {
        List<Users> usersList = usersService.selectAll();
        List<UsersInfoResponseBean> result = new ArrayList<>();
        for (Users user : usersList) {
            if (user.getEducationExperiencesId() != null) {
                EducationExperiences experiences = educationExperiencesService.selectById(
                        user.getEducationExperiencesId()
                );
                UsersInfoResponseBean responseBean = new UsersInfoResponseBean(user, experiences);
                result.add(responseBean);
            }
        }
        return new MyResponseBody(200, "OK", result);
    }

}
