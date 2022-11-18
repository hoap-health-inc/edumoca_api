package com.edumoca.soma.entities.models;

import com.edumoca.soma.entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class StudentResponse {
     int userId;
     String loginId;
     //String password;
     String profilePic;
     Gender gender;
     String firstName;
     String middleName;
     String lastName;
     String emailAddress;
     String motherTongue;
     String presentAddress;
     String permanentAddress;
     //RoleIdRequest roleIdRequest;
     int gradeSectionInstitutionYearMappingId;
     LocalDate dateOfBirth;
     String lrn;
     String psaRegNumber;
     String citizenShip;
     String religion;
     String placeOfBirth;
     String schoolLastAttended;
     String userType;
}
