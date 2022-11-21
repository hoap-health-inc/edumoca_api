package com.edumoca.soma.entities.dtos;

import com.edumoca.soma.entities.Gender;
import lombok.Data;

@Data
public class AdminDto {
    private int userId;
    private String loginId;
    //String password;
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
    private String userType;
}
