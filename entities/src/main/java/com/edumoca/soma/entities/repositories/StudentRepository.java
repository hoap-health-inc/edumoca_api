package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.models.StudentResponse;
import com.edumoca.soma.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends PagingAndSortingRepository<Student,Integer> {

    @Query("select new com.edumoca.soma.entities.models.StudentResponse(std.userId,std.loginId,std.profilePic,std.gender,std.firstName,std.middleName,std.lastName,std.emailAddress,std.motherTongue,std.presentAddress,std.permanantAddress,std.gradeSectionInstitutionYearMapping.gradeSectionInstitutionYearMapId,std.dateOfBirth,std.lrn,std.psaRegNumber,std.citizenShip,std.religion,std.placeOfBirth,std.schoolLastAttended,'STUDENT') from Student std where std.loginId= :studentLoginId")
    public StudentResponse getStudentByLoginId(@Param("studentLoginId") String studentLoginId);

}
