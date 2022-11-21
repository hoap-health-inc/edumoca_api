package com.edumoca.soma.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@DiscriminatorValue(value = "SUPERADMIN")
public class SuperAdmin extends Teacher{

}
