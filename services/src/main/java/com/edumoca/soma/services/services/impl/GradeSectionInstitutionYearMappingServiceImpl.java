package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.dtos.GradeSectionInstitutionYearMappingDTO;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.GradeSectionInstitutionYearMappingResponse;
import com.edumoca.soma.entities.repositories.AcademicYearRepository;
import com.edumoca.soma.entities.repositories.GradeSectionInstitutionMappingRepository;
import com.edumoca.soma.entities.repositories.GradeSectionInstitutionYearMappingRepository;
import com.edumoca.soma.services.services.GradeSectionInstitutionYearMappingService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GradeSectionInstitutionYearMappingServiceImpl implements GradeSectionInstitutionYearMappingService {

	private final GradeSectionInstitutionYearMappingRepository gradeSectionInstitutionYearMappingRepository;
	private final GradeSectionInstitutionMappingRepository gradeSectionInstitutionMappingRepository;
	private final AcademicYearRepository academicYearRepository;

	private final ModelMapper modelMapper;

	@Override
	public GradeSectionInstitutionYearMappingDTO createGradeSectionInstitutionYearMapping(GradeSectionInstitutionYearMapping gradeSectionInstitutionYearMapping) {
		return modelMapper.map(gradeSectionInstitutionYearMappingRepository.save(gradeSectionInstitutionYearMapping),GradeSectionInstitutionYearMappingDTO.class);
	}

	@Override
	public GradeSectionInstitutionYearMappingDTO updateGradeSectionInstitutionYearMapping(GradeSectionInstitutionYearMapping gradeSectionInstitutionYearMapping,Integer gradeSectionInstitutionYearMappingId) {
		Optional<GradeSectionInstitutionYearMappingResponse> gradeSectionInstitutionYearMapping1 = Optional.ofNullable(gradeSectionInstitutionYearMappingRepository.getGradeSectionInstitutionYearMappingByGradeSectionInstitutionYearMapId(gradeSectionInstitutionYearMappingId));
		if(gradeSectionInstitutionYearMapping1.isPresent())
			gradeSectionInstitutionYearMapping.setGradeSectionInstitutionYearMapId(gradeSectionInstitutionYearMappingId);
		return modelMapper.map(gradeSectionInstitutionYearMappingRepository.save(gradeSectionInstitutionYearMapping),GradeSectionInstitutionYearMappingDTO.class);
	}

	@Override
	public List<GradeSectionInstitutionYearMappingDTO> getAllGradeSectionInstitutionYearMappingByInstitution(Integer institutionId) {
		return gradeSectionInstitutionYearMappingRepository.getAllGradeSectionInstitutionYearMappingByInstitutionId(institutionId).stream().map(e->{
			GradeSectionInstitutionYearMappingDTO gradeSectionInstitutionYearMappingDTO = new GradeSectionInstitutionYearMappingDTO();
			BeanUtils.copyProperties(e,gradeSectionInstitutionYearMappingDTO);
			return gradeSectionInstitutionYearMappingDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public GradeSectionInstitutionYearMappingDTO getGradeSectionInstitutionYearMappingByInstitutionAndGradeSectionInstitutionYearMapping(Integer institutionId,Integer gradeSectionInstitutionYearMappingId) {
		Optional<GradeSectionInstitutionYearMappingResponse> quickGradeSectionInstitutionYearMapping = Optional.ofNullable(gradeSectionInstitutionYearMappingRepository.getGradeSectionInstitutionYearMappingByInstitutionIdAndGradeSectionInstitutionYearMapId(institutionId, gradeSectionInstitutionYearMappingId));
		if(quickGradeSectionInstitutionYearMapping.isPresent()) {
		   GradeSectionInstitutionYearMappingDTO gradeSectionInstitutionYearMappingDTO = new GradeSectionInstitutionYearMappingDTO();
		   BeanUtils.copyProperties(quickGradeSectionInstitutionYearMapping.get(),gradeSectionInstitutionYearMappingDTO);
		   return gradeSectionInstitutionYearMappingDTO;
		}else
			throw new DataNotFoundException("GradeSectionInstitutionYearMapping with id not found.");
	}

	@Override
	public Map<String, Set<GradeSectionInstitutionYearMappingDTO>> loadGradeSectionInstitutionYearMappings(XSSFSheet gradeSectionInstitutionYearMappingSheet, String gradeSectionInstitutionYearMappingSheetName) {
		Map<String,Set<GradeSectionInstitutionYearMappingDTO>> gradeSectionInstitutionYearMap = new HashMap<>();
		Set<GradeSectionInstitutionYearMappingDTO> gradeSectionInstitutionYearMappingSet = new HashSet<>();
		gradeSectionInstitutionYearMappingSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				GradeSectionInstitutionYearMapping gradeSectionInstitutionYearMapping = new GradeSectionInstitutionYearMapping();
				Optional<GradeSectionInstitutionMapping> gradeSectionInstitutionMapping = gradeSectionInstitutionMappingRepository.findById(new Double(row.getCell(0).getNumericCellValue()).intValue());
				gradeSectionInstitutionMapping.ifPresent(gradeSectionInstitutionYearMapping::setGradeSectionInstitutionMapping);
				Optional<AcademicYear> academicYear = academicYearRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				academicYear.ifPresent(gradeSectionInstitutionYearMapping::setAcademicYear);
				gradeSectionInstitutionYearMappingRepository.save(gradeSectionInstitutionYearMapping);
				GradeSectionInstitutionYearMappingDTO gradeSectionInstitutionYearMappingDTO = new GradeSectionInstitutionYearMappingDTO();
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
