package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.Principal;
import com.edumoca.soma.entities.models.TeacherResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PrincipalRepository extends PagingAndSortingRepository<Principal, Integer>{

    @Query("select new com.edumoca.soma.entities.models.TeacherResponse(p.userId,p.loginId,p.profilePic,p.gender,p.firstName,p.middleName,p.lastName,p.emailAddress,p.motherTongue,p.presentAddress,p.permanantAddress,p.department.deptId,p.department.deptName,'PRINCIPAL') from Principal p where p.loginId= :teacherLoginId")
    public TeacherResponse getPrincipalByLoginId(@Param("teacherLoginId") String teacherLoginId);

}
