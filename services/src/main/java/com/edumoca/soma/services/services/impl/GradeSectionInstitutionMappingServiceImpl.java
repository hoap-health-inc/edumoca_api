package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Grade;
import com.edumoca.soma.entities.GradeSectionInstitutionMap;
import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.Section;
import com.edumoca.soma.entities.dtos.GradeSectionInstitutionMapDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.GradeSectionInstitutionMapResponse;
import com.edumoca.soma.entities.repositories.GradeRepository;
import com.edumoca.soma.entities.repositories.GradeSectionInstitutionMapRepository;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import com.edumoca.soma.entities.repositories.SectionRepository;
import com.edumoca.soma.services.services.GradeSectionInstitutionMappingService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GradeSectionInstitutionMappingServiceImpl implements GradeSectionInstitutionMappingService {
    private final GradeSectionInstitutionMapRepository gradeSectionInstitutionMappingRepository;
	private final GradeRepository gradeRepository;
	private final SectionRepository sectionRepository;
	private final InstitutionRepository institutionRepository;
	private final ModelMapper modelMapper;


	@Override
	public GradeSectionInstitutionMapDto createGradeSectionInstitutionMapping(GradeSectionInstitutionMap gradeSectionInstitutionMap) {
		return modelMapper.map(gradeSectionInstitutionMappingRepository.save(gradeSectionInstitutionMap), GradeSectionInstitutionMapDto.class);
	}

	@Override
	public GradeSectionInstitutionMapDto updateGradeSectionInstitutionMapping(GradeSectionInstitutionMap gradeSectionInstitutionMap, Integer gradeSectionInstitutionMappingId) {
        Optional<GradeSectionInstitutionMapResponse> gradeSectionInstitutionMappingResponse1 = Optional.ofNullable(gradeSectionInstitutionMappingRepository.getGradeSectionInstitutionMappingByGradeSectionInstitutionMappingId(gradeSectionInstitutionMappingId));
		if(gradeSectionInstitutionMappingResponse1.isPresent())
			gradeSectionInstitutionMap.setGradeSectionInstitutionMappingId(gradeSectionInstitutionMappingId);
		return modelMapper.map(gradeSectionInstitutionMappingRepository.save(gradeSectionInstitutionMap), GradeSectionInstitutionMapDto.class);

	}

	@Override
	public List<GradeSectionInstitutionMapDto> getAllGradeSectionInstitutionMappingByInstitution(Integer institutionId) {
		return gradeSectionInstitutionMappingRepository.getAllGradeSectionInstitutionMappingByInstitutionId(institutionId).stream().map(e->{
			return modelMapper.map(e, GradeSectionInstitutionMapDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public GradeSectionInstitutionMapDto getGradeSectionInstitutionMappingByInstitutionAndGradeSectionInstitutionMappingId(Integer institutionId, Integer gradeSectionInstitutionMappingId) {
		Optional<GradeSectionInstitutionMapResponse> quickGradeSectionInstitutionMapping = Optional.ofNullable(gradeSectionInstitutionMappingRepository.getGradeSectionInstitutionMappingByInstitutionIdAndGradeSectionInstitutionMappingId(institutionId, gradeSectionInstitutionMappingId));
		if(quickGradeSectionInstitutionMapping.isPresent()){
			return modelMapper.map(quickGradeSectionInstitutionMapping.get(), GradeSectionInstitutionMapDto.class);
		}else
			throw new DataNotFoundException("GradeSectionInstitutionMapping with id not found");
	}

	@Override
	public Map<String, Set<GradeSectionInstitutionMapDto>> loadGradeSectionInstitutionMapping(XSSFSheet gradeSectionInstitutionMappingSheet, String gradeSectionInstitutionMappingSheetName) {
		Map<String,Set<GradeSectionInstitutionMapDto>> gradeSectionInstitutionMappingMap = new HashMap<>();
		Set<GradeSectionInstitutionMapDto> gradeSectionInstitutionMappingSet = new HashSet<>();
		gradeSectionInstitutionMappingSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				GradeSectionInstitutionMap gradeSectionInstitutionMap = new GradeSectionInstitutionMap();
				Optional<Grade> grade = gradeRepository.findById(new Double(row.getCell(0).getNumericCellValue()).intValue());
				grade.ifPresent(gradeSectionInstitutionMap::setGrade);
				Optional<Section> section = sectionRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				section.ifPresent(gradeSectionInstitutionMap::setSection);
				Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(2).getNumericCellValue()).intValue());
				institution.ifPresent(gradeSectionInstitutionMap::setInstitution);
				gradeSectionInstitutionMappingRepository.save(gradeSectionInstitutionMap);
				GradeSectionInstitutionMapDto gradeSectionInstitutionMappingDTO = new GradeSectionInstitutionMapDto();
				gradeSectionInstitutionMappingDTO.setGradeSectionInstitutionMappingId(gradeSectionInstitutionMap.getGradeSectionInstitutionMappingId());
				//gradeSectionInstitutionMappingDTO.setGradeName(gradeSectionInstitutionMapping.getGrade().getGradeName());
				//gradeSectionInstitutionMappingDTO.setSectionName(gradeSectionInstitutionMapping.getSection().getSectionName());
				//gradeSectionInstitutionMappingDTO.setInstitutionName(gradeSectionInstitutionMapping.getInstitution().getInstitutionName());
				gradeSectionInstitutionMappingSet.add(gradeSectionInstitutionMappingDTO);
			}
		});
		gradeSectionInstitutionMappingMap.put(gradeSectionInstitutionMappingSheetName,gradeSectionInstitutionMappingSet);
		return gradeSectionInstitutionMappingMap;
	}
}
