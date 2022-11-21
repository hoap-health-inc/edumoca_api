package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.AcademicYear;
import com.edumoca.soma.entities.GradeSectionInstitutionMap;
import com.edumoca.soma.entities.GradeSectionInstitutionYearMap;
import com.edumoca.soma.entities.dtos.GradeSectionInstitutionYearMapDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.GradeSectionInstitutionYearMapResponse;
import com.edumoca.soma.entities.repositories.AcademicYearRepository;
import com.edumoca.soma.entities.repositories.GradeSectionInstitutionMapRepository;
import com.edumoca.soma.entities.repositories.GradeSectionInstitutionYearMapRepository;
import com.edumoca.soma.services.services.GradeSectionInstitutionYearMappingService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GradeSectionInstitutionYearMappingServiceImpl implements GradeSectionInstitutionYearMappingService {

	private final GradeSectionInstitutionYearMapRepository gradeSectionInstitutionYearMappingRepository;
	private final GradeSectionInstitutionMapRepository gradeSectionInstitutionMappingRepository;
	private final AcademicYearRepository academicYearRepository;

	private final ModelMapper modelMapper;

	@Override
	public GradeSectionInstitutionYearMapDto createGradeSectionInstitutionYearMapping(GradeSectionInstitutionYearMap gradeSectionInstitutionYearMap) {
		return modelMapper.map(gradeSectionInstitutionYearMappingRepository.save(gradeSectionInstitutionYearMap), GradeSectionInstitutionYearMapDto.class);
	}

	@Override
	public GradeSectionInstitutionYearMapDto updateGradeSectionInstitutionYearMapping(GradeSectionInstitutionYearMap gradeSectionInstitutionYearMap, Integer gradeSectionInstitutionYearMappingId) {
		Optional<GradeSectionInstitutionYearMapResponse> gradeSectionInstitutionYearMapping1 = Optional.ofNullable(gradeSectionInstitutionYearMappingRepository.getGradeSectionInstitutionYearMappingByGradeSectionInstitutionYearMapId(gradeSectionInstitutionYearMappingId));
		if(gradeSectionInstitutionYearMapping1.isPresent())
			gradeSectionInstitutionYearMap.setGradeSectionInstitutionYearMapId(gradeSectionInstitutionYearMappingId);
		return modelMapper.map(gradeSectionInstitutionYearMappingRepository.save(gradeSectionInstitutionYearMap), GradeSectionInstitutionYearMapDto.class);
	}

	@Override
	public List<GradeSectionInstitutionYearMapDto> getAllGradeSectionInstitutionYearMappingByInstitution(Integer institutionId) {
		return gradeSectionInstitutionYearMappingRepository.getAllGradeSectionInstitutionYearMappingByInstitutionId(institutionId).stream().map(e->{
			GradeSectionInstitutionYearMapDto gradeSectionInstitutionYearMappingDTO = new GradeSectionInstitutionYearMapDto();
			BeanUtils.copyProperties(e,gradeSectionInstitutionYearMappingDTO);
			return gradeSectionInstitutionYearMappingDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public GradeSectionInstitutionYearMapDto getGradeSectionInstitutionYearMappingByInstitutionAndGradeSectionInstitutionYearMapping(Integer institutionId, Integer gradeSectionInstitutionYearMappingId) {
		Optional<GradeSectionInstitutionYearMapResponse> quickGradeSectionInstitutionYearMapping = Optional.ofNullable(gradeSectionInstitutionYearMappingRepository.getGradeSectionInstitutionYearMappingByInstitutionIdAndGradeSectionInstitutionYearMapId(institutionId, gradeSectionInstitutionYearMappingId));
		if(quickGradeSectionInstitutionYearMapping.isPresent()) {
		   GradeSectionInstitutionYearMapDto gradeSectionInstitutionYearMappingDTO = new GradeSectionInstitutionYearMapDto();
		   BeanUtils.copyProperties(quickGradeSectionInstitutionYearMapping.get(),gradeSectionInstitutionYearMappingDTO);
		   return gradeSectionInstitutionYearMappingDTO;
		}else
			throw new DataNotFoundException("GradeSectionInstitutionYearMapping with id not found.");
	}

	@Override
	public Map<String, Set<GradeSectionInstitutionYearMapDto>> loadGradeSectionInstitutionYearMappings(XSSFSheet gradeSectionInstitutionYearMappingSheet, String gradeSectionInstitutionYearMappingSheetName) {
		Map<String,Set<GradeSectionInstitutionYearMapDto>> gradeSectionInstitutionYearMap = new HashMap<>();
		Set<GradeSectionInstitutionYearMapDto> gradeSectionInstitutionYearMappingSet = new HashSet<>();
		gradeSectionInstitutionYearMappingSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				GradeSectionInstitutionYearMap gradeSectionInstitutionYearMapping = new GradeSectionInstitutionYearMap();
				Optional<GradeSectionInstitutionMap> gradeSectionInstitutionMapping = gradeSectionInstitutionMappingRepository.findById(new Double(row.getCell(0).getNumericCellValue()).intValue());
				gradeSectionInstitutionMapping.ifPresent(gradeSectionInstitutionYearMapping::setGradeSectionInstitutionMap);
				Optional<AcademicYear> academicYear = academicYearRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				academicYear.ifPresent(gradeSectionInstitutionYearMapping::setAcademicYear);
				gradeSectionInstitutionYearMappingRepository.save(gradeSectionInstitutionYearMapping);
				GradeSectionInstitutionYearMapDto gradeSectionInstitutionYearMappingDTO = new GradeSectionInstitutionYearMapDto();
				gradeSectionInstitutionYearMappingDTO.setGradeSectionInstitutionYearMappingId(gradeSectionInstitutionYearMapping.getGradeSectionInstitutionYearMapId());
				//gradeSectionInstitutionYearMappingDTO.setGradeName(gradeSectionInstitutionYearMapping.getGradeSectionInstitutionMapping().getGrade().getGradeName());
				//gradeSectionInstitutionYearMappingDTO.setSectionName(gradeSectionInstitutionYearMapping.getGradeSectionInstitutionMapping().getSection().getSectionName());
				//gradeSectionInstitutionYearMappingDTO.setInstitutionName(gradeSectionInstitutionYearMapping.getGradeSectionInstitutionMapping().getInstitution().getInstitutionName());
				//gradeSectionInstitutionYearMappingDTO.setAcademicStartDate(gradeSectionInstitutionYearMapping.getAcademicYear().getStartDate());
				//gradeSectionInstitutionYearMappingDTO.setAcademicEndDate(gradeSectionInstitutionYearMapping.getAcademicYear().getEndDate());
                gradeSectionInstitutionYearMappingSet.add(gradeSectionInstitutionYearMappingDTO);
			}
		});
		gradeSectionInstitutionYearMap.put(gradeSectionInstitutionYearMappingSheetName,gradeSectionInstitutionYearMappingSet);
		return gradeSectionInstitutionYearMap;
	}
}
