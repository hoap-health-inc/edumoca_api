package com.edumoca.soma.entities;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "CHAPTERS",uniqueConstraints = {@UniqueConstraint(columnNames = {"CHAPTER_NAME","BOOK_ID"})})
@Data
public class Chapter extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAPTER_ID")
    private int chapterId;
    @Column(name = "CHAPTER_NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "BOOK_ID",referencedColumnName = "BOOK_ID")
    private Book book;
}
