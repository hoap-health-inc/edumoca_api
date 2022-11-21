package com.edumoca.soma.entities.models;

import com.edumoca.soma.entities.Gender;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class StudentRequest implements Serializable {
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
    private String lrn;
    private String psaRegNumber;
    private String citizenShip;
    private String religion;
    private String placeOfBirth;
    private String schoolLastAttended;
}
