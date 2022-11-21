package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.AcademicYear;
import com.edumoca.soma.entities.models.AcademicYearResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcademicYearRepository extends PagingAndSortingRepository<AcademicYear, Integer>{

    @Query("select new com.edumoca.soma.entities.models.AcademicYearResponse(acyr.id,acyr.startDate,acyr.endDate) from AcademicYear acyr where acyr.institution.institutionId= :institutionId")
    public List<AcademicYearResponse> getAllAcademicYearByInstitutionId(@Param("institutionId") Integer institutionId);

    @Query("select new com.edumoca.soma.entities.models.AcademicYearResponse(acyr.id,acyr.startDate,acyr.endDate) from AcademicYear acyr where acyr.institution.institutionId= :institutionId and acyr.id = :academicYearId")
    public AcademicYearResponse getAcademicYearByInstitutionIdAndAcademicYearId(@Param("institutionId") Integer institutionId, @Param("academicYearId") Integer academicYearId);

    @Query("select new com.edumoca.soma.entities.models.AcademicYearResponse(acyr.id,acyr.startDate,acyr.endDate) from AcademicYear acyr where acyr.id = :academicYearId")
    public AcademicYearResponse getAcademicYearByAcademicYearId(@Param("academicYearId") Integer academicYearId);
}
