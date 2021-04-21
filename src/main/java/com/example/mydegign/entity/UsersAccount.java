package com.example.mydegign.entity;

public class UsersAccount {
    private Integer usersAccountId;

    private Integer usersId;

    private String usersAccountAccount;

    private String usersAccountPassword;

    public UsersAccount(String usersAccountAccount, String usersAccountPassword) {
        this.usersAccountAccount = usersAccountAccount;
        this.usersAccountPassword = usersAccountPassword;
    }

    public Integer getUsersAccountId() {
        return usersAccountId;
    }

    public void setUsersAccountId(Integer usersAccountId) {
        this.usersAccountId = usersAccountId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getUsersAccountAccount() {
        return usersAccountAccount;
    }

    public void setUsersAccountAccount(String usersAccountAccount) {
        this.usersAccountAccount = usersAccountAccount == null ? null : usersAccountAccount.trim();
    }

    public String getUsersAccountPassword() {
        return usersAccountPassword;
    }

    public void setUsersAccountPassword(String usersAccountPassword) {
        this.usersAccountPassword = usersAccountPassword == null ? null : usersAccountPassword.trim();
    }
}