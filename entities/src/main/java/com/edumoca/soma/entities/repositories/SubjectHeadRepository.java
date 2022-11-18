package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.models.TeacherResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edumoca.soma.entities.SubjectHead;
import org.springframework.data.repository.query.Param;

public interface SubjectHeadRepository extends PagingAndSortingRepository<SubjectHead, Integer>{

    @Query("select new com.edumoca.soma.entities.models.TeacherResponse(s.userId,s.loginId,s.profilePic,s.gender,s.firstName,s.middleName,s.lastName,s.emailAddress,s.motherTongue,s.presentAddress,s.permanantAddress,s.department.deptId,s.department.deptName,'SUBJECTHEAD') from SubjectHead s where s.loginId= :teacherLoginId")
    public TeacherResponse getSubjectHeadByLoginId(@Param("teacherLoginId") String teacherLoginId);

}
