package com.example.mydegign.mapper;

import com.example.mydegign.entity.Records;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordsMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(Records record);

    Records selectByPrimaryKey(Integer recordId);

    Records selectByAllID(
            @Param("employerAccountId") Integer employerAccountId,
            @Param("positionId") Integer positionId,
            @Param("userAccountId") Integer userAccountId);


    List<Records> selectAll();

    List<Records> selectAllByUserAccountId(int userAccountId);

    List<Records> selectAllByEmployerAccountId(int employerAccountId);

    List<Records> selectAllByEmployerAccountIdType(
            @Param("employerAccountId") int employerAccountId,
            @Param("type") String type);


    int updateByPrimaryKey(Records record);

    int updateByPrimaryKeySelective(Records record);
}