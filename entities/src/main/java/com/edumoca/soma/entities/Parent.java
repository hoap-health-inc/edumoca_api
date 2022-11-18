package com.edumoca.soma.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

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

