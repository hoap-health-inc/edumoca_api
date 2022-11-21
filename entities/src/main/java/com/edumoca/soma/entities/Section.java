package com.edumoca.soma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SECTION",uniqueConstraints = {@UniqueConstraint(columnNames = {"SECTION_NAME","INST_ID"})})
@Data
public class Section extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SECTION_ID")
    @JsonIgnore
    private int sectionId;
    @Column(name = "SECTION_NAME",nullable = false)
    private String sectionName;
    @Column(name = "SECTION_DESC")
    private String sectionDescription;
	@ManyToOne
	@JoinColumn(name = "INST_ID",referencedColumnName = "INST_ID",nullable = false)
    @JsonIgnore
    private Institution institution;
}
