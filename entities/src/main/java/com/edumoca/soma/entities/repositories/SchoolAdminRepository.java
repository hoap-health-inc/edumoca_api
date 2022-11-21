package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.SchoolAdmin;
import com.edumoca.soma.entities.models.AdminResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface SchoolAdminRepository extends PagingAndSortingRepository<SchoolAdmin, Integer> {
    @Query("select new com.edumoca.soma.entities.models.AdminResponse(sa.userId,sa.loginId,sa.profilePic,sa.gender,sa.firstName,sa.middleName,sa.lastName,sa.emailAddress,sa.motherTongue,sa.presentAddress,sa.permanantAddress,'SCHOOLADMIN') from SchoolAdmin sa where sa.loginId= :teacherLoginId")
    public AdminResponse getSchoolAdminByLoginId(@Param("teacherLoginId") String teacherLoginId);
}
