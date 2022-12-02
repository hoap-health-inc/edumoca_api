package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Grade;
import com.edumoca.soma.entities.dtos.GradeDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GradeService {
	GradeDto createGrade(Grade grade);
	GradeDto updateGrade(Grade grade, Integer gradeId);
	List<GradeDto> getAllGradesByInstitution(Integer institutionId);
	Grade getGradeByInstitutionAndGrade(Integer institutionId, Integer gradeId);

	Map<String, Set<GradeDto>> loadGrades(XSSFSheet gradeSheet, String gradeSheetName);
}
