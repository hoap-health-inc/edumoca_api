package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.AcademicYear;
import com.edumoca.soma.entities.dtos.AcademicYearDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AcademicYearService {
	AcademicYearDto createAcademicYear(AcademicYear academicYear);
	AcademicYearDto updateAcademicYear(AcademicYear academicYear, Integer academicYearId);
	List<AcademicYearDto> getAcademicYearByInstitution(Integer institutionId);
	AcademicYearDto getAcademicYearByInstitutionAndAcademicYear(Integer institutionId, Integer academicYearId);

	Map<String, Set<AcademicYearDto>>  loadAcademicYear(XSSFSheet academicYearSheet, String academicYearSheetName);
}
