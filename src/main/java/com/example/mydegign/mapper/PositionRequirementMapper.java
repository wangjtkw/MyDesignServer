package com.example.mydegign.mapper;

import com.example.mydegign.entity.PositionRequirement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRequirementMapper {
    int deleteByPrimaryKey(Integer positionRequirementId);

    int insert(PositionRequirement record);

    PositionRequirement selectByPrimaryKey(Integer positionRequirementId);

    List<PositionRequirement> selectAll();

    int updateByPrimaryKey(PositionRequirement record);
}