package com.example.mydegign.mapper;

import com.example.mydegign.entity.EmployerCompanyInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerCompanyInfoMapper {
    int deleteByPrimaryKey(Integer employerCompanyInfoId);

    int insert(EmployerCompanyInfo record);

    EmployerCompanyInfo selectByPrimaryKey(Integer employerCompanyInfoId);

    List<EmployerCompanyInfo> selectAll();

    List<EmployerCompanyInfo> selectAllByState(String state);

    int updateByPrimaryKey(EmployerCompanyInfo record);

    int updateByPrimaryKeySelective(EmployerCompanyInfo record);

}