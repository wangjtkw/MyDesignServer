package com.example.mydegign.mapper;

import com.example.mydegign.entity.EmployerAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerAccountMapper {
    int deleteByPrimaryKey(Integer employerAccountId);

    int insert(EmployerAccount record);

    EmployerAccount selectByPrimaryKey(Integer employerAccountId);

    EmployerAccount selectByAccount(String account);

    EmployerAccount selectByCompanyId(int companyId);

    List<EmployerAccount> selectAll();

    int updateByPrimaryKey(EmployerAccount record);

    int updateByPrimaryKeySelective(EmployerAccount record);
}