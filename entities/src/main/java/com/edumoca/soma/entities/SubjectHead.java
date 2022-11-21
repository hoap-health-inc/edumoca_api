package com.edumoca.soma.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SUBJECTHEAD")
@Setter
@Getter
public class SubjectHead extends Teacher{ 
}
