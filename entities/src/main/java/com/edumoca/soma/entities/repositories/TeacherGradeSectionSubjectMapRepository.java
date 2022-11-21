package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.TeacherGradeSectionSubjectMap;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherGradeSectionSubjectMapRepository extends PagingAndSortingRepository<TeacherGradeSectionSubjectMap,Integer>{

	//public TeacherGradeSectionSubjectMapping getTeacherGradeSectionSubjectIdByGradeSectionYearMappingAndTeacherAndSubject(GradeSectionInstitutionYearMapping gsym, Teacher teacher, Subject subject);
}
