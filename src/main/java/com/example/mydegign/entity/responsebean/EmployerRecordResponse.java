package com.example.mydegign.entity.responsebean;

import com.example.mydegign.entity.EmployerPosition;
import com.example.mydegign.entity.Records;
import com.example.mydegign.entity.Users;

public class EmployerRecordResponse {
    Users users;
    Records records;
    EmployerPosition position;

    public EmployerRecordResponse(Users users, Records records, EmployerPosition position) {
        this.users = users;
        this.records = records;
        this.position = position;
    }

    public EmployerPosition getPosition() {
        return position;
    }

    public void setPosition(EmployerPosition position) {
        this.position = position;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records records) {
        this.records = records;
    }
}
