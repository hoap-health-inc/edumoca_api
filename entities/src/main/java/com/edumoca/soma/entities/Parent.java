package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@DiscriminatorValue(value = "PARENT")
public class Parent extends AppUser{
    @ManyToMany
    @JoinTable(name = "PARENT_STUDENT_MAPPING", joinColumns = {@JoinColumn(name = "PARENT_ID")}, inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID")})
    @NotNull(message = "Student cannot be blank or null.")
    @NotEmpty
    private Set<Student> students = new HashSet<>();
    @Column(name = "PARENT_TYPE")
    private ParentType parentType;
    @Column(name = "OCCUPATION")
    private String occupation;
}

