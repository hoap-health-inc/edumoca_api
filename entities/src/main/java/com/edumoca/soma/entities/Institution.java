package com.edumoca.soma.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "INSTITUTION", uniqueConstraints = @UniqueConstraint(columnNames = {"INST_NAME"}))
@Data
public class Institution extends BaseEntity{
    @Id
    @Column(name = "INST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int institutionId;
    @Column(name = "INST_NAME",nullable = false)
    private String institutionName;
    @OneToMany(mappedBy = "institution")
    Set<GradeSectionInstitutionMapping> gradeSectionInstitutionMappingSet = new HashSet<>();
}
