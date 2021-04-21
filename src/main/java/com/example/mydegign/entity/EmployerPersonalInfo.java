package com.example.mydegign.entity;

public class EmployerPersonalInfo {
    private Integer employerPersonalInfoId;

    private String employerPersonalInfoHead;

    private String employerPersonalInfoName;

    private String employerPersonalInfoIdCardNum;

    private String employerPersonalInfoIdCardFront;

    private String employerPersonalInfoIdCardBack;

    public EmployerPersonalInfo(Integer employerPersonalInfoId, String employerPersonalInfoHead, String employerPersonalInfoName, String employerPersonalInfoIdCardNum, String employerPersonalInfoIdCardFront, String employerPersonalInfoIdCardBack) {
        this.employerPersonalInfoId = employerPersonalInfoId;
        this.employerPersonalInfoHead = employerPersonalInfoHead;
        this.employerPersonalInfoName = employerPersonalInfoName;
        this.employerPersonalInfoIdCardNum = employerPersonalInfoIdCardNum;
        this.employerPersonalInfoIdCardFront = employerPersonalInfoIdCardFront;
        this.employerPersonalInfoIdCardBack = employerPersonalInfoIdCardBack;
    }

    public EmployerPersonalInfo(String employerPersonalInfoHead, String employerPersonalInfoName, String employerPersonalInfoIdCardNum, String employerPersonalInfoIdCardFront, String employerPersonalInfoIdCardBack) {
        this.employerPersonalInfoHead = employerPersonalInfoHead;
        this.employerPersonalInfoName = employerPersonalInfoName;
        this.employerPersonalInfoIdCardNum = employerPersonalInfoIdCardNum;
        this.employerPersonalInfoIdCardFront = employerPersonalInfoIdCardFront;
        this.employerPersonalInfoIdCardBack = employerPersonalInfoIdCardBack;
    }

    public Integer getEmployerPersonalInfoId() {
        return employerPersonalInfoId;
    }

    public void setEmployerPersonalInfoId(Integer employerPersonalInfoId) {
        this.employerPersonalInfoId = employerPersonalInfoId;
    }

    public String getEmployerPersonalInfoHead() {
        return employerPersonalInfoHead;
    }

    public void setEmployerPersonalInfoHead(String employerPersonalInfoHead) {
        this.employerPersonalInfoHead = employerPersonalInfoHead == null ? null : employerPersonalInfoHead.trim();
    }

    public String getEmployerPersonalInfoName() {
        return employerPersonalInfoName;
    }

    public void setEmployerPersonalInfoName(String employerPersonalInfoName) {
        this.employerPersonalInfoName = employerPersonalInfoName == null ? null : employerPersonalInfoName.trim();
    }

    public String getEmployerPersonalInfoIdCardNum() {
        return employerPersonalInfoIdCardNum;
    }

    public void setEmployerPersonalInfoIdCardNum(String employerPersonalInfoIdCardNum) {
        this.employerPersonalInfoIdCardNum = employerPersonalInfoIdCardNum == null ? null : employerPersonalInfoIdCardNum.trim();
    }

    public String getEmployerPersonalInfoIdCardFront() {
        return employerPersonalInfoIdCardFront;
    }

    public void setEmployerPersonalInfoIdCardFront(String employerPersonalInfoIdCardFront) {
        this.employerPersonalInfoIdCardFront = employerPersonalInfoIdCardFront == null ? null : employerPersonalInfoIdCardFront.trim();
    }

    public String getEmployerPersonalInfoIdCardBack() {
        return employerPersonalInfoIdCardBack;
    }

    public void setEmployerPersonalInfoIdCardBack(String employerPersonalInfoIdCardBack) {
        this.employerPersonalInfoIdCardBack = employerPersonalInfoIdCardBack == null ? null : employerPersonalInfoIdCardBack.trim();
    }
}