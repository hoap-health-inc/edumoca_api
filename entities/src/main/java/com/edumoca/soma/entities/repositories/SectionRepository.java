package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.models.SectionResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edumoca.soma.entities.Section;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends PagingAndSortingRepository<Section, Integer>{

	@Query("select new com.edumoca.soma.entities.models.SectionResponse(sec.sectionId,sec.sectionName,sec.sectionDescription) from Section sec where sec.institution.institutionId= :institutionId")
	public List<SectionResponse> findAllSections(@Param("institutionId") Integer institutionId);

	@Query("select new com.edumoca.soma.entities.models.SectionResponse(sec.sectionId,sec.sectionName,sec.sectionDescription) from Section sec where sec.sectionId = :sectionId and sec.institution.institutionId= :institutionId")
	public SectionResponse findSectionById(@Param("institutionId") Integer institutionId, @Param("sectionId") Integer sectionId);

	@Query("select new com.edumoca.soma.entities.models.SectionResponse(sec.sectionId,sec.sectionName,sec.sectionDescription) from Section sec where sec.sectionId = :sectionId")
	public SectionResponse findSectionBySectionId(@Param("sectionId") Integer sectionId);
}
