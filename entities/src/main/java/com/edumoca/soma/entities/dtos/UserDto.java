package com.edumoca.soma.entities.dtos;

import lombok.Data;

@Data
public class UserDto {
    private int userId;
    private String loginId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userType;
}
