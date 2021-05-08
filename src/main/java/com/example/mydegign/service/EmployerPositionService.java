package com.example.mydegign.service;

import com.example.mydegign.entity.EmployerPosition;
import com.example.mydegign.mapper.EmployerPositionMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerPositionService {
    @Autowired
    private EmployerPositionMapper mapper;

    public int insertPosition(EmployerPosition position) {
        return mapper.insert(position);
    }

    public List<EmployerPosition> selectAllByAccountState(Integer accountId, String state) {
        return mapper.selectAllByAccountState(accountId, state);
    }

    public List<EmployerPosition> selectAllByType(String type, String param) {
        return mapper.selectAllByType(type, param);
    }

    public EmployerPosition selectById(int positionId) {
        return mapper.selectByPrimaryKey(positionId);
    }

    public int updateByPrimaryKeySelective(EmployerPosition record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

}
