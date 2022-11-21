package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.CourseSession;
import com.edumoca.soma.entities.models.CourseSessionResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseSessionRepository extends PagingAndSortingRepository<CourseSession,	Integer>{

    @Query("select new com.edumoca.soma.entities.models.CourseSessionResponse(cs.sessionId,cs.period.periodName,cs.period.periodNo,cs.startTime,cs.endTime) from CourseSession cs where cs.institution.institutionId = :institutionId")
    public List<CourseSessionResponse> getAllCourseSessionByInstitutionId(@Param("institutionId") Integer institutionId);

    @Query("select new com.edumoca.soma.entities.models.CourseSessionResponse(cs.sessionId,cs.period.periodName,cs.period.periodNo,cs.startTime,cs.endTime) from CourseSession cs where cs.institution.institutionId = :institutionId and cs.sessionId = :sessionId")
    public CourseSessionResponse getCourseSessionByInstitutionIdAndSessionId(@Param("institutionId") Integer institutionId,@Param("sessionId") Integer sessionId);

    @Query("select new com.edumoca.soma.entities.models.CourseSessionResponse(cs.sessionId,cs.period.periodName,cs.period.periodNo,cs.startTime,cs.endTime) from CourseSession cs where cs.sessionId = :sessionId")
    public CourseSessionResponse getCourseSessionBySessionId(@Param("sessionId") Integer sessionId);
}
