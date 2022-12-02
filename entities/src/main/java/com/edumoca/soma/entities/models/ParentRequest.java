package com.edumoca.soma.entities.models;

import com.edumoca.soma.entities.Gender;
import com.edumoca.soma.entities.ParentType;
import com.edumoca.soma.entities.Student;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class ParentRequest {
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
    private LocalDate dateOfBirth;
    private Set<StudentIdRequest> studentIdsRequest = new HashSet<>();
    private ParentType parentType;
    private String occupation;
}
