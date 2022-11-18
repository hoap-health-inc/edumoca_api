package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.SuperAdmin;
import com.edumoca.soma.entities.models.SuperAdminResponse;
import com.edumoca.soma.entities.models.TeacherResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface SuperAdminRepository  extends PagingAndSortingRepository<SuperAdmin, Integer> {
    @Query("select new com.edumoca.soma.entities.models.SuperAdminResponse(sa.userId,sa.loginId,sa.profilePic,sa.gender,sa.firstName,sa.middleName,sa.lastName,sa.emailAddress,sa.motherTongue,sa.presentAddress,sa.permanantAddress,'SUPERADMIN') from SuperAdmin sa where sa.loginId= :teacherLoginId")
    public SuperAdminResponse getSuperAdminByLoginId(@Param("teacherLoginId") String teacherLoginId);
}
