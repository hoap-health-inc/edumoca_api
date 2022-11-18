package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.models.GradeSectionInstitutionYearMappingResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeSectionInstitutionYearMappingRepository extends PagingAndSortingRepository<GradeSectionInstitutionYearMapping, Integer>{

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionYearMappingResponse(gsiym.gradeSectionInstitutionYearMapId,gsiym.gradeSectionInstitutionMapping.grade.gradeName,gsiym.gradeSectionInstitutionMapping.section.sectionName,gsiym.gradeSectionInstitutionMapping.institution.institutionName,gsiym.academicYear.startDate,gsiym.academicYear.endDate) from GradeSectionInstitutionYearMapping gsiym where gsiym.gradeSectionInstitutionMapping.institution.institutionId = :institutionId")
    List<GradeSectionInstitutionYearMappingResponse> getAllGradeSectionInstitutionYearMappingByInstitutionId(@Param("institutionId") Integer institutionId);

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionYearMappingResponse(gsiym.gradeSectionInstitutionYearMapId,gsiym.gradeSectionInstitutionMapping.grade.gradeName,gsiym.gradeSectionInstitutionMapping.section.sectionName,gsiym.gradeSectionInstitutionMapping.institution.institutionName,gsiym.academicYear.startDate,gsiym.academicYear.endDate) from GradeSectionInstitutionYearMapping gsiym where gsiym.gradeSectionInstitutionMapping.institution.institutionId = :institutionId and gsiym.gradeSectionInstitutionYearMapId = :gradeSectionInstitutionYearMappingId")
    GradeSectionInstitutionYearMappingResponse getGradeSectionInstitutionYearMappingByInstitutionIdAndGradeSectionInstitutionYearMapId(@Param("institutionId") Integer institutionId, @Param("gradeSectionInstitutionYearMappingId") Integer gradeSectionInstitutionYearMappingId);

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionYearMappingResponse(gsiym.gradeSectionInstitutionYearMapId,gsiym.gradeSectionInstitutionMapping.grade.gradeName,gsiym.gradeSectionInstitutionMapping.section.sectionName,gsiym.gradeSectionInstitutionMapping.institution.institutionName,gsiym.academicYear.startDate,gsiym.academicYear.endDate) from GradeSectionInstitutionYearMapping gsiym where gsiym.gradeSectionInstitutionYearMapId = :gradeSectionInstitutionYearMappingId")
    GradeSectionInstitutionYearMappingResponse getGradeSectionInstitutionYearMappingByGradeSectionInstitutionYearMapId(@Param("gradeSectionInstitutionYearMappingId") Integer gradeSectionInstitutionYearMappingId);
}
