package com.empresa.rh.controller.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record FuncionarioRequest(
    @NotBlank String nome, 
    @NotBlank String email, 
    @NotBlank Double salario,
    @NotBlank Date dataContratacao,
    Long departamentoId,
    Long cargoId,
    Long chefeId
) {
}
