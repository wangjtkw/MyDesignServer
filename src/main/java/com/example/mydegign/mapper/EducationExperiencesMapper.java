package com.example.mydegign.mapper;

import com.example.mydegign.entity.EducationExperiences;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationExperiencesMapper {
    int deleteByPrimaryKey(Integer educationExperiencesId);

    int insert(EducationExperiences record);

    EducationExperiences selectByPrimaryKey(Integer educationExperiencesId);

    List<EducationExperiences> selectAll();

    int updateByPrimaryKey(EducationExperiences record);

    int updateByPrimaryKeySelective(EducationExperiences record);

}