package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Section;
import com.edumoca.soma.entities.dtos.SectionDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SectionService {
	SectionDto createSection(Section section);
	SectionDto updateSection(Section section, Integer sectionId);
	List<SectionDto> getAllSectionByInstitution(Integer institutionId);
	SectionDto getSectionByInstitutionAndSection(Integer institutionId, Integer sectionId);

	Map<String, Set<SectionDto>> loadSections(XSSFSheet sectionSheet, String sectionSheetName);
}
