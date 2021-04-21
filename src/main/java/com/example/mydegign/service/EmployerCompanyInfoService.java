package com.example.mydegign.service;

import com.example.mydegign.entity.EmployerCompanyInfo;
import com.example.mydegign.mapper.EmployerCompanyInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerCompanyInfoService {

    @Autowired
    private EmployerCompanyInfoMapper mapper;

    public int insert(EmployerCompanyInfo companyInfo) {
        return mapper.insert(companyInfo);
    }

    public EmployerCompanyInfo selectById(int companyInfoId) {
        return mapper.selectByPrimaryKey(companyInfoId);
    }

    public List<EmployerCompanyInfo> selectAllByState(String state) {
        return mapper.selectAllByState(state);
    }

    public int updateByPrimaryKeySelective(EmployerCompanyInfo companyInfo) {
        return mapper.updateByPrimaryKeySelective(companyInfo);
    }

}
