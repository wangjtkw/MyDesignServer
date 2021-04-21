package com.example.mydegign.entity.requestbean;

public class EmployerRequestBean {
    private String employerAccountAccount;

    private String employerAccountPassword;

    public EmployerRequestBean(String employerAccountAccount, String employerAccountPassword) {
        this.employerAccountAccount = employerAccountAccount;
        this.employerAccountPassword = employerAccountPassword;
    }

    public String getEmployerAccountAccount() {
        return employerAccountAccount;
    }

    public void setEmployerAccountAccount(String employerAccountAccount) {
        this.employerAccountAccount = employerAccountAccount;
    }

    public String getEmployerAccountPassword() {
        return employerAccountPassword;
    }

    public void setEmployerAccountPassword(String employerAccountPassword) {
        this.employerAccountPassword = employerAccountPassword;
    }
}
