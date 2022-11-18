package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.dtos.AcademicYearDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.edumoca.soma.entities.AcademicYear;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AcademicYearService {
	AcademicYearDTO createAcademicYear(AcademicYear academicYear);
	AcademicYearDTO updateAcademicYear(AcademicYear academicYear,Integer academicYearId);
	List<AcademicYearDTO> getAcademicYearByInstitution(Integer institutionId);
	AcademicYearDTO getAcademicYearByInstitutionAndAcademicYear(Integer institutionId,Integer academicYearId);

	Map<String, Set<AcademicYearDTO>>  loadAcademicYear(XSSFSheet academicYearSheet,String academicYearSheetName);
}
