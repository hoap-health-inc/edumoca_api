package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.entities.models.SubjectResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends PagingAndSortingRepository<Subject, Integer>{

	@Query("select new com.edumoca.soma.entities.models.SubjectResponse(sub.subjectId,sub.subjectName) from Subject sub where sub.institution.institutionId= :institutionId")
	public List<SubjectResponse> getAllSubjectsByInstitutionId(@Param("institutionId") Integer institutionId);

	@Query("select new  com.edumoca.soma.entities.models.SubjectResponse(sub.subjectId,sub.subjectName) from Subject sub where sub.institution.institutionId= :institutionId and sub.subjectId= :subjectId")
	public SubjectResponse getSubjectByInstitutionIdAndSubjectId(@Param("institutionId") Integer institutionId, @Param("subjectId") Integer subjectId);

	@Query("select new  com.edumoca.soma.entities.models.SubjectResponse(sub.subjectId,sub.subjectName) from Subject sub where sub.subjectId= :subjectId")
	public SubjectResponse getSubjectBySubjectId(@Param("subjectId") Integer subjectId);
}
