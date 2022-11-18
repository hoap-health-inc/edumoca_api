package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.dtos.GradeSectionInstitutionMappingDTO;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.GradeSectionInstitutionMappingResponse;
import com.edumoca.soma.entities.repositories.GradeRepository;
import com.edumoca.soma.entities.repositories.GradeSectionInstitutionMappingRepository;
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
    private final GradeSectionInstitutionMappingRepository gradeSectionInstitutionMappingRepository;
	private final GradeRepository gradeRepository;
	private final SectionRepository sectionRepository;
	private final InstitutionRepository institutionRepository;
	private final ModelMapper modelMapper;


	@Override
	public GradeSectionInstitutionMappingDTO createGradeSectionInstitutionMapping(GradeSectionInstitutionMapping gradeSectionInstitutionMapping) {
		return modelMapper.map(gradeSectionInstitutionMappingRepository.save(gradeSectionInstitutionMapping), GradeSectionInstitutionMappingDTO.class);
	}

	@Override
	public GradeSectionInstitutionMappingDTO updateGradeSectionInstitutionMapping(GradeSectionInstitutionMapping gradeSectionInstitutionMapping,Integer gradeSectionInstitutionMappingId) {
        Optional<GradeSectionInstitutionMappingResponse> gradeSectionInstitutionMappingResponse1 = Optional.ofNullable(gradeSectionInstitutionMappingRepository.getGradeSectionInstitutionMappingByGradeSectionInstitutionMappingId(gradeSectionInstitutionMappingId));
		if(gradeSectionInstitutionMappingResponse1.isPresent())
			gradeSectionInstitutionMapping.setGradeSectionInstitutionMappingId(gradeSectionInstitutionMappingId);
		return modelMapper.map(gradeSectionInstitutionMappingRepository.save(gradeSectionInstitutionMapping),GradeSectionInstitutionMappingDTO.class);

	}

	@Override
	public List<GradeSectionInstitutionMappingDTO> getAllGradeSectionInstitutionMappingByInstitution(Integer institutionId) {
		return gradeSectionInstitutionMappingRepository.getAllGradeSectionInstitutionMappingByInstitutionId(institutionId).stream().map(e->{
			return modelMapper.map(e,GradeSectionInstitutionMappingDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	public GradeSectionInstitutionMappingDTO getGradeSectionInstitutionMappingByInstitutionAndGradeSectionInstitutionMappingId(Integer institutionId, Integer gradeSectionInstitutionMappingId) {
		Optional<GradeSectionInstitutionMappingResponse> quickGradeSectionInstitutionMapping = Optional.ofNullable(gradeSectionInstitutionMappingRepository.getGradeSectionInstitutionMappingByInstitutionIdAndGradeSectionInstitutionMappingId(institutionId, gradeSectionInstitutionMappingId));
		if(quickGradeSectionInstitutionMapping.isPresent()){
			return modelMapper.map(quickGradeSectionInstitutionMapping.get(),GradeSectionInstitutionMappingDTO.class);
		}else
			throw new DataNotFoundException("GradeSectionInstitutionMapping with id not found");
	}

	@Override
	public Map<String, Set<GradeSectionInstitutionMappingDTO>> loadGradeSectionInstitutionMapping(XSSFSheet gradeSectionInstitutionMappingSheet, String gradeSectionInstitutionMappingSheetName) {
		Map<String,Set<GradeSectionInstitutionMappingDTO>> gradeSectionInstitutionMappingMap = new HashMap<>();
		Set<GradeSectionInstitutionMappingDTO> gradeSectionInstitutionMappingSet = new HashSet<>();
		gradeSectionInstitutionMappingSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				GradeSectionInstitutionMapping gradeSectionInstitutionMapping = new GradeSectionInstitutionMapping();
				Optional<Grade> grade = gradeRepository.findById(new Double(row.getCell(0).getNumericCellValue()).intValue());
				grade.ifPresent(gradeSectionInstitutionMapping::setGrade);
				Optional<Section> section = sectionRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				section.ifPresent(gradeSectionInstitutionMapping::setSection);
				Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(2).getNumericCellValue()).intValue());
				institution.ifPresent(gradeSectionInstitutionMapping::setInstitution);
				gradeSectionInstitutionMappingRepository.save(gradeSectionInstitutionMapping);
				GradeSectionInstitutionMappingDTO gradeSectionInstitutionMappingDTO = new GradeSectionInstitutionMappingDTO();
				gradeSectionInstitutionMappingDTO.setGradeSectionInstitutionMappingId(gradeSectionInstitutionMapping.getGradeSectionInstitutionMappingId());
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
