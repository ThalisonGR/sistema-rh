package com.empresa.rh.controller.dtos.response;

public record FuncionarioResponse(
    Long id, 
    String nome, 
    String email, 
    Double salario,
    Long departamentoId,
    Long cargoId,
    Long chefeId
) {}
