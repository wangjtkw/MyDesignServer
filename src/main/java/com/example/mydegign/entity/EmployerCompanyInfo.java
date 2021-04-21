package com.example.mydegign.entity;

public class EmployerCompanyInfo {
    private Integer employerCompanyInfoId;

    private String employerCompanyInfoName;

    private String employerCompanyInfoCompanyType;

    private String employerCompanyInfoBusinessState;

    private String employerCompanyInfoFoundTime;

    private String employerCompanyInfoRegisterAddress;

    private String employerCompanyInfoUniformSocialCreditCode;

    private String employerCompanyInfoOrganizationCode;

    private String employerCompanyInfoBusinessScope;

    private String employerCompanyInfoAuditState;

    public EmployerCompanyInfo(String employerCompanyInfoName, String employerCompanyInfoCompanyType, String employerCompanyInfoBusinessState, String employerCompanyInfoFoundTime, String employerCompanyInfoRegisterAddress, String employerCompanyInfoUniformSocialCreditCode, String employerCompanyInfoOrganizationCode, String employerCompanyInfoBusinessScope, String employerCompanyInfoAuditState) {
        this.employerCompanyInfoName = employerCompanyInfoName;
        this.employerCompanyInfoCompanyType = employerCompanyInfoCompanyType;
        this.employerCompanyInfoBusinessState = employerCompanyInfoBusinessState;
        this.employerCompanyInfoFoundTime = employerCompanyInfoFoundTime;
        this.employerCompanyInfoRegisterAddress = employerCompanyInfoRegisterAddress;
        this.employerCompanyInfoUniformSocialCreditCode = employerCompanyInfoUniformSocialCreditCode;
        this.employerCompanyInfoOrganizationCode = employerCompanyInfoOrganizationCode;
        this.employerCompanyInfoBusinessScope = employerCompanyInfoBusinessScope;
        this.employerCompanyInfoAuditState = employerCompanyInfoAuditState;
    }

    public Integer getEmployerCompanyInfoId() {
        return employerCompanyInfoId;
    }

    public void setEmployerCompanyInfoId(Integer employerCompanyInfoId) {
        this.employerCompanyInfoId = employerCompanyInfoId;
    }

    public String getEmployerCompanyInfoName() {
        return employerCompanyInfoName;
    }

    public void setEmployerCompanyInfoName(String employerCompanyInfoName) {
        this.employerCompanyInfoName = employerCompanyInfoName == null ? null : employerCompanyInfoName.trim();
    }

    public String getEmployerCompanyInfoCompanyType() {
        return employerCompanyInfoCompanyType;
    }

    public void setEmployerCompanyInfoCompanyType(String employerCompanyInfoCompanyType) {
        this.employerCompanyInfoCompanyType = employerCompanyInfoCompanyType == null ? null : employerCompanyInfoCompanyType.trim();
    }

    public String getEmployerCompanyInfoBusinessState() {
        return employerCompanyInfoBusinessState;
    }

    public void setEmployerCompanyInfoBusinessState(String employerCompanyInfoBusinessState) {
        this.employerCompanyInfoBusinessState = employerCompanyInfoBusinessState == null ? null : employerCompanyInfoBusinessState.trim();
    }

    public String getEmployerCompanyInfoFoundTime() {
        return employerCompanyInfoFoundTime;
    }

    public void setEmployerCompanyInfoFoundTime(String employerCompanyInfoFoundTime) {
        this.employerCompanyInfoFoundTime = employerCompanyInfoFoundTime == null ? null : employerCompanyInfoFoundTime.trim();
    }

    public String getEmployerCompanyInfoRegisterAddress() {
        return employerCompanyInfoRegisterAddress;
    }

    public void setEmployerCompanyInfoRegisterAddress(String employerCompanyInfoRegisterAddress) {
        this.employerCompanyInfoRegisterAddress = employerCompanyInfoRegisterAddress == null ? null : employerCompanyInfoRegisterAddress.trim();
    }

    public String getEmployerCompanyInfoUniformSocialCreditCode() {
        return employerCompanyInfoUniformSocialCreditCode;
    }

    public void setEmployerCompanyInfoUniformSocialCreditCode(String employerCompanyInfoUniformSocialCreditCode) {
        this.employerCompanyInfoUniformSocialCreditCode = employerCompanyInfoUniformSocialCreditCode == null ? null : employerCompanyInfoUniformSocialCreditCode.trim();
    }

    public String getEmployerCompanyInfoOrganizationCode() {
        return employerCompanyInfoOrganizationCode;
    }

    public void setEmployerCompanyInfoOrganizationCode(String employerCompanyInfoOrganizationCode) {
        this.employerCompanyInfoOrganizationCode = employerCompanyInfoOrganizationCode == null ? null : employerCompanyInfoOrganizationCode.trim();
    }

    public String getEmployerCompanyInfoBusinessScope() {
        return employerCompanyInfoBusinessScope;
    }

    public void setEmployerCompanyInfoBusinessScope(String employerCompanyInfoBusinessScope) {
        this.employerCompanyInfoBusinessScope = employerCompanyInfoBusinessScope == null ? null : employerCompanyInfoBusinessScope.trim();
    }

    public String getEmployerCompanyInfoAuditState() {
        return employerCompanyInfoAuditState;
    }

    public void setEmployerCompanyInfoAuditState(String employerCompanyInfoAuditState) {
        this.employerCompanyInfoAuditState = employerCompanyInfoAuditState == null ? null : employerCompanyInfoAuditState.trim();
    }

    @Override
    public String toString() {
        return "EmployerCompanyInfo{" +
                "employerCompanyInfoId=" + employerCompanyInfoId +
                ", employerCompanyInfoName='" + employerCompanyInfoName + '\'' +
                ", employerCompanyInfoCompanyType='" + employerCompanyInfoCompanyType + '\'' +
                ", employerCompanyInfoBusinessState='" + employerCompanyInfoBusinessState + '\'' +
                ", employerCompanyInfoFoundTime='" + employerCompanyInfoFoundTime + '\'' +
                ", employerCompanyInfoRegisterAddress='" + employerCompanyInfoRegisterAddress + '\'' +
                ", employerCompanyInfoUniformSocialCreditCode='" + employerCompanyInfoUniformSocialCreditCode + '\'' +
                ", employerCompanyInfoOrganizationCode='" + employerCompanyInfoOrganizationCode + '\'' +
                ", employerCompanyInfoBusinessScope='" + employerCompanyInfoBusinessScope + '\'' +
                ", employerCompanyInfoAuditState='" + employerCompanyInfoAuditState + '\'' +
                '}';
    }
}