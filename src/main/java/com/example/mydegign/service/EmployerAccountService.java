package com.example.mydegign.service;

import com.example.mydegign.entity.EmployerAccount;
import com.example.mydegign.mapper.EmployerAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerAccountService {
    @Autowired
    private EmployerAccountMapper mapper;

    public int insert(EmployerAccount account) {
        return mapper.insert(account);
    }

    public EmployerAccount selectByAccount(String account) {
        return mapper.selectByAccount(account);
    }

    public EmployerAccount selectById(Integer accountId) {
        return mapper.selectByPrimaryKey(accountId);
    }

    public int updateSelective(EmployerAccount account) {
        return mapper.updateByPrimaryKeySelective(account);
    }

    public EmployerAccount selectByCompanyId(int companyId) {
        return mapper.selectByCompanyId(companyId);
    }

}
