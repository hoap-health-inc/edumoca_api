package com.edumoca.soma.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "PRINCIPAL")
@Data
@EqualsAndHashCode(callSuper=true)
public class Principal extends Teacher{

}
