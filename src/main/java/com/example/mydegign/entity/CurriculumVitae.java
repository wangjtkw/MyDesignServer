package com.example.mydegign.entity;

public class CurriculumVitae {
    private Integer curriculumVitaeId;

    private String curriculumVitaeEmile;

    public CurriculumVitae(String curriculumVitaeEmile) {
        this.curriculumVitaeEmile = curriculumVitaeEmile;
    }

    public Integer getCurriculumVitaeId() {
        return curriculumVitaeId;
    }

    public void setCurriculumVitaeId(Integer curriculumVitaeId) {
        this.curriculumVitaeId = curriculumVitaeId;
    }

    public String getCurriculumVitaeEmile() {
        return curriculumVitaeEmile;
    }

    public void setCurriculumVitaeEmile(String curriculumVitaeEmile) {
        this.curriculumVitaeEmile = curriculumVitaeEmile == null ? null : curriculumVitaeEmile.trim();
    }
}