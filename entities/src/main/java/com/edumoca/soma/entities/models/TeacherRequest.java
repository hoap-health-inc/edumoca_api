package com.edumoca.soma.entities.models;

import com.edumoca.soma.entities.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherRequest {
    private int userId;
    private String loginId;
    private String password;
    private String profilePic;
    private Gender gender;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private String motherTongue;
    private String presentAddress;
    private String permanentAddress;
    private RoleIdRequest roleIdRequest;
    private GradeSectionInstitutionYearMapIdRequest gradeSectionInstitutionYearMappingIdRequest;
    private LocalDate dateOfBirth;
    private DepartmentIdRequest departmentIdRequest;
}
