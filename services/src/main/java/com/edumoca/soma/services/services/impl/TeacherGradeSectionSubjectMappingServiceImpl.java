package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.GradeSectionInstitutionYearMap;
import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.entities.Teacher;
import com.edumoca.soma.entities.TeacherGradeSectionSubjectMap;
import com.edumoca.soma.entities.dtos.TeacherGradeSectionSubjectMapDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.GradeSectionInstitutionYearMapRepository;
import com.edumoca.soma.entities.repositories.SubjectRepository;
import com.edumoca.soma.entities.repositories.TeacherGradeSectionSubjectMapRepository;
import com.edumoca.soma.entities.repositories.TeacherRepository;
import com.edumoca.soma.services.services.TeacherGradeSectionSubjectMappingService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeacherGradeSectionSubjectMappingServiceImpl implements TeacherGradeSectionSubjectMappingService{


	private final TeacherGradeSectionSubjectMapRepository teacherGradeSectionSubjectMappingRepository;
	private final TeacherRepository teacherRepository;
	private final GradeSectionInstitutionYearMapRepository gradeSectionInstitutionYearMappingRepository;
	private final SubjectRepository subjectRepository;

	@Autowired
	public TeacherGradeSectionSubjectMappingServiceImpl(TeacherGradeSectionSubjectMapRepository teacherGradeSectionSubjectMappingRepository, TeacherRepository teacherRepository, GradeSectionInstitutionYearMapRepository gradeSectionInstitutionYearMappingRepository, SubjectRepository subjectRepository) {
		this.teacherGradeSectionSubjectMappingRepository = teacherGradeSectionSubjectMappingRepository;
		this.teacherRepository = teacherRepository;
		this.gradeSectionInstitutionYearMappingRepository = gradeSectionInstitutionYearMappingRepository;
		this.subjectRepository = subjectRepository;
	}

	@Override
	public TeacherGradeSectionSubjectMap createTeacherGradeSectionSubjectMapping(
			TeacherGradeSectionSubjectMap teacherGradeSectionSubjectMap) {
		return teacherGradeSectionSubjectMappingRepository.save(teacherGradeSectionSubjectMap);
	}

	@Override
	public TeacherGradeSectionSubjectMap updateTeacherGradeSectionSubjectMapping(
			TeacherGradeSectionSubjectMap teacherGradeSectionSubjectMap) {
		return teacherGradeSectionSubjectMappingRepository.save(teacherGradeSectionSubjectMap);
	}

	@Override
	public TeacherGradeSectionSubjectMap getTeacherGradeSectionSubjectMappingById(
			Integer teacherGradeSectionSubjectMappingId) {
		Optional<TeacherGradeSectionSubjectMap> teacherGradeSectionSubjectMapping = teacherGradeSectionSubjectMappingRepository.findById(teacherGradeSectionSubjectMappingId);
		if(teacherGradeSectionSubjectMapping.isPresent())
			return teacherGradeSectionSubjectMapping.get();
		else
			throw new DataNotFoundException("TeacherGradeSectionSubjectMapping with id not found.");
	}

	@Override
	public Map<String, Set<TeacherGradeSectionSubjectMapDto>> loadTeacherGradeSectionSubjectMappings(XSSFSheet teacherGradeSectionSubjectMappingsSheet, String teacherGradeSectionSubjectMappingsSheetName) {
		Map<String, Set<TeacherGradeSectionSubjectMapDto>> teacherGradeSectionSubjectMappingsMap = new HashMap<>();
		Set<TeacherGradeSectionSubjectMapDto> teacherGradeSectionSubjectMappingsSet = new HashSet<>();
		teacherGradeSectionSubjectMappingsSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				TeacherGradeSectionSubjectMap teacherGradeSectionSubjectMap = new TeacherGradeSectionSubjectMap();
				Optional<Teacher> teacher = teacherRepository.findById(new Double(row.getCell(0).getNumericCellValue()).intValue());
				teacher.ifPresent(teacherGradeSectionSubjectMap::setTeacher);
				Optional<GradeSectionInstitutionYearMap> gradeSectionInstitutionYearMapping = gradeSectionInstitutionYearMappingRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				gradeSectionInstitutionYearMapping.ifPresent(teacherGradeSectionSubjectMap::setGradeSectionInstitutionYearMap);
				Optional<Subject> subject = subjectRepository.findById(new Double(row.getCell(2).getNumericCellValue()).intValue());
				subject.ifPresent(teacherGradeSectionSubjectMap::setSubject);
				teacherGradeSectionSubjectMappingRepository.save(teacherGradeSectionSubjectMap);
				TeacherGradeSectionSubjectMapDto teacherGradeSectionSubjectMappingDto = new TeacherGradeSectionSubjectMapDto();
				teacherGradeSectionSubjectMappingDto.setTeacherGradeSectionSubjectMappingId(teacherGradeSectionSubjectMap.getTeacherGradeSectionSubjectMappingId());
				teacherGradeSectionSubjectMappingDto.setTeacherId(teacherGradeSectionSubjectMap.getTeacher().getUserId());
				teacherGradeSectionSubjectMappingDto.setTeacherName(teacherGradeSectionSubjectMap.getTeacher().getFullName());
				teacherGradeSectionSubjectMappingDto.setSubjectId(teacherGradeSectionSubjectMap.getSubject().getSubjectId());
				teacherGradeSectionSubjectMappingDto.setSubjectName(teacherGradeSectionSubjectMap.getSubject().getSubjectName());
				teacherGradeSectionSubjectMappingsSet.add(teacherGradeSectionSubjectMappingDto);
			}
		});
		teacherGradeSectionSubjectMappingsMap.put(teacherGradeSectionSubjectMappingsSheetName,teacherGradeSectionSubjectMappingsSet);
		return teacherGradeSectionSubjectMappingsMap;
	}
}
