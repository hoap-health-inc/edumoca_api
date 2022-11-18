package com.edumoca.soma.entities;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "USER_ROLES",uniqueConstraints = {@UniqueConstraint(columnNames = {"ROLE_NAME","INST_ID"})})
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private int roleId;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @ManyToOne
    @JoinColumn(name = "INST_ID",referencedColumnName = "INST_ID")
    private Institution institution;
}
