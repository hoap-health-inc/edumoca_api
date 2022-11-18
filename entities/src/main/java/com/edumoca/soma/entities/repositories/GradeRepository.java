package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.models.GradeResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edumoca.soma.entities.Grade;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends PagingAndSortingRepository<Grade, Integer>{
	@Query("select new com.edumoca.soma.entities.models.GradeResponse(g.gradeId,g.gradeName,g.gradeDescription) from Grade g where g.institution.institutionId= :institutionId")
	public List<GradeResponse> findAllGradesByInstitutionId(@Param("institutionId") Integer institutionId);

	@Query("select new com.edumoca.soma.entities.models.GradeResponse(g.gradeId,g.gradeName,g.gradeDescription) from Grade g where g.institution.institutionId= :institutionId and g.gradeId= :gradeId")
	public GradeResponse findGradeByInstitutionAndGradeId(@Param("institutionId") Integer institutionId, @Param("gradeId") Integer gradeId);

	@Query("select new com.edumoca.soma.entities.models.GradeResponse(g.gradeId,g.gradeName,g.gradeDescription) from Grade g where g.gradeId= :gradeId")
	public GradeResponse findGradeByGradeId(@Param("gradeId") Integer gradeId);
}
