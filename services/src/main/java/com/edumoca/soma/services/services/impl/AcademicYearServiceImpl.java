package com.edumoca.soma.services.services.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.edumoca.soma.entities.models.AcademicYearResponse;
import com.edumoca.soma.entities.dtos.AcademicYearDTO;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.AcademicYear;
import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.AcademicYearRepository;
import com.edumoca.soma.services.services.AcademicYearService;

@Service
@AllArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService{

	private final AcademicYearRepository academicYearRepository;
	private final InstitutionRepository institutionRepository;
	private final ModelMapper modelMapper;


	@Override
	public AcademicYearDTO createAcademicYear(AcademicYear academicYear) {
		return modelMapper.map(academicYearRepository.save(academicYear),AcademicYearDTO.class);
	}

	@Override
	public AcademicYearDTO updateAcademicYear(AcademicYear academicYear,Integer academicYearId) {
		Optional<AcademicYearResponse> academicYear1 = Optional.ofNullable(academicYearRepository.getAcademicYearByAcademicYearId(academicYearId));
		if(academicYear1.isPresent())
			academicYear.setId(academicYearId);
		return modelMapper.map(academicYearRepository.save(academicYear),AcademicYearDTO.class);
	}

	@Override
	public List<AcademicYearDTO> getAcademicYearByInstitution(Integer institutionId) {
		return academicYearRepository.getAllAcademicYearByInstitutionId(institutionId).stream().map(e->{
			return modelMapper.map(e,AcademicYearDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	public AcademicYearDTO getAcademicYearByInstitutionAndAcademicYear(Integer institutionId, Integer academicYearId) {
		Optional<AcademicYearResponse> academicYear = Optional.ofNullable(academicYearRepository.getAcademicYearByInstitutionIdAndAcademicYearId(institutionId, academicYearId));
		if(academicYear.isPresent()){
			return modelMapper.map(academicYear,AcademicYearDTO.class);
		}else
			throw new DataNotFoundException("AcademicYear with id not found");
	}

	@Override
	public Map<String, Set<AcademicYearDTO>> loadAcademicYear(XSSFSheet academicYearSheet, String academicYearSheetName) {
		Map<String,Set<AcademicYearDTO>> academicYearMap = new HashMap<>();
        Set<AcademicYearDTO> academicYearSet = new HashSet<>();
		academicYearSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0) {
				AcademicYear academicYear = new AcademicYear();
				//startDate
				Calendar startDate = Calendar.getInstance();
	        	startDate.setTime(row.getCell(0).getDateCellValue());
	        	academicYear.setStartDate(startDate);
	        	//endDate
	        	Calendar endDate = Calendar.getInstance();
	        	endDate.setTime(row.getCell(1).getDateCellValue());
	        	academicYear.setEndDate(endDate);
				Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(2).getNumericCellValue()).intValue());
				institution.ifPresent(academicYear::setInstitution);
	        	academicYearRepository.save(academicYear);
				AcademicYearDTO academicYearDTO = new AcademicYearDTO();
				academicYearDTO.setAcademicYearId(academicYear.getId());
				academicYearDTO.setStartDate(academicYear.getStartDate());
				academicYearDTO.setEndDate(academicYear.getEndDate());
				academicYearSet.add(academicYearDTO);
			}
		});
		academicYearMap.put(academicYearSheetName,academicYearSet);
		return academicYearMap;
	}
}
