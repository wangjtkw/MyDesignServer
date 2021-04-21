package com.example.mydegign.mapper;

import com.example.mydegign.entity.EmployerPosition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerPositionMapper {
    int deleteByPrimaryKey(Integer employerPositionId);

    int insert(EmployerPosition record);

    EmployerPosition selectByPrimaryKey(Integer employerPositionId);

    List<EmployerPosition> selectAll();

    List<EmployerPosition> selectAllByAccountState(@Param("accountId") Integer accountId, @Param("state") String state);

    List<EmployerPosition> selectAllByType(String param);

    int updateByPrimaryKey(EmployerPosition record);
}