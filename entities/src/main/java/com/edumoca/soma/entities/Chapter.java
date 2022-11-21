package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CHAPTERS",uniqueConstraints = {@UniqueConstraint(columnNames = {"CHAPTER_NAME"})})
@Data
public class Chapter extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAPTER_ID")
    private int chapterId;
    @Column(name = "CHAPTER_NAME")
    private String name;
}
