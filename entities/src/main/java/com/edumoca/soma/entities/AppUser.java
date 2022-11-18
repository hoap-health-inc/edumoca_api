package com.edumoca.soma.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS", uniqueConstraints = @UniqueConstraint(columnNames = {"LOGIN_ID"}))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING)
public class AppUser extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int userId;
    @Column(name = "LOGIN_ID")
    private String loginId;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PROFILE_PIC")
    private String profilePic;
    @Column(name = "GENDER")
    private Gender gender;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MIDDILE_NAME")
    private String middleName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "MOTHER_TONGUE")
    private String motherTongue;
    @Column(name = "PRESENT_ADDRESS")
    private String presentAddress;
    @Column(name = "PERMANANT_ADDRESS")
    private String permanantAddress;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE_MAPPING",
            joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @NotNull(message = "Roles cannot be blank or null.")
    @NotEmpty
    private Set<Role> roles = new HashSet<>();
}
