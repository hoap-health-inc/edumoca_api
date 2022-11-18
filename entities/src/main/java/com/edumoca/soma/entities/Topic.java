package com.edumoca.soma.entities;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "TOPIC",uniqueConstraints = {@UniqueConstraint(columnNames = {"TOPIC_NAME","CHAPTER_ID"})})
@Data
public class Topic extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPIC_ID")
    private int topicId;
    @Column(name = "TOPIC_NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "CHAPTER_ID",referencedColumnName = "CHAPTER_ID")
    private Chapter chapter;
}
