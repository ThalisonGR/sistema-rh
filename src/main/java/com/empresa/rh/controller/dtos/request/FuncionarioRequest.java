package com.empresa.rh.controller.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FuncionarioRequest(
    @NotBlank String nome, 
    @NotBlank String email, 
    Double salario,

    @NotNull(message = "Não foi informado um departamento")
    LocalDate dataContratacao,

    @NotNull(message = "Não foi informado um departamento")
    Long departamentoId,

    @NotNull(message = "cannot be null or empty")
    Long cargoId,

    Long chefeId
) {
}
