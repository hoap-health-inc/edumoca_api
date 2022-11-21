package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS",uniqueConstraints = {@UniqueConstraint(columnNames = {"BOOK_NAME"})})
@Data
public class Book extends BaseEntity{
    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name = "BOOK_NAME")
    private String bookName;
    @ManyToOne
    @JoinTable(name = "GRADE_SECTION_INSTITUTION_MAP_ID",joinColumns = {@JoinColumn(name = "BOOK_ID")},inverseJoinColumns = {@JoinColumn(name = "GRADE_SECTION_INSTITUTION_MAP_ID")})
    private GradeSectionInstitutionMap gradeSectionInstitutionMaps;
    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "SUBJECT_ID")
    private Subject subject;
    @Column(name = "HAS_DIGITAL_COPY")
    private boolean hasDigitalCopy;
    @Column(name = "BOOK_FORMAT")
    private String bookFormat;
    @Column(name = "BOOK_DATA_ID")
    private String bookLocationId;
    @Column(name = "COVER_PAGE")
    private String coverPage;
}
