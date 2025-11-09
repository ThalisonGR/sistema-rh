package com.empresa.rh.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
//    private Date dataContratacao;
    private Double salario;

//    @OneToMany(fetch = FetchType.EAGER)
//    private Departamento departamento;
//
//    @OneToMany
//    private Cargo cargo;
//
//    private Funcionario funcionario;
}
