package com.example.mydegign.entity;

public class Records {
    private Integer recordId;

    private Integer employerAccountId;

    private Integer employerPositionId;

    private Integer usersAccountId;

    private String recordStateUser;

    private String recordStateEmployer;

    public Records(Integer employerAccountId, Integer employerPositionId, Integer usersAccountId, String recordStateUser, String recordStateEmployer) {
        this.employerAccountId = employerAccountId;
        this.employerPositionId = employerPositionId;
        this.usersAccountId = usersAccountId;
        this.recordStateUser = recordStateUser;
        this.recordStateEmployer = recordStateEmployer;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getEmployerAccountId() {
        return employerAccountId;
    }

    public void setEmployerAccountId(Integer employerAccountId) {
        this.employerAccountId = employerAccountId;
    }

    public Integer getEmployerPositionId() {
        return employerPositionId;
    }

    public void setEmployerPositionId(Integer employerPositionId) {
        this.employerPositionId = employerPositionId;
    }

    public Integer getUsersAccountId() {
        return usersAccountId;
    }

    public void setUsersAccountId(Integer usersAccountId) {
        this.usersAccountId = usersAccountId;
    }

    public String getRecordStateUser() {
        return recordStateUser;
    }

    public void setRecordStateUser(String recordStateUser) {
        this.recordStateUser = recordStateUser == null ? null : recordStateUser.trim();
    }

    public String getRecordStateEmployer() {
        return recordStateEmployer;
    }

    public void setRecordStateEmployer(String recordStateEmployer) {
        this.recordStateEmployer = recordStateEmployer == null ? null : recordStateEmployer.trim();
    }
}