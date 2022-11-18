package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.models.TeacherResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edumoca.soma.entities.HomeRoomTeacher;
import org.springframework.data.repository.query.Param;

public interface HomeRoomTeacherRepository extends PagingAndSortingRepository<HomeRoomTeacher, Integer>{

    @Query("select new com.edumoca.soma.entities.models.TeacherResponse(hrt.userId,hrt.loginId,hrt.profilePic,hrt.gender,hrt.firstName,hrt.middleName,hrt.lastName,hrt.emailAddress,hrt.motherTongue,hrt.presentAddress,hrt.permanantAddress,hrt.department.deptId,hrt.department.deptName,'HOMEROOMTEACHER') from HomeRoomTeacher hrt where hrt.loginId= :teacherLoginId")
    public TeacherResponse getHomeRoomByLoginId(@Param("teacherLoginId") String teacherLoginId);
}
