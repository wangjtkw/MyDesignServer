package com.example.mydegign.service;

import com.example.mydegign.entity.EmployerPersonalInfo;
import com.example.mydegign.mapper.EmployerPersonalInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerPersonalInfoService {

    @Autowired
    private EmployerPersonalInfoMapper mapper;

    public int insert(EmployerPersonalInfo employerPersonalInfo) {
        return mapper.insert(employerPersonalInfo);
    }

    public EmployerPersonalInfo selectById(int personalId) {
        return mapper.selectByPrimaryKey(personalId);
    }

    public int update(EmployerPersonalInfo employerPersonalInfo) {
        return mapper.updateByPrimaryKeySelective(employerPersonalInfo);
    }
}
