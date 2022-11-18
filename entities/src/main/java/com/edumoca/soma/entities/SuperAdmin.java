package com.edumoca.soma.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DiscriminatorValue(value = "SUPERADMIN")
public class SuperAdmin extends Teacher{

}
