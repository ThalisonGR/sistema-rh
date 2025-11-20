package com.empresa.rh.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.List;

@Data
@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.PERSIST)
    private List<Funcionario> funcionarioList;
}
