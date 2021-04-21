package com.example.mydegign.service;

import com.example.mydegign.entity.CurriculumVitae;
import com.example.mydegign.mapper.CurriculumVitaeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurriculumVitaeService {

    @Autowired
    private CurriculumVitaeMapper mapper;

    public int insert(CurriculumVitae curriculumVitae) {
        return mapper.insert(curriculumVitae);
    }
}
