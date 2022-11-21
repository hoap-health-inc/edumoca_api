package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.Teacher;
import com.edumoca.soma.entities.models.TeacherResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Integer>{

//	@Query("select new com.edumoca.soma.entities.models.UserScheduleResponse(sch.teacherGradeSectionSubjectMapping.teacher.userId,sch.teacherGradeSectionSubjectMapping.teacher.loginId,sch.teacherGradeSectionSubjectMapping.teacher.firstName,sch.teacherGradeSectionSubjectMapping.teacher.middleName,sch.teacherGradeSectionSubjectMapping.teacher.lastName,sch.scheduleId,sch.scheduleName,sch.startDate,sch.endDate,sch.session.sessionId,sch.session.startTime,sch.session.endTime,sch.session.period.periodName,sch.teacherGradeSectionSubjectMapping.teacher.userId,sch.teacherGradeSectionSubjectMapping.teacher.firstName,sch.teacherGradeSectionSubjectMapping.subject.subjectId,sch.teacherGradeSectionSubjectMapping.subject.subjectName) from Schedule sch where sch.teacherGradeSectionSubjectMapping.teacher.loginId= :teacherLoginId and sch.startDate <= :currentDate and sch.endDate >= :currentDate and sch.recurring = true")
//	public List<UserScheduleResponse> getTeacherScheduleByLoginId(@Param("teacherLoginId") String teacherLoginId, @Param("currentDate") Date currentDate);

    @Query("select new com.edumoca.soma.entities.models.TeacherResponse(t.userId,t.loginId,t.profilePic,t.gender,t.firstName,t.middleName,t.lastName,t.emailAddress,t.motherTongue,t.presentAddress,t.permanantAddress,t.department.deptId,t.department.deptName,'TEACHER') from Teacher t where t.loginId= :teacherLoginId")
    public TeacherResponse getTeacherByLoginId(@Param("teacherLoginId") String teacherLoginId);
}
