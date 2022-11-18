package com.edumoca.soma.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@DiscriminatorValue(value = "TEACHER")
@Data
public class Teacher extends AppUser{
	
	@OneToOne
	@JoinColumn(name = "DEPT_ID")
	private Department department;
}
