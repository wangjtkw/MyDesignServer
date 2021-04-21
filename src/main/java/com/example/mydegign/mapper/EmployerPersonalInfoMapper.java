package com.example.mydegign.mapper;

import com.example.mydegign.entity.EmployerPersonalInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerPersonalInfoMapper {
    int deleteByPrimaryKey(Integer employerPersonalInfoId);

    int insert(EmployerPersonalInfo record);

    EmployerPersonalInfo selectByPrimaryKey(Integer employerPersonalInfoId);

    List<EmployerPersonalInfo> selectAll();

    int updateByPrimaryKey(EmployerPersonalInfo record);

    int updateByPrimaryKeySelective(EmployerPersonalInfo record);

}