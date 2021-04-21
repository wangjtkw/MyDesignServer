package com.example.mydegign.entity.responsebean;

import com.example.mydegign.entity.EmployerCompanyInfo;
import com.example.mydegign.entity.EmployerPersonalInfo;

public class AdminCompanyInfoResponse {
    private EmployerCompanyInfo companyInfo;
    private EmployerPersonalInfo personalInfo;

    public AdminCompanyInfoResponse(EmployerCompanyInfo companyInfo, EmployerPersonalInfo personalInfo) {
        this.companyInfo = companyInfo;
        this.personalInfo = personalInfo;
    }

    public EmployerCompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(EmployerCompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public EmployerPersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(EmployerPersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }
}
