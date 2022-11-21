package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.Period;
import com.edumoca.soma.entities.models.PeriodResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeriodRepository extends PagingAndSortingRepository<Period, Integer>{

    @Query("select new com.edumoca.soma.entities.models.PeriodResponse(p.periodNo,p.periodName,p.periodNo) from Period p")
    public List<PeriodResponse> getAllPeriods();

    @Query("select new com.edumoca.soma.entities.models.PeriodResponse(p.periodNo,p.periodName,p.periodNo) from Period p where p.periodNo = :periodId")
    public PeriodResponse getPeriodByPeriodId(@Param("periodId") Integer periodId);
}
