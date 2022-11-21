package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_CHAPTER_TOPIC_MAPPING",uniqueConstraints = @UniqueConstraint(columnNames = {"BOOK_CHAPTER_MAP_ID","TOPIC_ID"}))
@Data
public class BookChapterTopicMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_CHAPTER_TOPIC_MAP_ID")
    private int bookChapterTopicMappingId;
    @ManyToOne
    @JoinColumn(name = "BOOK_CHAPTER_MAP_ID",referencedColumnName = "BOOK_CHAPTER_MAP_ID")
    private BookChapterMap bookChapterMapping;
    @ManyToOne
    @JoinColumn(name = "TOPIC_ID",referencedColumnName = "TOPIC_ID")
    private Topic topic;
}
