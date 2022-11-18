package com.edumoca.soma.entities.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.edumoca.soma.entities.GradeSectionInstitutionYearMapping;
import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.entities.Teacher;
import com.edumoca.soma.entities.TeacherGradeSectionSubjectMapping;

@Repository
public interface TeacherGradeSectionSubjectMappingRepository extends PagingAndSortingRepository<TeacherGradeSectionSubjectMapping,Integer>{

	//public TeacherGradeSectionSubjectMapping getTeacherGradeSectionSubjectIdByGradeSectionYearMappingAndTeacherAndSubject(GradeSectionInstitutionYearMapping gsym, Teacher teacher, Subject subject);
}
