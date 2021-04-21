package com.example.mydegign.entity;

public class PositionRequirement {
    private Integer positionRequirementId;

    private String positionRequirementAge;

    private String positionRequirementSex;

    private String positionRequirementHeight;

    private String positionRequirementEducation;

    public PositionRequirement(String positionRequirementAge, String positionRequirementSex, String positionRequirementHeight, String positionRequirementEducation) {
        this.positionRequirementAge = positionRequirementAge;
        this.positionRequirementSex = positionRequirementSex;
        this.positionRequirementHeight = positionRequirementHeight;
        this.positionRequirementEducation = positionRequirementEducation;
    }

    public Integer getPositionRequirementId() {
        return positionRequirementId;
    }

    public void setPositionRequirementId(Integer positionRequirementId) {
        this.positionRequirementId = positionRequirementId;
    }

    public String getPositionRequirementAge() {
        return positionRequirementAge;
    }

    public void setPositionRequirementAge(String positionRequirementAge) {
        this.positionRequirementAge = positionRequirementAge == null ? null : positionRequirementAge.trim();
    }

    public String getPositionRequirementSex() {
        return positionRequirementSex;
    }

    public void setPositionRequirementSex(String positionRequirementSex) {
        this.positionRequirementSex = positionRequirementSex == null ? null : positionRequirementSex.trim();
    }

    public String getPositionRequirementHeight() {
        return positionRequirementHeight;
    }

    public void setPositionRequirementHeight(String positionRequirementHeight) {
        this.positionRequirementHeight = positionRequirementHeight == null ? null : positionRequirementHeight.trim();
    }

    public String getPositionRequirementEducation() {
        return positionRequirementEducation;
    }

    public void setPositionRequirementEducation(String positionRequirementEducation) {
        this.positionRequirementEducation = positionRequirementEducation == null ? null : positionRequirementEducation.trim();
    }
}