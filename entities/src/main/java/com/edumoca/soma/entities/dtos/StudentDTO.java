package com.edumoca.soma.entities.dtos;

import com.edumoca.soma.entities.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {
    private int userId;
    private String loginId;
    //String password;
    //private String profilePic;
    private byte[] profilePic;
    private Gender gender;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private String motherTongue;
    private String presentAddress;
    private String permanentAddress;
    //RoleIdRequest roleIdRequest;
    private int gradeSectionInstitutionYearMappingId;
    private LocalDate dateOfBirth;
    private String lrn;
    private String psaRegNumber;
    private String citizenShip;
    private String religion;
    private String placeOfBirth;
    private String schoolLastAttended;
    private String userType;
}
