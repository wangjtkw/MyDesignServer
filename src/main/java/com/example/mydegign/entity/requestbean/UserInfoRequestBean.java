package com.example.mydegign.entity.requestbean;

public class UserInfoRequestBean {
    private String usersAccountId;

    private String usersName;

    private String usersSex;

    private String usersBirthday;

    private String usersRole;

    private String usersPhoneNum;

    private String usersWechat;

    private String usersQq;

    private String educationExperiencesEducation;

    private String educationExperiencesSchool;

    private String educationExperiencesEnterDate;

    private String educationExperiencesMajor;

    public UserInfoRequestBean(String usersAccountId, String usersName, String usersSex, String usersBirthday, String usersRole, String usersPhoneNum, String usersWechat, String usersQq, String educationExperiencesEducation, String educationExperiencesSchool, String educationExperiencesEnterDate, String educationExperiencesMajor) {
        this.usersAccountId = usersAccountId;
        this.usersName = usersName;
        this.usersSex = usersSex;
        this.usersBirthday = usersBirthday;
        this.usersRole = usersRole;
        this.usersPhoneNum = usersPhoneNum;
        this.usersWechat = usersWechat;
        this.usersQq = usersQq;
        this.educationExperiencesEducation = educationExperiencesEducation;
        this.educationExperiencesSchool = educationExperiencesSchool;
        this.educationExperiencesEnterDate = educationExperiencesEnterDate;
        this.educationExperiencesMajor = educationExperiencesMajor;
    }

    public String getUsersAccountId() {
        return usersAccountId;
    }

    public void setUsersAccountId(String usersAccountId) {
        this.usersAccountId = usersAccountId;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getUsersSex() {
        return usersSex;
    }

    public void setUsersSex(String usersSex) {
        this.usersSex = usersSex;
    }

    public String getUsersBirthday() {
        return usersBirthday;
    }

    public void setUsersBirthday(String usersBirthday) {
        this.usersBirthday = usersBirthday;
    }

    public String getUsersRole() {
        return usersRole;
    }

    public void setUsersRole(String usersRole) {
        this.usersRole = usersRole;
    }

    public String getUsersPhoneNum() {
        return usersPhoneNum;
    }

    public void setUsersPhoneNum(String usersPhoneNum) {
        this.usersPhoneNum = usersPhoneNum;
    }

    public String getUsersWechat() {
        return usersWechat;
    }

    public void setUsersWechat(String usersWechat) {
        this.usersWechat = usersWechat;
    }

    public String getUsersQq() {
        return usersQq;
    }

    public void setUsersQq(String usersQq) {
        this.usersQq = usersQq;
    }

    public String getEducationExperiencesEducation() {
        return educationExperiencesEducation;
    }

    public void setEducationExperiencesEducation(String educationExperiencesEducation) {
        this.educationExperiencesEducation = educationExperiencesEducation;
    }

    public String getEducationExperiencesSchool() {
        return educationExperiencesSchool;
    }

    public void setEducationExperiencesSchool(String educationExperiencesSchool) {
        this.educationExperiencesSchool = educationExperiencesSchool;
    }

    public String getEducationExperiencesEnterDate() {
        return educationExperiencesEnterDate;
    }

    public void setEducationExperiencesEnterDate(String educationExperiencesEnterDate) {
        this.educationExperiencesEnterDate = educationExperiencesEnterDate;
    }

    public String getEducationExperiencesMajor() {
        return educationExperiencesMajor;
    }

    public void setEducationExperiencesMajor(String educationExperiencesMajor) {
        this.educationExperiencesMajor = educationExperiencesMajor;
    }
}
