package com.edumoca.soma.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "SUBJECTHEAD")
@Setter
@Getter
public class SubjectHead extends Teacher{ 
}
