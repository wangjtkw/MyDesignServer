package com.example.mydegign.entity;

public class EmployerPosition {
    private Integer employerPositionId;

    private Integer positionRequirementId;

    private Integer employerAccountId;

    private String employerPositionTitle;

    private String employerPositionContent;

    private String employerPositionPersonNum;

    private String employerPositionImg;

    private String employerPositionSalary;

    private String employerPositionSettlement;

    private String employerPositionWelfare;

    private String employerPositionPlace;

    private String employerPositionDate;

    private String employerPositionConnectType;

    private String employerPositionConnectInfo;

    private String employerPositionPersonRequirements;

    private String employerPositionIndustry;

    private String employerPositionCity;

    private String employerPositionState;

    public EmployerPosition(Integer positionRequirementId, Integer employerAccountId, String employerPositionTitle, String employerPositionContent, String employerPositionPersonNum, String employerPositionImg, String employerPositionSalary, String employerPositionSettlement, String employerPositionWelfare, String employerPositionPlace, String employerPositionDate, String employerPositionConnectType, String employerPositionConnectInfo, String employerPositionPersonRequirements, String employerPositionIndustry, String employerPositionCity, String employerPositionState) {
        this.positionRequirementId = positionRequirementId;
        this.employerAccountId = employerAccountId;
        this.employerPositionTitle = employerPositionTitle;
        this.employerPositionContent = employerPositionContent;
        this.employerPositionPersonNum = employerPositionPersonNum;
        this.employerPositionImg = employerPositionImg;
        this.employerPositionSalary = employerPositionSalary;
        this.employerPositionSettlement = employerPositionSettlement;
        this.employerPositionWelfare = employerPositionWelfare;
        this.employerPositionPlace = employerPositionPlace;
        this.employerPositionDate = employerPositionDate;
        this.employerPositionConnectType = employerPositionConnectType;
        this.employerPositionConnectInfo = employerPositionConnectInfo;
        this.employerPositionPersonRequirements = employerPositionPersonRequirements;
        this.employerPositionIndustry = employerPositionIndustry;
        this.employerPositionCity = employerPositionCity;
        this.employerPositionState = employerPositionState;
    }

    public Integer getEmployerPositionId() {
        return employerPositionId;
    }

    public void setEmployerPositionId(Integer employerPositionId) {
        this.employerPositionId = employerPositionId;
    }

    public Integer getPositionRequirementId() {
        return positionRequirementId;
    }

    public void setPositionRequirementId(Integer positionRequirementId) {
        this.positionRequirementId = positionRequirementId;
    }

    public Integer getEmployerAccountId() {
        return employerAccountId;
    }

    public void setEmployerAccountId(Integer employerAccountId) {
        this.employerAccountId = employerAccountId;
    }

    public String getEmployerPositionTitle() {
        return employerPositionTitle;
    }

    public void setEmployerPositionTitle(String employerPositionTitle) {
        this.employerPositionTitle = employerPositionTitle == null ? null : employerPositionTitle.trim();
    }

    public String getEmployerPositionContent() {
        return employerPositionContent;
    }

    public void setEmployerPositionContent(String employerPositionContent) {
        this.employerPositionContent = employerPositionContent == null ? null : employerPositionContent.trim();
    }

    public String getEmployerPositionPersonNum() {
        return employerPositionPersonNum;
    }

    public void setEmployerPositionPersonNum(String employerPositionPersonNum) {
        this.employerPositionPersonNum = employerPositionPersonNum == null ? null : employerPositionPersonNum.trim();
    }

    public String getEmployerPositionImg() {
        return employerPositionImg;
    }

    public void setEmployerPositionImg(String employerPositionImg) {
        this.employerPositionImg = employerPositionImg == null ? null : employerPositionImg.trim();
    }

    public String getEmployerPositionSalary() {
        return employerPositionSalary;
    }

    public void setEmployerPositionSalary(String employerPositionSalary) {
        this.employerPositionSalary = employerPositionSalary == null ? null : employerPositionSalary.trim();
    }

