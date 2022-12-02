package com.edumoca.soma.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TOPIC",uniqueConstraints = {@UniqueConstraint(columnNames = {"TOPIC_NAME"})})
@Data
public class Topic extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPIC_ID")
    private int topicId;
    @Column(name = "TOPIC_NAME")
    private String name;
}
