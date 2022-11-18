package com.edumoca.soma.services.services.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.edumoca.soma.entities.models.InstitutionResponse;
import com.edumoca.soma.entities.dtos.InstitutionDTO;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import com.edumoca.soma.services.services.InstitutionService;

@Service
@AllArgsConstructor
public class InstitutionServiceImpl implements InstitutionService{

	private final InstitutionRepository institutionRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public InstitutionDTO createInstitution(Institution institution) {
		return modelMapper.map(institutionRepository.save(institution),InstitutionDTO.class);
	}
	
	@Override
	public InstitutionDTO updateInstitution(Institution institution,Integer institutionId) {
		Optional<InstitutionResponse> institution1 = Optional.ofNullable(institutionRepository.findInstitutionById(institutionId));
		if(institution1.isPresent())
			institution.setInstitutionId(institutionId);
		return modelMapper.map(institutionRepository.save(institution),InstitutionDTO.class);
	}
	
	@Override
	public List<InstitutionDTO> getAllInstitutions() {
		return institutionRepository.findAllInstitution().stream().map(e->{
			return modelMapper.map(e,InstitutionDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	public InstitutionDTO getInstitutionById(Integer institutionId) {
	  Optional<InstitutionResponse> institution = Optional.ofNullable(institutionRepository.findInstitutionById(institutionId));
	  if(institution.isPresent()) {
		  return modelMapper.map(institution.get(),InstitutionDTO.class);
	  } else
		  throw new DataNotFoundException("Institution with id not found.");
	}

	@Override
	public void deleteInstitution(Integer institutionId) {
		institutionRepository.deleteById(institutionId);
	}

	@Override
	public Map<String,Set<InstitutionDTO>> loadInstitution(XSSFSheet institutionSheet,String instituteName) {
		Map<String, Set<InstitutionDTO>> institutionMap = new HashMap<>();
		Set<InstitutionDTO> institutionSet = new HashSet<>();
		institutionSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0) {
				Institution institution = new Institution();
				InstitutionDTO institutionDTO = new InstitutionDTO();
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
