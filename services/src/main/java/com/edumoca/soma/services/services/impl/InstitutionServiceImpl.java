package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.dtos.InstitutionDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.InstitutionResponse;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import com.edumoca.soma.services.services.InstitutionService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstitutionServiceImpl implements InstitutionService{

	private final InstitutionRepository institutionRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public InstitutionDto createInstitution(Institution institution) {
		return modelMapper.map(institutionRepository.save(institution), InstitutionDto.class);
	}
	
	@Override
	public InstitutionDto updateInstitution(Institution institution, Integer institutionId) {
		Optional<InstitutionResponse> institution1 = Optional.ofNullable(institutionRepository.findInstitutionById(institutionId));
		if(institution1.isPresent())
			institution.setInstitutionId(institutionId);
		return modelMapper.map(institutionRepository.save(institution), InstitutionDto.class);
	}
	
	@Override
	public List<InstitutionDto> getAllInstitutions() {
		return institutionRepository.findAllInstitution().stream().map(e->{
			return modelMapper.map(e, InstitutionDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public InstitutionDto getInstitutionById(Integer institutionId) {
	  Optional<InstitutionResponse> institution = Optional.ofNullable(institutionRepository.findInstitutionById(institutionId));
	  if(institution.isPresent()) {
		  return modelMapper.map(institution.get(), InstitutionDto.class);
	  } else
		  throw new DataNotFoundException("Institution with id not found.");
	}

	@Override
	public void deleteInstitution(Integer institutionId) {
		institutionRepository.deleteById(institutionId);
	}

	@Override
	public Map<String,Set<InstitutionDto>> loadInstitution(XSSFSheet institutionSheet, String instituteName) {
		Map<String, Set<InstitutionDto>> institutionMap = new HashMap<>();
		Set<InstitutionDto> institutionSet = new HashSet<>();
		institutionSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0) {
				Institution institution = new Institution();
				InstitutionDto institutionDTO = new InstitutionDto();
				institution.setInstitutionName(row.getCell(0).getStringCellValue());
				institutionRepository.save(institution);
				institutionDTO.setInstitutionId(institution.getInstitutionId());
				institutionDTO.setInstitutionName(institution.getInstitutionName());
				institutionSet.add(institutionDTO);
			}
		});
		institutionMap.put(instituteName, institutionSet);
		return institutionMap;
	}
}
