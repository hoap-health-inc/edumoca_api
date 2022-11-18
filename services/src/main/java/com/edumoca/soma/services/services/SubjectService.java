package com.edumoca.soma.services.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.edumoca.soma.entities.dtos.SubjectDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.edumoca.soma.entities.Subject;

public interface SubjectService {

	SubjectDTO createSubject(Subject subject);
	SubjectDTO updateSubject(Subject subject,Integer subjectId);
	List<SubjectDTO> getAllSubjectsByInstitution(Integer institutionId);
	SubjectDTO getSubjectByInstitutionAndSubject(Integer institutionId,Integer subjectId);

	Map<String, Set<SubjectDTO>> loadSubjects(XSSFSheet subjectsSheet, String subjectsSheetName);
}
