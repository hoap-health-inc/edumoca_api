package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.dtos.InstitutionDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface InstitutionService {
	InstitutionDto createInstitution(Institution institution);
	InstitutionDto updateInstitution(Institution institution, Integer institutionId);
	List<InstitutionDto> getAllInstitutions();
	InstitutionDto getInstitutionById(Integer institutionId);

	void deleteInstitution(Integer institutionId);
	Map<String, Set<InstitutionDto>> loadInstitution(XSSFSheet institutionSheet, String instituteSheetName);
}
