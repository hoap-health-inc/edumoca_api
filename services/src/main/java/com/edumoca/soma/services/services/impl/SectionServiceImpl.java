package com.edumoca.soma.services.services.impl;
import java.util.*;
import java.util.stream.Collectors;
import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.models.SectionResponse;
import com.edumoca.soma.entities.dtos.SectionDTO;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.edumoca.soma.entities.Section;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.SectionRepository;
import com.edumoca.soma.services.services.SectionService;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService{

	private final SectionRepository sectionRepository;
	private final InstitutionRepository institutionRepository;

	private final ModelMapper modelMapper;

	@Override
	public SectionDTO createSection(Section section) {
		return modelMapper.map(sectionRepository.save(section),SectionDTO.class);
	}

	@Override
	public SectionDTO updateSection(Section section,Integer sectionId) {
         Optional<SectionResponse> section1 = Optional.ofNullable(sectionRepository.findSectionBySectionId(sectionId));
		 if(section1.isPresent())
			 section.setSectionId(sectionId);
	     return  modelMapper.map(sectionRepository.save(section),SectionDTO.class);
	}
	
	@Override
	public List<SectionDTO> getAllSectionByInstitution(Integer institutionId) {
		return sectionRepository.findAllSections(institutionId).stream().map(e->{
			SectionDTO sectionDTO = new SectionDTO();
			BeanUtils.copyProperties(e,sectionDTO);
			return sectionDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public SectionDTO getSectionByInstitutionAndSection(Integer institutionId,Integer sectionId) {
		Optional<SectionResponse> section = Optional.ofNullable(sectionRepository.findSectionById(institutionId,sectionId));
		if(section.isPresent()) {
			SectionDTO sectionDTO = new SectionDTO();
			BeanUtils.copyProperties(section.get(),sectionDTO);
			return sectionDTO;
		}else
			throw new DataNotFoundException("Section with id not found.");
	}

	@Override
	public Map<String, Set<SectionDTO>> loadSections(XSSFSheet sectionSheet, String sectionSheetName) {
		Map<String,Set<SectionDTO>> sectionMap = new HashMap<>();
		Set<SectionDTO> sectionSet = new HashSet<>();
		sectionSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				Section section = new Section();
				SectionDTO sectionDTO = new SectionDTO();
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
