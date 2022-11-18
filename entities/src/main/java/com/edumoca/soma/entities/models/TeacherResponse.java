package com.edumoca.soma.entities.models;

import com.edumoca.soma.entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TeacherResponse {
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
    int departmentId;
    String departmentName;
    String userType;
}
