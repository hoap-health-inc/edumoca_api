package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Grade;
import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.dtos.GradeDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.GradeResponse;
import com.edumoca.soma.entities.repositories.GradeRepository;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import com.edumoca.soma.services.services.GradeService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService{

	private final GradeRepository gradeRepository;
	private final InstitutionRepository institutionRepository;
	private final ModelMapper modelMapper;

	@Override
	public GradeDto createGrade(Grade grade) {
		return modelMapper.map(gradeRepository.save(grade), GradeDto.class);
	}

	@Override
	public GradeDto updateGrade(Grade grade, Integer gradeId) {
		Optional<GradeResponse> grade1 = Optional.ofNullable(gradeRepository.findGradeByGradeId(gradeId));
		if(grade1.isPresent())
			grade.setGradeId(gradeId);
		return modelMapper.map(gradeRepository.save(grade), GradeDto.class);
	}
	
	@Override
	public List<GradeDto> getAllGradesByInstitution(Integer institutionId) {
		return gradeRepository.findAllGradesByInstitutionId(institutionId).stream().map(e->{
			return modelMapper.map(e, GradeDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public GradeDto getGradeByInstitutionAndGrade(Integer institutionId, Integer gradeId) {
		 Optional<GradeResponse> grade = Optional.ofNullable(gradeRepository.findGradeByInstitutionAndGradeId(institutionId, gradeId));
		 if(grade.isPresent()) {
			 return modelMapper.map(grade.get(), GradeDto.class);
		 }else
		 throw new DataNotFoundException("Grade with id not found");
	}


	@Override
	public Map<String, Set<GradeDto>> loadGrades(XSSFSheet gradeSheet, String gradeSheetName) {
		Map<String,Set<GradeDto>> gradeMap = new HashMap<>();
		Set<GradeDto> gradeSet = new HashSet<>();
		gradeSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0) {
				Grade grade = new Grade();
				grade.setGradeName(row.getCell(0).getStringCellValue());
				Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				institution.ifPresent(grade::setInstitution);
				gradeRepository.save(grade);
				GradeDto gradeDTO = new GradeDto();
				gradeDTO.setGradeId(grade.getGradeId());
				gradeDTO.setGradeName(grade.getGradeName());
			//	gradeDTO.setGradeDescription(grade.getGradeDescription());
				gradeSet.add(gradeDTO);
			}
		});
		gradeMap.put(gradeSheetName,gradeSet);
		return gradeMap;
	}
}
