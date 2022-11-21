package com.edumoca.soma.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "GRADE_SECTION_INSTITUTION_MAPPING",uniqueConstraints = @UniqueConstraint(columnNames = {"GRADE_ID","SECTION_ID","INST_ID"}))
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "gradeSectionInstitutionMappingId")
public class GradeSectionInstitutionMap {
    @Id
    @Column(name = "GRADE_SECTION_INSTITUTION_MAP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeSectionInstitutionMappingId;
    @ManyToOne
    @JoinColumn(name = "GRADE_ID", referencedColumnName = "GRADE_ID",nullable = false)
    private Grade grade;
    @ManyToOne
    @JoinColumn(name = "SECTION_ID", referencedColumnName = "SECTION_ID",nullable = false)
    private Section section;
    @ManyToOne
    @JoinColumn(name = "INST_ID",referencedColumnName = "INST_ID",nullable = false)
    private Institution institution;
    @OneToMany(mappedBy = "gradeSectionInstitutionMaps")
    private Set<Book> books;
}
