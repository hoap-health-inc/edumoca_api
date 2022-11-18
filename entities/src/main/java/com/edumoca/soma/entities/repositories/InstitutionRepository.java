package com.edumoca.soma.entities.repositories;
import com.edumoca.soma.entities.models.InstitutionResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.edumoca.soma.entities.Institution;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface InstitutionRepository extends PagingAndSortingRepository<Institution, Integer>{

	@Query("select new com.edumoca.soma.entities.models.InstitutionResponse(inst.institutionId,inst.institutionName) from Institution inst where inst.institutionId= :institutionId")
	public InstitutionResponse findInstitutionById(@Param("institutionId") Integer institutionId);

	@Query("select new com.edumoca.soma.entities.models.InstitutionResponse(inst.institutionId,inst.institutionName) from Institution inst")
	public List<InstitutionResponse> findAllInstitution();

}
