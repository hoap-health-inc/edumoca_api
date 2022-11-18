package com.edumoca.soma.entities.models;

import com.edumoca.soma.entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParentResponse {
     int userId;
     String loginId;
     String profilePic;
     Gender gender;
     String firstName;
     String middleName;
     String lastName;
     String fullName;
     String emailAddress;
     String motherTongue;
     String occupation;
     String userType;
}
