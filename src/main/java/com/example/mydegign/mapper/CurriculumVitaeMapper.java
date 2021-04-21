package com.example.mydegign.mapper;

import com.example.mydegign.entity.CurriculumVitae;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CurriculumVitaeMapper {
    int deleteByPrimaryKey(Integer curriculumVitaeId);

    int insert(CurriculumVitae record);

    CurriculumVitae selectByPrimaryKey(Integer curriculumVitaeId);

    List<CurriculumVitae> selectAll();

    int updateByPrimaryKey(CurriculumVitae record);
}