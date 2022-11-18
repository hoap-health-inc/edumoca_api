package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.models.ScheduleResponse;
import com.edumoca.soma.entities.models.StudentScheduleResponse;
import com.edumoca.soma.entities.models.TeacherScheduleResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edumoca.soma.entities.Schedule;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Integer>{

    @Query("select new com.edumoca.soma.entities.models.ScheduleResponse(sch.scheduleId,sch.scheduleName,sch.scheduleDateTime,sch.session.period.periodName,sch.session.period.periodNo,sch.startDate,sch.endDate,sch.session.sessionId,sch.session.startTime,sch.session.endTime,sch.recurring,sch.fullDay,sch.teacherGradeSectionSubjectMapping.teacher.userId,sch.teacherGradeSectionSubjectMapping.teacher.firstName,sch.teacherGradeSectionSubjectMapping.subject.subjectId,sch.teacherGradeSectionSubjectMapping.subject.subjectName) from Schedule sch where sch.gradeSectionInstitutionYearMapping.gradeSectionInstitutionMapping.institution.institutionId = :institutionId")
     List<ScheduleResponse> getAllScheduleByInstitution(@Param("institutionId") Integer institutionId);

    @Query("select new com.edumoca.soma.entities.models.ScheduleResponse(sch.scheduleId,sch.scheduleName,sch.scheduleDateTime,sch.session.period.periodName,sch.session.period.periodNo,sch.startDate,sch.endDate,sch.session.sessionId,sch.session.startTime,sch.session.endTime,sch.recurring,sch.fullDay,sch.teacherGradeSectionSubjectMapping.teacher.userId,sch.teacherGradeSectionSubjectMapping.teacher.firstName,sch.teacherGradeSectionSubjectMapping.subject.subjectId,sch.teacherGradeSectionSubjectMapping.subject.subjectName) from Schedule sch where sch.gradeSectionInstitutionYearMapping.gradeSectionInstitutionMapping.institution.institutionId= :institutionId and sch.scheduleId= :scheduleId")
     ScheduleResponse getScheduleByInstitutionIdAndScheduleId(@Param("institutionId") Integer institutionId,@Param("scheduleId") Integer scheduleId);

    @Query("select new com.edumoca.soma.entities.models.ScheduleResponse(sch.scheduleId,sch.scheduleName,sch.scheduleDateTime,sch.session.period.periodName,sch.session.period.periodNo,sch.startDate,sch.endDate,sch.session.sessionId,sch.session.startTime,sch.session.endTime,sch.recurring,sch.fullDay,sch.teacherGradeSectionSubjectMapping.teacher.userId,sch.teacherGradeSectionSubjectMapping.teacher.firstName,sch.teacherGradeSectionSubjectMapping.subject.subjectId,sch.teacherGradeSectionSubjectMapping.subject.subjectName) from Schedule sch where sch.scheduleId= :scheduleId")
     ScheduleResponse getScheduleByScheduleId(@Param("scheduleId") Integer scheduleId);

    @Query("select new com.edumoca.soma.entities.models.StudentScheduleResponse(sch.scheduleId,sch.scheduleName,sch.scheduleDateTime,sch.session.period.periodName,sch.session.period.periodNo,sch.startDate,sch.endDate,sch.session.sessionId,sch.session.startTime,sch.session.endTime,sch.recurring,sch.fullDay,sch.teacherGradeSectionSubjectMapping.teacher.userId,sch.teacherGradeSectionSubjectMapping.teacher.firstName,sch.teacherGradeSectionSubjectMapping.subject.subjectId,sch.teacherGradeSectionSubjectMapping.subject.subjectName) from Student std inner join std.gradeSectionInstitutionYearMapping gsiym inner join gsiym.schedules sch  where std.userId= :userId and sch.startDate <= :currentDate and sch.endDate >= :currentDate and sch.recurring = true")
     List<StudentScheduleResponse> getScheduleByStudentLoginId(@Param("userId") Integer userId,@Param("currentDate") Date currentDate);

    @Query(value = "select sdw.day_of_week from schedule_day_of_week sdw where sdw.schedule_schedule_id= :scheduleId order by case when sdw.day_of_week = 'Sunday' then 1 when sdw.day_of_week = 'Monday' then 2 when sdw.day_of_week = 'Tuesday' then 3 when sdw.day_of_week = 'Wednesday' then 4 when sdw.day_of_week = 'Thursday' then 5 when sdw.day_of_week = 'Friday' then 6 when sdw.day_of_week = 'Saturday' then 7 end asc",nativeQuery = true)
     List<DayOfWeek> getDayOfWeekByScheduleId(@Param("scheduleId") Integer scheduleId);

    @Query("select new com.edumoca.soma.entities.models.TeacherScheduleResponse(sch.scheduleId,sch.scheduleName,sch.scheduleDateTime,sch.session.period.periodName,sch.session.period.periodNo,sch.startDate,sch.endDate,sch.session.sessionId,sch.session.startTime,sch.session.endTime,sch.recurring,sch.fullDay,sch.teacherGradeSectionSubjectMapping.subject.subjectId,sch.teacherGradeSectionSubjectMapping.subject.subjectName) from TeacherGradeSectionSubjectMapping tgss inner join tgss.gradeSectionInstitutionYearMapping.schedules sch where tgss.teacher.userId= :userId and sch.startDate <= :currentDate and sch.endDate >= :currentDate and sch.recurring = true")
     List<TeacherScheduleResponse> getScheduleByTeacherLoginId(@Param("userId") Integer userId, @Param("currentDate") Date currentDate);


}
