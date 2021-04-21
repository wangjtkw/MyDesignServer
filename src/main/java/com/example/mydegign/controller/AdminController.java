package com.example.mydegign.controller;

import com.example.mydegign.common.state.AuditState;
import com.example.mydegign.common.ErrorCode;
import com.example.mydegign.common.MyResponseBody;
import com.example.mydegign.entity.EmployerAccount;
import com.example.mydegign.entity.EmployerCompanyInfo;
import com.example.mydegign.entity.EmployerPersonalInfo;
import com.example.mydegign.entity.responsebean.AdminCompanyInfoResponse;
import com.example.mydegign.service.EmployerAccountService;
import com.example.mydegign.service.EmployerCompanyInfoService;
import com.example.mydegign.service.EmployerPersonalInfoService;
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


    @RequestMapping("/login")
    @ResponseBody
    public Object login(String account, String password) {
        if ("admin".equals(account) && "admin".equals(password)) {
            return new MyResponseBody(200, "OK");
        }
        return new MyResponseBody(ErrorCode.PARAMETER_ERROR_CODE, "账号或密码错误！");
    }

    @RequestMapping("/get/companyInfo")
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
            }
        }
        return new MyResponseBody(200, "OK", result);

    }

}
