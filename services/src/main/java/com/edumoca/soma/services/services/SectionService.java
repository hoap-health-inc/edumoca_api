package com.edumoca.soma.services.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.edumoca.soma.entities.Section;
import com.edumoca.soma.entities.dtos.SectionDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface SectionService {
	SectionDTO createSection(Section section);
	SectionDTO updateSection(Section section,Integer sectionId);
	List<SectionDTO> getAllSectionByInstitution(Integer institutionId);
	SectionDTO getSectionByInstitutionAndSection(Integer institutionId,Integer sectionId);

	Map<String, Set<SectionDTO>> loadSections(XSSFSheet sectionSheet, String sectionSheetName);
}
