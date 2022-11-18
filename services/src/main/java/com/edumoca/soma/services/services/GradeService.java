package com.edumoca.soma.services.services;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.edumoca.soma.entities.Grade;
import com.edumoca.soma.entities.dtos.GradeDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface GradeService {
	GradeDTO createGrade(Grade grade);
	GradeDTO updateGrade(Grade grade,Integer gradeId);
	List<GradeDTO> getAllGradesByInstitution(Integer institutionId);
	GradeDTO getGradeByInstitutionAndGrade(Integer institutionId,Integer gradeId);

	Map<String, Set<GradeDTO>> loadGrades(XSSFSheet gradeSheet, String gradeSheetName);
}