    public String getEmployerPositionSettlement() {
        return employerPositionSettlement;
    }

    public void setEmployerPositionSettlement(String employerPositionSettlement) {
        this.employerPositionSettlement = employerPositionSettlement == null ? null : employerPositionSettlement.trim();
    }

    public String getEmployerPositionWelfare() {
        return employerPositionWelfare;
    }

    public void setEmployerPositionWelfare(String employerPositionWelfare) {
        this.employerPositionWelfare = employerPositionWelfare == null ? null : employerPositionWelfare.trim();
    }

    public String getEmployerPositionPlace() {
        return employerPositionPlace;
    }

    public void setEmployerPositionPlace(String employerPositionPlace) {
        this.employerPositionPlace = employerPositionPlace == null ? null : employerPositionPlace.trim();
    }

    public String getEmployerPositionDate() {
        return employerPositionDate;
    }

    public void setEmployerPositionDate(String employerPositionDate) {
        this.employerPositionDate = employerPositionDate == null ? null : employerPositionDate.trim();
    }

    public String getEmployerPositionConnectType() {
        return employerPositionConnectType;
    }

    public void setEmployerPositionConnectType(String employerPositionConnectType) {
        this.employerPositionConnectType = employerPositionConnectType == null ? null : employerPositionConnectType.trim();
    }

    public String getEmployerPositionConnectInfo() {
        return employerPositionConnectInfo;
    }

    public void setEmployerPositionConnectInfo(String employerPositionConnectInfo) {
        this.employerPositionConnectInfo = employerPositionConnectInfo == null ? null : employerPositionConnectInfo.trim();
    }

    public String getEmployerPositionPersonRequirements() {
        return employerPositionPersonRequirements;
    }

    public void setEmployerPositionPersonRequirements(String employerPositionPersonRequirements) {
        this.employerPositionPersonRequirements = employerPositionPersonRequirements == null ? null : employerPositionPersonRequirements.trim();
    }

    public String getEmployerPositionIndustry() {
        return employerPositionIndustry;
    }

    public void setEmployerPositionIndustry(String employerPositionIndustry) {
        this.employerPositionIndustry = employerPositionIndustry == null ? null : employerPositionIndustry.trim();
    }

    public String getEmployerPositionCity() {
        return employerPositionCity;
    }

    public void setEmployerPositionCity(String employerPositionCity) {
        this.employerPositionCity = employerPositionCity == null ? null : employerPositionCity.trim();
    }

    public String getEmployerPositionState() {
        return employerPositionState;
    }

    public void setEmployerPositionState(String employerPositionState) {
        this.employerPositionState = employerPositionState == null ? null : employerPositionState.trim();
    }

    @Override
    public String toString() {
        return "EmployerPosition{" +
                "employerPositionId=" + employerPositionId +
                ", positionRequirementId=" + positionRequirementId +
                ", employerAccountId=" + employerAccountId +
                ", employerPositionTitle='" + employerPositionTitle + '\'' +
                ", employerPositionContent='" + employerPositionContent + '\'' +
                ", employerPositionPersonNum='" + employerPositionPersonNum + '\'' +
                ", employerPositionImg='" + employerPositionImg + '\'' +
                ", employerPositionSalary='" + employerPositionSalary + '\'' +
                ", employerPositionSettlement='" + employerPositionSettlement + '\'' +
                ", employerPositionWelfare='" + employerPositionWelfare + '\'' +
                ", employerPositionPlace='" + employerPositionPlace + '\'' +
                ", employerPositionDate='" + employerPositionDate + '\'' +
                ", employerPositionConnectType='" + employerPositionConnectType + '\'' +
                ", employerPositionConnectInfo='" + employerPositionConnectInfo + '\'' +
                ", employerPositionPersonRequirements='" + employerPositionPersonRequirements + '\'' +
                ", employerPositionIndustry='" + employerPositionIndustry + '\'' +
                ", employerPositionCity='" + employerPositionCity + '\'' +
                ", employerPositionState='" + employerPositionState + '\'' +
                '}';
    }
}