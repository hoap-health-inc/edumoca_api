package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.GradeSectionInstitutionYearMap;
import com.edumoca.soma.entities.dtos.GradeSectionInstitutionYearMapDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GradeSectionInstitutionYearMappingService {
	GradeSectionInstitutionYearMapDto createGradeSectionInstitutionYearMapping(GradeSectionInstitutionYearMap gradeSectionInstitutionYearMap);
	GradeSectionInstitutionYearMapDto updateGradeSectionInstitutionYearMapping(GradeSectionInstitutionYearMap gradeSectionInstitutionYearMap, Integer gradeSectionInstitutionYearMappingId);
	List<GradeSectionInstitutionYearMapDto> getAllGradeSectionInstitutionYearMappingByInstitution(Integer institutionId);
	GradeSectionInstitutionYearMapDto getGradeSectionInstitutionYearMappingByInstitutionAndGradeSectionInstitutionYearMapping(Integer institutionId, Integer gradeSectionYearInstitutionId);

	Map<String, Set<GradeSectionInstitutionYearMapDto>> loadGradeSectionInstitutionYearMappings(XSSFSheet gradeSectionInstitutionYearMappingSheet, String gradeSectionInstitutionYearMappingSheetName);
}
