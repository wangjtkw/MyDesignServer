package com.example.mydegign.service;

import com.example.mydegign.entity.Records;
import com.example.mydegign.mapper.RecordsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsService {

    @Autowired
    private RecordsMapper mapper;

    public int insert(Records records) {
        return mapper.insert(records);
    }

    public Records selectById(int recordsId) {
        return mapper.selectByPrimaryKey(recordsId);
    }

    public Records selectByAllID(
            Integer employerAccountId,
            Integer positionId,
            Integer userAccountId) {
        return mapper.selectByAllID(employerAccountId, positionId, userAccountId);
    }

    public List<Records> selectAllByUserAccountId(int userAccountId) {
        return mapper.selectAllByUserAccountId(userAccountId);
    }

    public List<Records> selectAllByEmployerAccountId(int employerAccountId) {
        return mapper.selectAllByEmployerAccountId(employerAccountId);
    }

    public List<Records> selectAllByEmployerAccountIdType(
            int employerAccountId,
            String type) {
        return mapper.selectAllByEmployerAccountIdType(employerAccountId, type);
    }

    public int updateByPrimaryKeySelective(Records record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

}
