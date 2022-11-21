package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.entities.dtos.SubjectDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SubjectService {

	SubjectDto createSubject(Subject subject);
	SubjectDto updateSubject(Subject subject, Integer subjectId);
	List<SubjectDto> getAllSubjectsByInstitution(Integer institutionId);
	SubjectDto getSubjectByInstitutionAndSubject(Integer institutionId, Integer subjectId);

	Map<String, Set<SubjectDto>> loadSubjects(XSSFSheet subjectsSheet, String subjectsSheetName);
}
