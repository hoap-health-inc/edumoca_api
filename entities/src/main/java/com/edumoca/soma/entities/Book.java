package com.edumoca.soma.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "BOOKS",uniqueConstraints = {@UniqueConstraint(columnNames = {"BOOK_NAME"})})//,"INST_ID"
@Data
public class Book extends BaseEntity{
    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name = "BOOK_NAME")
    private String bookName;
//    @OneToOne
//    @JoinColumn(name = "GRADE_ID", referencedColumnName = "GRADE_ID")
//    private Grade grade;
//    @ManyToMany
//    @JoinTable(name = "BOOK_SECTION", joinColumns = {@JoinColumn(name = "BOOK_ID")}, inverseJoinColumns = {@JoinColumn(name = "SECTION_ID")})
//    private Set<Section> section;
    @ManyToMany
    //@JoinColumn(name = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID",referencedColumnName = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID")
    @JoinTable(name = "BOOK_GRADE_SECTION_INSTITUTION_YEAR_MAP",joinColumns = {@JoinColumn(name = "BOOK_ID")},inverseJoinColumns = {@JoinColumn(name = "GRADE_SECTION_INSTITUTION_YEAR_MAP_ID")})
    private List<GradeSectionInstitutionYearMapping> gradeSectionInstitutionYearMapping;
    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "SUBJECT_ID")
    private Subject subject;
    @Column(name = "HAS_DIGITAL_COPY")
    private boolean hasDigitalCopy;
    @Column(name = "BOOK_FORMAT")
    private String bookFormat;
    @Column(name = "BOOK_LOCATION")
    private String bookLocation;
    @Column(name = "BOOK_COVER_PAGE_LOCATION")
    private String bookCoverPageLocation;
//    @ManyToOne
//    @JoinColumn(name = "INST_ID",referencedColumnName = "INST_ID")
//    private Institution institution;
}
