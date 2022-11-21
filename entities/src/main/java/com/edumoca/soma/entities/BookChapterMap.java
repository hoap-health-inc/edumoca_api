package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_CHAPTER_MAPPING",uniqueConstraints = @UniqueConstraint(columnNames = {"BOOK_ID","CHAPTER_ID"}))
@Data
public class BookChapterMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_CHAPTER_MAP_ID")
    private int bookChapterMapId;
    @ManyToOne
    @JoinColumn(name = "BOOK_ID",referencedColumnName = "BOOK_ID")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "CHAPTER_ID",referencedColumnName = "CHAPTER_ID")
    private Chapter chapter;
}
