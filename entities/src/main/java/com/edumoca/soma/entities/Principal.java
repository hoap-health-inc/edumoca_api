package com.edumoca.soma.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue(value = "PRINCIPAL")
@Data
@EqualsAndHashCode(callSuper=true)
public class Principal extends Teacher{

}
