package com.edumoca.soma.services.services.impl;

import java.util.*;

import com.edumoca.soma.entities.GradeSectionInstitutionYearMapping;
import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.entities.Teacher;
import com.edumoca.soma.entities.dtos.TeacherGradeSectionSubjectMappingDTO;
import com.edumoca.soma.entities.repositories.GradeSectionInstitutionYearMappingRepository;
import com.edumoca.soma.entities.repositories.SubjectRepository;
import com.edumoca.soma.entities.repositories.TeacherRepository;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.TeacherGradeSectionSubjectMapping;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.repositories.TeacherGradeSectionSubjectMappingRepository;
import com.edumoca.soma.services.services.TeacherGradeSectionSubjectMappingService;

@Service
public class TeacherGradeSectionSubjectMappingServiceImpl implements TeacherGradeSectionSubjectMappingService{


	private final TeacherGradeSectionSubjectMappingRepository teacherGradeSectionSubjectMappingRepository;
	private final TeacherRepository teacherRepository;
	private final GradeSectionInstitutionYearMappingRepository gradeSectionInstitutionYearMappingRepository;
	private final SubjectRepository subjectRepository;

	@Autowired
	public TeacherGradeSectionSubjectMappingServiceImpl(TeacherGradeSectionSubjectMappingRepository teacherGradeSectionSubjectMappingRepository, TeacherRepository teacherRepository, GradeSectionInstitutionYearMappingRepository gradeSectionInstitutionYearMappingRepository, SubjectRepository subjectRepository) {
		this.teacherGradeSectionSubjectMappingRepository = teacherGradeSectionSubjectMappingRepository;
		this.teacherRepository = teacherRepository;
		this.gradeSectionInstitutionYearMappingRepository = gradeSectionInstitutionYearMappingRepository;
		this.subjectRepository = subjectRepository;
	}

	@Override
	public TeacherGradeSectionSubjectMapping createTeacherGradeSectionSubjectMapping(
			TeacherGradeSectionSubjectMapping teacherGradeSectionSubjectMapping) {
		return teacherGradeSectionSubjectMappingRepository.save(teacherGradeSectionSubjectMapping);
	}

	@Override
	public TeacherGradeSectionSubjectMapping updateTeacherGradeSectionSubjectMapping(
			TeacherGradeSectionSubjectMapping teacherGradeSectionSubjectMapping) {
		return teacherGradeSectionSubjectMappingRepository.save(teacherGradeSectionSubjectMapping);
	}

	@Override
	public TeacherGradeSectionSubjectMapping getTeacherGradeSectionSubjectMappingById(
			Integer teacherGradeSectionSubjectMappingId) {
		Optional<TeacherGradeSectionSubjectMapping> teacherGradeSectionSubjectMapping = teacherGradeSectionSubjectMappingRepository.findById(teacherGradeSectionSubjectMappingId);
		if(teacherGradeSectionSubjectMapping.isPresent())
			return teacherGradeSectionSubjectMapping.get();
		else
			throw new DataNotFoundException("TeacherGradeSectionSubjectMapping with id not found.");
	}

	@Override
	public Map<String, Set<TeacherGradeSectionSubjectMappingDTO>> loadTeacherGradeSectionSubjectMappings(XSSFSheet teacherGradeSectionSubjectMappingsSheet, String teacherGradeSectionSubjectMappingsSheetName) {
		Map<String, Set<TeacherGradeSectionSubjectMappingDTO>> teacherGradeSectionSubjectMappingsMap = new HashMap<>();
		Set<TeacherGradeSectionSubjectMappingDTO> teacherGradeSectionSubjectMappingsSet = new HashSet<>();
		teacherGradeSectionSubjectMappingsSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				TeacherGradeSectionSubjectMapping teacherGradeSectionSubjectMapping = new TeacherGradeSectionSubjectMapping();
				Optional<Teacher> teacher = teacherRepository.findById(new Double(row.getCell(0).getNumericCellValue()).intValue());
				teacher.ifPresent(teacherGradeSectionSubjectMapping::setTeacher);
				Optional<GradeSectionInstitutionYearMapping> gradeSectionInstitutionYearMapping = gradeSectionInstitutionYearMappingRepository.findById(new Double(row.getCell(1).getNumericCellValue()).intValue());
				gradeSectionInstitutionYearMapping.ifPresent(teacherGradeSectionSubjectMapping::setGradeSectionInstitutionYearMapping);
				Optional<Subject> subject = subjectRepository.findById(new Double(row.getCell(2).getNumericCellValue()).intValue());
				subject.ifPresent(teacherGradeSectionSubjectMapping::setSubject);
				teacherGradeSectionSubjectMappingRepository.save(teacherGradeSectionSubjectMapping);
				TeacherGradeSectionSubjectMappingDTO teacherGradeSectionSubjectMappingDTO = new TeacherGradeSectionSubjectMappingDTO();
				teacherGradeSectionSubjectMappingDTO.setTeacherGradeSectionSubjectMappingId(teacherGradeSectionSubjectMapping.getTeacherGradeSectionSubjectMappingId());
				teacherGradeSectionSubjectMappingDTO.setTeacherId(teacherGradeSectionSubjectMapping.getTeacher().getUserId());
				teacherGradeSectionSubjectMappingDTO.setTeacherName(teacherGradeSectionSubjectMapping.getTeacher().getFullName());
				teacherGradeSectionSubjectMappingDTO.setSubjectId(teacherGradeSectionSubjectMapping.getSubject().getSubjectId());
				teacherGradeSectionSubjectMappingDTO.setSubjectName(teacherGradeSectionSubjectMapping.getSubject().getSubjectName());
				teacherGradeSectionSubjectMappingsSet.add(teacherGradeSectionSubjectMappingDTO);
			}
		});
		teacherGradeSectionSubjectMappingsMap.put(teacherGradeSectionSubjectMappingsSheetName,teacherGradeSectionSubjectMappingsSet);
		return teacherGradeSectionSubjectMappingsMap;
	}
}
