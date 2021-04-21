package com.example.mydegign.entity;

public class EducationExperiences {
    private Integer educationExperiencesId;

    private String educationExperiencesEducation;

    private String educationExperiencesSchool;

    private String educationExperiencesEnterDate;

    private String educationExperiencesMajor;

    public EducationExperiences(String educationExperiencesEducation, String educationExperiencesSchool, String educationExperiencesEnterDate, String educationExperiencesMajor) {
        this.educationExperiencesEducation = educationExperiencesEducation;
        this.educationExperiencesSchool = educationExperiencesSchool;
        this.educationExperiencesEnterDate = educationExperiencesEnterDate;
        this.educationExperiencesMajor = educationExperiencesMajor;
    }

    public Integer getEducationExperiencesId() {
        return educationExperiencesId;
    }

    public void setEducationExperiencesId(Integer educationExperiencesId) {
        this.educationExperiencesId = educationExperiencesId;
    }

    public String getEducationExperiencesEducation() {
        return educationExperiencesEducation;
    }

    public void setEducationExperiencesEducation(String educationExperiencesEducation) {
        this.educationExperiencesEducation = educationExperiencesEducation == null ? null : educationExperiencesEducation.trim();
    }

    public String getEducationExperiencesSchool() {
        return educationExperiencesSchool;
    }

    public void setEducationExperiencesSchool(String educationExperiencesSchool) {
        this.educationExperiencesSchool = educationExperiencesSchool == null ? null : educationExperiencesSchool.trim();
    }

    public String getEducationExperiencesEnterDate() {
        return educationExperiencesEnterDate;
    }

    public void setEducationExperiencesEnterDate(String educationExperiencesEnterDate) {
        this.educationExperiencesEnterDate = educationExperiencesEnterDate == null ? null : educationExperiencesEnterDate.trim();
    }

    public String getEducationExperiencesMajor() {
        return educationExperiencesMajor;
    }

    public void setEducationExperiencesMajor(String educationExperiencesMajor) {
        this.educationExperiencesMajor = educationExperiencesMajor == null ? null : educationExperiencesMajor.trim();
    }
}