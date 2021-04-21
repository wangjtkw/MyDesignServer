package com.example.mydegign.service;

import com.example.mydegign.entity.UsersAccount;
import com.example.mydegign.mapper.UsersAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersAccountService {
    @Autowired
    private UsersAccountMapper mapper;

    public int insert(UsersAccount account) {
        return mapper.insert(account);
    }

    public UsersAccount selectByAccount(String account) {
        return mapper.selectByAccount(account);
    }

    public UsersAccount selectById(int accountId) {
        return mapper.selectByPrimaryKey(accountId);
    }

    public int updateByPrimaryKeySelective(UsersAccount record) {
        return mapper.updateByPrimaryKeySelective(record);
    }


}
