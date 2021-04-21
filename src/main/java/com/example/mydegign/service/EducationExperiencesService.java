package com.example.mydegign.service;

import com.example.mydegign.entity.EducationExperiences;
import com.example.mydegign.mapper.EducationExperiencesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationExperiencesService {
    @Autowired
    private EducationExperiencesMapper mapper;

    public int insert(EducationExperiences educationExperiences) {
        return mapper.insert(educationExperiences);
    }

    public EducationExperiences selectById(int experienceId) {
        return mapper.selectByPrimaryKey(experienceId);
    }

    public int updateByPrimaryKeySelective(EducationExperiences record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
}
