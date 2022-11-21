package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.Section;
import com.edumoca.soma.entities.dtos.SectionDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.SectionResponse;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import com.edumoca.soma.entities.repositories.SectionRepository;
import com.edumoca.soma.services.services.SectionService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService{

	private final SectionRepository sectionRepository;
	private final InstitutionRepository institutionRepository;

	private final ModelMapper modelMapper;

	@Override
	public SectionDto createSection(Section section) {
		return modelMapper.map(sectionRepository.save(section), SectionDto.class);
	}

	@Override
	public SectionDto updateSection(Section section, Integer sectionId) {
         Optional<SectionResponse> section1 = Optional.ofNullable(sectionRepository.findSectionBySectionId(sectionId));
		 if(section1.isPresent())
			 section.setSectionId(sectionId);
	     return  modelMapper.map(sectionRepository.save(section), SectionDto.class);
	}
	
	@Override
	public List<SectionDto> getAllSectionByInstitution(Integer institutionId) {
		return sectionRepository.findAllSections(institutionId).stream().map(e->{
			SectionDto sectionDTO = new SectionDto();
			BeanUtils.copyProperties(e,sectionDTO);
			return sectionDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public SectionDto getSectionByInstitutionAndSection(Integer institutionId, Integer sectionId) {
		Optional<SectionResponse> section = Optional.ofNullable(sectionRepository.findSectionById(institutionId,sectionId));
		if(section.isPresent()) {
			SectionDto sectionDTO = new SectionDto();
			BeanUtils.copyProperties(section.get(),sectionDTO);
			return sectionDTO;
		}else
			throw new DataNotFoundException("Section with id not found.");
	}

	@Override
	public Map<String, Set<SectionDto>> loadSections(XSSFSheet sectionSheet, String sectionSheetName) {
		Map<String,Set<SectionDto>> sectionMap = new HashMap<>();
		Set<SectionDto> sectionSet = new HashSet<>();
		sectionSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				Section section = new Section();
				SectionDto sectionDTO = new SectionDto();
				section.setSectionName(row.getCell(0).getStringCellValue());
                Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				institution.ifPresent(section::setInstitution);
				sectionRepository.save(section);
				sectionDTO.setSectionId(section.getSectionId());
				sectionDTO.setSectionName(section.getSectionName());
				//sectionDTO.setSectionDescription(section.getSectionDescription());
				sectionSet.add(sectionDTO);
			}
		});
		sectionMap.put(sectionSheetName,sectionSet);
		return sectionMap;
	}
}
