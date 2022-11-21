package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.GradeSectionInstitutionYearMap;
import com.edumoca.soma.entities.models.GradeSectionInstitutionYearMapResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeSectionInstitutionYearMapRepository extends PagingAndSortingRepository<GradeSectionInstitutionYearMap, Integer>{

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionYearMapResponse(gsiym.gradeSectionInstitutionYearMapId,gsiym.gradeSectionInstitutionMap.grade.gradeName,gsiym.gradeSectionInstitutionMap.section.sectionName,gsiym.gradeSectionInstitutionMap.institution.institutionName,gsiym.academicYear.startDate,gsiym.academicYear.endDate) from GradeSectionInstitutionYearMap gsiym where gsiym.gradeSectionInstitutionMap.institution.institutionId = :institutionId")
    List<GradeSectionInstitutionYearMapResponse> getAllGradeSectionInstitutionYearMappingByInstitutionId(@Param("institutionId") Integer institutionId);

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionYearMapResponse(gsiym.gradeSectionInstitutionYearMapId,gsiym.gradeSectionInstitutionMap.grade.gradeName,gsiym.gradeSectionInstitutionMap.section.sectionName,gsiym.gradeSectionInstitutionMap.institution.institutionName,gsiym.academicYear.startDate,gsiym.academicYear.endDate) from GradeSectionInstitutionYearMap gsiym where gsiym.gradeSectionInstitutionMap.institution.institutionId = :institutionId and gsiym.gradeSectionInstitutionYearMapId = :gradeSectionInstitutionYearMapId")
    GradeSectionInstitutionYearMapResponse getGradeSectionInstitutionYearMappingByInstitutionIdAndGradeSectionInstitutionYearMapId(@Param("institutionId") Integer institutionId, @Param("gradeSectionInstitutionYearMapId") Integer gradeSectionInstitutionYearMapId);

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionYearMapResponse(gsiym.gradeSectionInstitutionYearMapId,gsiym.gradeSectionInstitutionMap.grade.gradeName,gsiym.gradeSectionInstitutionMap.section.sectionName,gsiym.gradeSectionInstitutionMap.institution.institutionName,gsiym.academicYear.startDate,gsiym.academicYear.endDate) from GradeSectionInstitutionYearMap gsiym where gsiym.gradeSectionInstitutionYearMapId = :gradeSectionInstitutionYearMapId")
    GradeSectionInstitutionYearMapResponse getGradeSectionInstitutionYearMappingByGradeSectionInstitutionYearMapId(@Param("gradeSectionInstitutionYearMapId") Integer gradeSectionInstitutionYearMapId);
}
