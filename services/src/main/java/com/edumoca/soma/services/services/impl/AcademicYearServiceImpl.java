package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.AcademicYear;
import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.dtos.AcademicYearDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.AcademicYearResponse;
import com.edumoca.soma.entities.repositories.AcademicYearRepository;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import com.edumoca.soma.services.services.AcademicYearService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AcademicYearServiceImpl implements AcademicYearService{

	private final AcademicYearRepository academicYearRepository;
	private final InstitutionRepository institutionRepository;
	private final ModelMapper modelMapper;


	@Override
	public AcademicYearDto createAcademicYear(AcademicYear academicYear) {
		return modelMapper.map(academicYearRepository.save(academicYear), AcademicYearDto.class);
	}

	@Override
	public AcademicYearDto updateAcademicYear(AcademicYear academicYear, Integer academicYearId) {
		Optional<AcademicYearResponse> academicYear1 = Optional.ofNullable(academicYearRepository.getAcademicYearByAcademicYearId(academicYearId));
		if(academicYear1.isPresent())
			academicYear.setId(academicYearId);
		return modelMapper.map(academicYearRepository.save(academicYear), AcademicYearDto.class);
	}

	@Override
	public List<AcademicYearDto> getAcademicYearByInstitution(Integer institutionId) {
		return academicYearRepository.getAllAcademicYearByInstitutionId(institutionId).stream().map(e->{
			return modelMapper.map(e, AcademicYearDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public AcademicYearDto getAcademicYearByInstitutionAndAcademicYear(Integer institutionId, Integer academicYearId) {
		Optional<AcademicYearResponse> academicYear = Optional.ofNullable(academicYearRepository.getAcademicYearByInstitutionIdAndAcademicYearId(institutionId, academicYearId));
		if(academicYear.isPresent()){
			return modelMapper.map(academicYear, AcademicYearDto.class);
		}else
			throw new DataNotFoundException("AcademicYear with id not found");
	}

	@Override
	public Map<String, Set<AcademicYearDto>> loadAcademicYear(XSSFSheet academicYearSheet, String academicYearSheetName) {
		Map<String,Set<AcademicYearDto>> academicYearMap = new HashMap<>();
        Set<AcademicYearDto> academicYearSet = new HashSet<>();
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
				AcademicYearDto academicYearDTO = new AcademicYearDto();
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
