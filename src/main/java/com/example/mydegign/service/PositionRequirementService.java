package com.example.mydegign.service;

import com.example.mydegign.entity.PositionRequirement;
import com.example.mydegign.mapper.PositionRequirementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionRequirementService {
    @Autowired
    private PositionRequirementMapper mapper;

    public int insert(PositionRequirement requirement) {
        return mapper.insert(requirement);
    }

    public PositionRequirement selectById(int positionId) {
        return mapper.selectByPrimaryKey(positionId);
    }

}
