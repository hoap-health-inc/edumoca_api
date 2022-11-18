package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.models.GradeSectionInstitutionMappingResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edumoca.soma.entities.GradeSectionInstitutionMapping;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeSectionInstitutionMappingRepository extends PagingAndSortingRepository<GradeSectionInstitutionMapping, Integer>{

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionMappingResponse(gsim.gradeSectionInstitutionMappingId,gsim.grade.gradeName,gsim.section.sectionName,gsim.institution.institutionName) from GradeSectionInstitutionMapping gsim where gsim.institution.institutionId = :institutionId")
    public List<GradeSectionInstitutionMappingResponse> getAllGradeSectionInstitutionMappingByInstitutionId(@Param("institutionId") Integer institutionId);

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionMappingResponse(gsim.gradeSectionInstitutionMappingId,gsim.grade.gradeName,gsim.section.sectionName,gsim.institution.institutionName) from GradeSectionInstitutionMapping gsim where gsim.institution.institutionId = :institutionId and gsim.gradeSectionInstitutionMappingId= :gradeSectionInstitutionMappingId")
    public GradeSectionInstitutionMappingResponse getGradeSectionInstitutionMappingByInstitutionIdAndGradeSectionInstitutionMappingId(@Param("institutionId") Integer institutionId, @Param("gradeSectionInstitutionMappingId") Integer gradeSectionInstitutionMappingId);

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionMappingResponse(gsim.gradeSectionInstitutionMappingId,gsim.grade.gradeName,gsim.section.sectionName,gsim.institution.institutionName) from GradeSectionInstitutionMapping gsim where gsim.gradeSectionInstitutionMappingId= :gradeSectionInstitutionMappingId")
    public GradeSectionInstitutionMappingResponse getGradeSectionInstitutionMappingByGradeSectionInstitutionMappingId(@Param("gradeSectionInstitutionMappingId") Integer gradeSectionInstitutionMappingId);
}
