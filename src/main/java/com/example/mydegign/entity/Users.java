package com.example.mydegign.entity;

public class Users {
    private Integer usersId;

    private Integer curriculumVitaeId;

    private Integer userPersonalTagId;

    private Integer educationExperiencesId;

    private String usersImg;

    private String usersName;

    private String usersSex;

    private String usersBirthday;

    private String usersRole;

    private String usersPhoneNum;

    private String usersWechat;

    private String usersQq;

    private String usersSelfDescription;

    public Users(Integer educationExperiencesId, String usersImg, String usersName, String usersSex, String usersBirthday, String usersRole, String usersPhoneNum, String usersWechat, String usersQq) {
        this.educationExperiencesId = educationExperiencesId;
        this.usersImg = usersImg;
        this.usersName = usersName;
        this.usersSex = usersSex;
        this.usersBirthday = usersBirthday;
        this.usersRole = usersRole;
        this.usersPhoneNum = usersPhoneNum;
        this.usersWechat = usersWechat;
        this.usersQq = usersQq;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public Integer getCurriculumVitaeId() {
        return curriculumVitaeId;
    }

    public void setCurriculumVitaeId(Integer curriculumVitaeId) {
        this.curriculumVitaeId = curriculumVitaeId;
    }

    public Integer getUserPersonalTagId() {
        return userPersonalTagId;
    }

    public void setUserPersonalTagId(Integer userPersonalTagId) {
        this.userPersonalTagId = userPersonalTagId;
    }

    public Integer getEducationExperiencesId() {
        return educationExperiencesId;
    }

    public void setEducationExperiencesId(Integer educationExperiencesId) {
        this.educationExperiencesId = educationExperiencesId;
    }

    public String getUsersImg() {
        return usersImg;
    }

    public void setUsersImg(String usersImg) {
        this.usersImg = usersImg == null ? null : usersImg.trim();
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName == null ? null : usersName.trim();
    }

    public String getUsersSex() {
        return usersSex;
    }

    public void setUsersSex(String usersSex) {
        this.usersSex = usersSex == null ? null : usersSex.trim();
    }

    public String getUsersBirthday() {
        return usersBirthday;
    }

    public void setUsersBirthday(String usersBirthday) {
        this.usersBirthday = usersBirthday == null ? null : usersBirthday.trim();
    }

    public String getUsersRole() {
        return usersRole;
    }

    public void setUsersRole(String usersRole) {
        this.usersRole = usersRole == null ? null : usersRole.trim();
    }

    public String getUsersPhoneNum() {
        return usersPhoneNum;
    }

    public void setUsersPhoneNum(String usersPhoneNum) {
        this.usersPhoneNum = usersPhoneNum == null ? null : usersPhoneNum.trim();
    }

    public String getUsersWechat() {
        return usersWechat;
    }

    public void setUsersWechat(String usersWechat) {
        this.usersWechat = usersWechat == null ? null : usersWechat.trim();
    }

    public String getUsersQq() {
        return usersQq;
    }

    public void setUsersQq(String usersQq) {
        this.usersQq = usersQq == null ? null : usersQq.trim();
    }

    public String getUsersSelfDescription() {
        return usersSelfDescription;
    }

    public void setUsersSelfDescription(String usersSelfDescription) {
        this.usersSelfDescription = usersSelfDescription == null ? null : usersSelfDescription.trim();
    }
}