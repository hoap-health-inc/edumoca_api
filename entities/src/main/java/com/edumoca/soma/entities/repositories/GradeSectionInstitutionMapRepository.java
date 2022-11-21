package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.GradeSectionInstitutionMap;
import com.edumoca.soma.entities.models.GradeSectionInstitutionMapResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeSectionInstitutionMapRepository extends PagingAndSortingRepository<GradeSectionInstitutionMap, Integer>{

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionMapResponse(gsim.gradeSectionInstitutionMappingId,gsim.grade.gradeName,gsim.section.sectionName,gsim.institution.institutionName) from GradeSectionInstitutionMap gsim where gsim.institution.institutionId = :institutionId")
    public List<GradeSectionInstitutionMapResponse> getAllGradeSectionInstitutionMappingByInstitutionId(@Param("institutionId") Integer institutionId);

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionMapResponse(gsim.gradeSectionInstitutionMappingId,gsim.grade.gradeName,gsim.section.sectionName,gsim.institution.institutionName) from GradeSectionInstitutionMap gsim where gsim.institution.institutionId = :institutionId and gsim.gradeSectionInstitutionMappingId= :gradeSectionInstitutionMappingId")
    public GradeSectionInstitutionMapResponse getGradeSectionInstitutionMappingByInstitutionIdAndGradeSectionInstitutionMappingId(@Param("institutionId") Integer institutionId, @Param("gradeSectionInstitutionMappingId") Integer gradeSectionInstitutionMappingId);

    @Query("select new com.edumoca.soma.entities.models.GradeSectionInstitutionMapResponse(gsim.gradeSectionInstitutionMappingId,gsim.grade.gradeName,gsim.section.sectionName,gsim.institution.institutionName) from GradeSectionInstitutionMap gsim where gsim.gradeSectionInstitutionMappingId= :gradeSectionInstitutionMappingId")
    public GradeSectionInstitutionMapResponse getGradeSectionInstitutionMappingByGradeSectionInstitutionMappingId(@Param("gradeSectionInstitutionMappingId") Integer gradeSectionInstitutionMappingId);
}
