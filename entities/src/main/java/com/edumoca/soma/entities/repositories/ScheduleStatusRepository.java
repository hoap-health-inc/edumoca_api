package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.ScheduleStatus;
import com.edumoca.soma.entities.models.ScheduleStatusResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ScheduleStatusRepository extends PagingAndSortingRepository<ScheduleStatus, Integer>{

    @Query("select new com.edumoca.soma.entities.models.ScheduleStatusResponse(ss.scheduleStatusId,ss.schedule.scheduleId,ss.status,ss.startDate,ss.endDate) from ScheduleStatus ss where ss.startDate <= :currentDate and ss.endDate >= :currentDate and ss.schedule.scheduleId= :scheduleId")
    public Set<ScheduleStatusResponse> getScheduleStatusByScheduleId(@Param("currentDate") Date currentDate, @Param("scheduleId") Integer scheduleId);
}
