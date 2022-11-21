package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Institution;
import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.entities.dtos.SubjectDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.SubjectResponse;
import com.edumoca.soma.entities.repositories.InstitutionRepository;
import com.edumoca.soma.entities.repositories.SubjectRepository;
import com.edumoca.soma.services.services.SubjectService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService{

	private final SubjectRepository subjectRepository;
	private final InstitutionRepository institutionRepository;
	private final ModelMapper modelMapper;

	@Override
	public SubjectDto createSubject(Subject subject) {
		return modelMapper.map(subjectRepository.save(subject), SubjectDto.class);
	}

	@Override
	public SubjectDto updateSubject(Subject subject, Integer subjectId) {
        Optional<SubjectResponse> subject1 = Optional.ofNullable(subjectRepository.getSubjectBySubjectId(subjectId));
		if(subject1.isPresent())
			subject.setSubjectId(subjectId);
		return modelMapper.map(subjectRepository.save(subject), SubjectDto.class);
	}
	
	@Override
	public List<SubjectDto> getAllSubjectsByInstitution(Integer institutionId) {
		return subjectRepository.getAllSubjectsByInstitutionId(institutionId).stream().map(sub->{
			SubjectDto subjectDTO = new SubjectDto();
			BeanUtils.copyProperties(sub,subjectDTO);
			return subjectDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public SubjectDto getSubjectByInstitutionAndSubject(Integer institutionId, Integer subjectId) {
		Optional<SubjectResponse> quickSubject = Optional.ofNullable(subjectRepository.getSubjectByInstitutionIdAndSubjectId(institutionId, subjectId));
		if(quickSubject.isPresent()) {
			SubjectDto subjectDTO = new SubjectDto();
			BeanUtils.copyProperties(quickSubject.get(),subjectDTO);
			return subjectDTO;
		}else
			throw new DataNotFoundException("Subject with id not found.");
	}

	@Override
	public Map<String, Set<SubjectDto>> loadSubjects(XSSFSheet subjectsSheet, String subjectsSheetName) {
		Map<String,Set<SubjectDto>> subjectsMap = new HashMap<>();
		Set<SubjectDto> subjectsSet = new HashSet<>();
		subjectsSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				Subject subject = new Subject();
				subject.setSubjectName(row.getCell(0).getStringCellValue());
				Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				institution.ifPresent(subject::setInstitution);
				subjectRepository.save(subject);
				SubjectDto subjectDTO = new SubjectDto();
				subjectDTO.setSubjectId(subject.getSubjectId());
				subjectDTO.setSubjectName(subject.getSubjectName());
				subjectsSet.add(subjectDTO);
			}
		});
		subjectsMap.put(subjectsSheetName,subjectsSet);
		return subjectsMap;
	}
}
