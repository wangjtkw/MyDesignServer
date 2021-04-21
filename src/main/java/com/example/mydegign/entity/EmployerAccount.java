package com.example.mydegign.entity;

public class EmployerAccount {
    private Integer employerAccountId;

    private Integer employerPersonalInfoId;

    private Integer employerCompanyInfo;

    private String employerAccountAccount;

    private String employerAccountPassword;

    public EmployerAccount(String employerAccountAccount, String employerAccountPassword) {
        this.employerAccountAccount = employerAccountAccount;
        this.employerAccountPassword = employerAccountPassword;
    }

    public Integer getEmployerAccountId() {
        return employerAccountId;
    }

    public void setEmployerAccountId(Integer employerAccountId) {
        this.employerAccountId = employerAccountId;
    }

    public Integer getEmployerPersonalInfoId() {
        return employerPersonalInfoId;
    }

    public void setEmployerPersonalInfoId(Integer employerPersonalInfoId) {
        this.employerPersonalInfoId = employerPersonalInfoId;
    }

    public Integer getEmployerCompanyInfo() {
        return employerCompanyInfo;
    }

    public void setEmployerCompanyInfo(Integer employerCompanyInfo) {
        this.employerCompanyInfo = employerCompanyInfo;
    }

    public String getEmployerAccountAccount() {
        return employerAccountAccount;
    }

    public void setEmployerAccountAccount(String employerAccountAccount) {
        this.employerAccountAccount = employerAccountAccount == null ? null : employerAccountAccount.trim();
    }

    public String getEmployerAccountPassword() {
        return employerAccountPassword;
    }

    public void setEmployerAccountPassword(String employerAccountPassword) {
        this.employerAccountPassword = employerAccountPassword == null ? null : employerAccountPassword.trim();
    }

    @Override
    public String toString() {
        return "EmployerAccount{" +
                "employerAccountId=" + employerAccountId +
                ", employerPersonalInfoId=" + employerPersonalInfoId +
                ", employerCompanyInfo=" + employerCompanyInfo +
                ", employerAccountAccount='" + employerAccountAccount + '\'' +
                ", employerAccountPassword='" + employerAccountPassword + '\'' +
                '}';
    }
}