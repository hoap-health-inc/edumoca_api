package com.edumoca.soma.entities.dtos;

import com.edumoca.soma.entities.Gender;
import lombok.Data;

@Data
public class ParentDto {
    private int userId;
    private String loginId;
    private byte[] profilePic;
    private Gender gender;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private String motherTongue;
    private String occupation;
    private String userType;
}
