package com.example.mydegign.entity.responsebean;

import com.example.mydegign.entity.EmployerCompanyInfo;
import com.example.mydegign.entity.EmployerPosition;
import com.example.mydegign.entity.PositionRequirement;

public class PositionResponseBean {
    private EmployerPosition position;
    private PositionRequirement positionRequirement;
    private EmployerCompanyInfo companyInfo;

    public PositionResponseBean(EmployerPosition position, PositionRequirement positionRequirement, EmployerCompanyInfo companyInfo) {
        this.position = position;
        this.positionRequirement = positionRequirement;
        this.companyInfo = companyInfo;
    }

    public EmployerPosition getPosition() {
        return position;
    }

    public void setPosition(EmployerPosition position) {
        this.position = position;
    }

    public PositionRequirement getPositionRequirement() {
        return positionRequirement;
    }

    public void setPositionRequirement(PositionRequirement positionRequirement) {
        this.positionRequirement = positionRequirement;
    }

    public EmployerCompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(EmployerCompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }
}

